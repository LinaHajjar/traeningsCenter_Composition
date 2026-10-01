package composition;

import java.util.Scanner;

/*
 * UI = brugergrænseflade.
 *
 * Klassen har KUN ansvar for at tale med brugeren: vise menuen og læse input.
 * Reglerne for medlemmer, træningstimer og bookinger skal ligge i dine egne klasser
 * (FitnessCenter, Member, TrainingSession, Booking ...), ikke her.
 *
 * Menuen og input-metoderne er færdige. Din opgave er at udfylde TODO'erne,
 * efterhånden som du laver klasserne i trin 2 i README.md.
 */
public class UI {
    private Scanner scanner; // HAS-A: UI'en læser input med en Scanner

    // TODO (2.6): Når du har lavet FitnessCenter, skal UI'en have et felt til det:
    // private FitnessCenter center;

    public UI(Scanner scanner) {
        this.scanner = scanner;
        // TODO (2.6): Modtag et FitnessCenter i konstruktøren, og gem det i feltet
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Vælg: ");
            System.out.println();

            if (choice == 1) {
                showAllMembers();
            } else if (choice == 2) {
                showAllSessions();
            } else if (choice == 3) {
                showAvailableSessions();
            } else if (choice == 4) {
                createMember();
            } else if (choice == 5) {
                createSession();
            } else if (choice == 6) {
                bookSession();
            } else if (choice == 7) {
                cancelBooking();
            } else if (choice == 8) {
                showBookings();
            } else if (choice == 0) {
                running = false;
                System.out.println("Farvel!");
            } else {
                System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("===== Træningscenter ====="); // TODO: Vis centerets navn i stedet
        System.out.println("1. Vis alle medlemmer");
        System.out.println("2. Vis alle træningstimer");
        System.out.println("3. Vis træningstimer med ledige pladser");
        System.out.println("4. Opret medlem");
        System.out.println("5. Opret træningstime");
        System.out.println("6. Book en træningstime");
        System.out.println("7. Afmeld en træningstime");
        System.out.println("8. Vis et medlems aktive bookinger");
        System.out.println("0. Afslut");
    }

    // ---------- Menupunkterne ----------

    private void showAllMembers() {
        // TODO: Bed FitnessCenter om at udskrive alle medlemmer
        notImplemented();
    }

    private void showAllSessions() {
        // TODO: Bed FitnessCenter om at udskrive alle træningstimer
        notImplemented();
    }

    private void showAvailableSessions() {
        // TODO: Bed FitnessCenter om at udskrive træningstimer med ledige pladser
        notImplemented();
    }

    private void createMember() {
        String name = readText("Navn: ");
        int memberId = readInt("Medlemsnummer: ");
        String type = readText("Medlemstype (Basic/Premium): ");

        // TODO (2.3): Opret et Member-objekt med name, memberId og type
        // TODO (2.8): Opret i stedet et BasicMember eller et PremiumMember afhængigt af type.
        //             Hvilken type skal variablen have, så den kan indeholde begge?
        // TODO: Tilføj medlemmet til FitnessCenter
        notImplemented();
    }

    private void createSession() {
        String title = readText("Titel: ");
        String instructor = readText("Instruktør: ");
        int capacity = readInt("Antal pladser: ");

        // TODO (2.2): Opret et TrainingSession-objekt, og tilføj det til FitnessCenter
        notImplemented();
    }

    private void bookSession() {
        int memberId = readInt("Medlemsnummer: ");
        String title = readText("Træningstime: ");

        // TODO: Find medlemmet og træningstimen i FitnessCenter.
        //       Hvad skal der ske, hvis en af dem ikke findes?
        // TODO: Bed FitnessCenter om at booke træningstimen for medlemmet
        notImplemented();
    }

    private void cancelBooking() {
        int memberId = readInt("Medlemsnummer: ");
        String title = readText("Træningstime der skal afmeldes: ");

        // TODO: Find medlemmet og træningstimen, og bed FitnessCenter om at afmelde bookingen
        notImplemented();
    }

    private void showBookings() {
        int memberId = readInt("Medlemsnummer: ");

        // TODO: Find medlemmet, og udskriv medlemmets aktive bookinger
        notImplemented();
    }

    private void notImplemented() {
        System.out.println("Dette menupunkt er ikke lavet endnu.");
    }

    // ---------- Hjælpemetoder til input ----------

    // Bliver ved med at spørge, indtil brugeren skriver et helt tal
    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Skriv et tal: ");
        }
        int number = scanner.nextInt();
        scanner.nextLine(); // fjern linjeskiftet efter tallet
        return number;
    }

    // Bliver ved med at spørge, indtil brugeren skriver noget
    private String readText(String prompt) {
        System.out.print(prompt);
        String text = scanner.nextLine().trim();
        while (text.isEmpty()) {
            System.out.print("Feltet må ikke være tomt: ");
            text = scanner.nextLine().trim();
        }
        return text;
    }
}
