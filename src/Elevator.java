public class Elevator {
    int numberOfFloors;
    int currentFloor;
    int id;
    boolean doorsOpen;

    public Elevator(int id, int numberOfFloors, int startingFloor) {
        this.id = id;
        this.numberOfFloors = numberOfFloors;
        this.currentFloor = startingFloor;
    }

    public void moveToFloor(int desiredFloor) {
        if (desiredFloor > numberOfFloors || desiredFloor < 1) {
            System.out.println("Elevator: " + id + " received an invalid desired floor - ignoring");
            return;
        }

        currentFloor = desiredFloor;
        System.out.println("Elevator " + id + " moving to floor: " + desiredFloor);
    }

    public String toString() {
        String doorsOpenStr = " and it has its doors open.";
        if (doorsOpen == false) {
            doorsOpenStr = " and it has its doors closed.";
        }

        return "Elevator: " + id + " is currently on floor: " + currentFloor + doorsOpenStr;
    }

    public void openDoor() {
        doorsOpen = true;
        System.out.println("Elevator: " + id + " is openeing its doors");
    }

    public void closeDoor() {
        doorsOpen = false;
        System.out.println("Elevator: " + id + " is closing its doors");
    }
}
