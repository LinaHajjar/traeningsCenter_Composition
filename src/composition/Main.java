package composition;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // TODO (2.6): Opret et FitnessCenter, og tilføj nogle medlemmer og træningstimer,
        //             så man kan prøve programmet med det samme. Giv det til UI'en.


        UI ui = new UI(new Scanner(System.in));
        ui.run();
    }
}
