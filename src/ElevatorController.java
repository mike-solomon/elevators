import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ElevatorController {
    int numberOfElevators;
    int numberOfFloors;
    int startingFloor;
    private ArrayList<Elevator> elevators; // Don't think we need this but lets double check later. Prolly can just use sets.
    private Set<Elevator> occupiedElevators;
    private Set<Elevator> unoccupiedElevators;
    private Set<Elevator> elevatorsGoingThroughMaintenance;

    public ElevatorController(int numberOfElevators, int numberOfFloors, int startingFloor) {
        this.numberOfElevators = numberOfElevators;
        this.numberOfFloors = numberOfFloors;
        this.startingFloor = startingFloor;

        this.elevators = new ArrayList<>();
        for (int i = 1; i <= numberOfElevators; i++) {
            Elevator elevator = new Elevator(i, numberOfFloors, startingFloor);
            elevators.add(elevator);
        }

        this.occupiedElevators = new HashSet<>();
        this.unoccupiedElevators = new HashSet<>();
        unoccupiedElevators.addAll(elevators);
        this.elevatorsGoingThroughMaintenance = new HashSet<>();
    }

    public void requestElevator(int floorRequestWasMadeFrom, int desiredFloor) {
        System.out.println("A request was made from floor " + floorRequestWasMadeFrom + " to go to floor: " + desiredFloor);

        // Priority 1
        // Check and see if an UNOCCUPIED elevator is already at the requested floor
        for (Elevator elevator : unoccupiedElevators) {
            if (elevator.getCurrentFloor() == floorRequestWasMadeFrom) {
                elevator.pickupPerson();
                // TODO: Cleanup this code perhaps - lots of adding and removing from sets that's duplicated
                occupiedElevators.add(elevator);
                unoccupiedElevators.remove(elevator);
                elevator.moveToFloor(desiredFloor);
                occupiedElevators.remove(elevator);
                unoccupiedElevators.remove(elevator);
                checkIfElevatorNeedsMaintenance(elevator);
                return;
            }
        }

        // Priority 2
        // Check and see if an OCCUPIED elevator is moving and will pass the floor on its way
        // TODO: What about time?
        for (Elevator elevator : occupiedElevators) {
            // Elevator is on floor 2 and is moving to floor 10
            // Requested floor is 5 so we should stop this elevator
            if (elevator.isMovingUp() && (floorRequestWasMadeFrom > elevator.getCurrentFloor()) && (elevator.getDesiredFloor() > floorRequestWasMadeFrom)) {
                // TODO: Stop the elevator on the floor?
            } else if (!elevator.isMovingUp() && (floorRequestWasMadeFrom < elevator.getCurrentFloor()) && (elevator.getDesiredFloor() < floorRequestWasMadeFrom)) {
                // TODO: Stop the elevator on the floor
            }
        }

        // Priority 3
        // The unoccupied elevator closest to the requested floor should move to there
        int closestDistanceToFloor = Integer.MAX_VALUE;
        Elevator closestElevator = null;
        for (Elevator elevator : unoccupiedElevators) {
            int distanceBetweenFloors = Math.abs(floorRequestWasMadeFrom - elevator.getCurrentFloor());
            if (distanceBetweenFloors < closestDistanceToFloor) {
                closestElevator = elevator;
                closestDistanceToFloor = distanceBetweenFloors;
            }
        }

        if (closestElevator != null) {
            closestElevator.moveToFloor(floorRequestWasMadeFrom);
            closestElevator.pickupPerson();
            occupiedElevators.add(closestElevator);
            unoccupiedElevators.remove(closestElevator);
            closestElevator.moveToFloor(desiredFloor);
            occupiedElevators.remove(closestElevator);
            unoccupiedElevators.remove(closestElevator);
            checkIfElevatorNeedsMaintenance(closestElevator);
            return;
        }

        // Priority 4
        // If there are no unoccupied elevators that are going to move past this then what? Wait?
        System.out.println("There are no unoccupied elevators and there aren't any occupied elevators moving past :(");
    }

    public Set<Elevator> getUnoccupiedElevators() {
        return this.unoccupiedElevators;
    }

    public Set<Elevator> getOccupiedElevators() {
        return this.occupiedElevators;
    }


    public Set<Elevator> elevatorsGoingThroughMaintenance() {
        return this.elevatorsGoingThroughMaintenance;
    }


    public void fixElevator(Elevator elevator) {
        elevator.fixElevator();
    }

    private void checkIfElevatorNeedsMaintenance(Elevator elevator) {
        if (elevator.isMaintenanceMode()) {
            elevatorsGoingThroughMaintenance.add(elevator);
            unoccupiedElevators.remove(elevator);
        }
    }
}
