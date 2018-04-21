import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ElevatorController {
    int numberOfElevators;
    int numberOfFloors;
    int startingFloor;
    private ArrayList<Elevator> elevators;
    private Set<Elevator> occupiedElevators;
    private Set<Elevator> unoccupiedElevators;

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
    }

    public void requestElevator(int floorRequestWasMadeFrom, int desiredFloor) {
        System.out.println("A request was made from floor " + floorRequestWasMadeFrom + " to go to floor: " + desiredFloor);

        // Priority 1
        // Check and see if an UNOCCUPIED elevator is already at the requested floor
        for (Elevator elevator : unoccupiedElevators) {
            if (elevator.getCurrentFloor() == floorRequestWasMadeFrom) {
                elevator.pickupPerson();
                elevator.moveToFloor(desiredFloor);
                return;
            }
        }

        // Priority 2
        // Check and see if an OCCUPIED elevator is moving and will pass the floor on its way

        // Priority 3
        // The unoccupied elevator closest to the requested floor should move to there

        // Priority 4
        // If there are no unoccupied elevators that are going to move past this then what? Wait?
    }

}
