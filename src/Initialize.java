import java.util.ArrayList;

public class Initialize {
    public static void main(String[] args) {
        System.out.println("hello world");
        int desiredNumberOfElevators = 2;
        int desiredNumberOfFloors = 1;
        int startingFloor = 1;

        ArrayList<Elevator> elevators = new ArrayList<>();
        for (int i = 1; i <= desiredNumberOfElevators; i++) {
            Elevator elevator = new Elevator(i, desiredNumberOfFloors, startingFloor);
            elevators.add(elevator);
        }

        for (Elevator elevator : elevators) {
            System.out.println(elevator);
        }


    }
}
