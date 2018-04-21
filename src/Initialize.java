public class Initialize {
    public static void main(String[] args) {
        System.out.println("Starting elevator program");

        int desiredNumberOfElevators = 2;
        int desiredNumberOfFloors = 10;
        int startingFloor = 1;

        ElevatorController controller = new ElevatorController(desiredNumberOfElevators, desiredNumberOfFloors, startingFloor);
        controller.requestElevator(1, 3);
        controller.requestElevator(1, 3);

    }
}
