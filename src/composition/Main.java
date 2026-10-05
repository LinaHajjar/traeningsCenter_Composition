package composition;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // TODO : Opret et FitnessCenter, og tilføj nogle medlemmer og træningstimer,
        //             så man kan prøve programmet med det samme. Giv det til UI'en.
        FitnessCenter center= new FitnessCenter("pureGym");


        //FinalTest
        // Testdata, så man kan prøve programmet med det samme.
        /*center.addMember(new Member("Sara", 101));
        center.addMember(new Member("Ali", 102));
        center.addMember(new Member("Emma", 103));

        center.addSession(new TrainingSession("Yoga", "Mette", 2));
        center.addSession(new TrainingSession("Spinning", "Jonas", 1));
        center.addSession(new TrainingSession("Crossfit", "Lars", 10));
        center.addSession(new TrainingSession("Pilates", "Nadia", 5));*/


        UI ui = new UI(new Scanner(System.in), center); //TODO: skal rettes når et fitnessCenter er oprettet
        ui.run();
    }
}
