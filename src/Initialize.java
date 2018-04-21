import java.util.ArrayList;

public class Initialize {
    public static void main(String[] args) {
        System.out.println("hello world");
        int desiredNumberOfElevators = 2;
        int desiredNumberOfFloors = 1;

        ArrayList<Elevator> elevators = new ArrayList<>();
        for (int i = 0; i < desiredNumberOfElevators; i++) {
            Elevator elevator = new Elevator(desiredNumberOfFloors);
            elevators.add(elevator);
        }

        System.out.println(elevators);


    }
}
