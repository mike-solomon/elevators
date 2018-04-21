import java.util.concurrent.TimeUnit;

public class Elevator {
    private static int NUMBER_OF_TRIPS_BEFORE_MAINTENANCE = 100;
    private int numberOfFloors;
    private int currentFloor;
    private int id;
    private boolean doorsOpen;
    private boolean occupied;
    private boolean maintenanceMode;
    private int numberOfTrips;
    private int numberOfFloorsPassed;
    private boolean moving;
    private boolean movingUp;
    private int desiredFloor;

    public Elevator(int id, int numberOfFloors, int startingFloor) {
        this.id = id;
        this.numberOfFloors = numberOfFloors;
        this.currentFloor = startingFloor;
        this.maintenanceMode = false;
        this.numberOfTrips = 0;
        this.numberOfFloorsPassed = 0;
        this.moving = false;
    }

    public void moveToFloor(int desiredFloor) {
        if (desiredFloor > numberOfFloors || desiredFloor < 1) {
            System.out.println("Elevator: " + id + " received an invalid desired floor - ignoring");
            return;
        }

        if (desiredFloor - currentFloor > 0) {
            this.movingUp = false;
        } else {
            this.movingUp = true;
        }

        this.desiredFloor = desiredFloor;

        // TODO: Add element of time here or some way to halt the elevator while it's moving
        stepToNextFloor();

        numberOfFloorsPassed += Math.abs(currentFloor - desiredFloor);
        currentFloor = desiredFloor;
        System.out.println("Elevator " + id + " moving to floor: " + desiredFloor);

        this.numberOfTrips++;
        if (numberOfTrips >= NUMBER_OF_TRIPS_BEFORE_MAINTENANCE) {
            System.out.println("Elevator: " + this.id + " is going into maintenance mode!");
            this.maintenanceMode = true;
        }
    }

    // This won't really be of use unless we have threading to halt while it's stepping through the floors because
    // we won't be able to pick up any breaking or halting input and change our action.
    public void stepToNextFloor() {
        while (this.currentFloor != this.desiredFloor) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                // Ignore for now
            }

            if (this.movingUp) {
                this.currentFloor++;
            } else {
                this.currentFloor--;
            }
        }
    }

    public String toString() {
        String occupiedStr = " and it's occupied ";
        if (occupied == false) {
            occupiedStr = "and it's not occupied ";
        }

        String doorsOpenStr = " and it has its doors open.";
        if (doorsOpen == false) {
            doorsOpenStr = " and it has its doors closed.";
        }

        return "Elevator: " + id + " is currently on floor: " + currentFloor + occupiedStr + doorsOpenStr;
    }

    public void openDoor() {
        doorsOpen = true;
        System.out.println("Elevator: " + id + " is openeing its doors");
    }

    public void closeDoor() {
        doorsOpen = false;
        System.out.println("Elevator: " + id + " is closing its doors");
    }

    public void pickupPerson() {
        this.openDoor();
        this.occupied = true;
        this.closeDoor();
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public boolean isMaintenanceMode() {
        return this.maintenanceMode;
    }

    public void fixElevator() {
        // Question: Should the number of floors passed be reset when we fix the elevator?
        System.out.println("Elevator: " + this.id + " is fixed!");
        this.maintenanceMode = false;
    }

    public int getNumberOfFloorsPassed() {
        return this.numberOfFloorsPassed;
    }

    public boolean isMovingUp() {
        return this.movingUp;
    }

    public int getDesiredFloor() {
        return this.desiredFloor;
    }
}
