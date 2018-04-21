import java.util.ArrayList;

public class Initialize {
    public static void main(String[] args) {
        System.out.println("hello world");

        int desiredNumberOfElevators = 2;
        int desiredNumberOfFloors = 10;
        int startingFloor = 1;

        ElevatorController controller = new ElevatorController(desiredNumberOfElevators, desiredNumberOfFloors, startingFloor);
        controller.requestElevator(1, 3);


        ArrayList<Elevator> elevators = new ArrayList<>();
        for (int i = 1; i <= desiredNumberOfElevators; i++) {
            Elevator elevator = new Elevator(i, desiredNumberOfFloors, startingFloor);
            elevators.add(elevator);
        }

        elevators.get(0).moveToFloor(5);
        elevators.get(0).openDoor();
        elevators.get(0).closeDoor();

        elevators.get(1).moveToFloor(11);
        elevators.get(1).moveToFloor(-1);

        for (Elevator elevator : elevators) {
            System.out.println(elevator);
        }


    }
}
