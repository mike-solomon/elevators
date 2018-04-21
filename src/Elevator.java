public class Elevator {
    int numberOfFloors;
    int currentFloor;
    int id;

    public Elevator(int id, int numberOfFloors, int startingFloor) {
        this.id = id;
        this.numberOfFloors = numberOfFloors;
        this.currentFloor = startingFloor;
    }

    public void moveToFloor(int desiredFloor) {
        if (desiredFloor > numberOfFloors || desiredFloor < 1) {
            System.out.println("Desired floor is invalid - ignoring");
            return;
        }

        currentFloor = desiredFloor;
        System.out.println("Elevator " + id + " moving to floor: " + desiredFloor);
    }

    public String toString() {
        return "Elevator: " + id + " is currently on floor: " + currentFloor;
    }
}
