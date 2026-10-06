package composition;

import java.util.Scanner;

/*
UI (User Interface): brugergrænseflade.

Klassen har KUN ansvar for at tale med brugeren: vise menuen og læse input.
Reglerne for medlemmer, træningstimer og bookinger skal ligge i dine egne klasser
(FitnessCenter, Member, TrainingSession, Booking ...), ikke her.

Menuen og input-metoderne er færdige. Din opgave er at udfylde TODO'erne,
efterhånden som du laver klasserne i trin 2 i TRIN2.md og TRIN3.md
*/


public class UI {
    private Scanner scan; //læser input fra brugeren med en Scanner
    private FitnessCenter center;


    public UI(Scanner scan, FitnessCenter center) {
        this.scan = scan;
        this.center=center;
    }



    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Vælg: ");
            System.out.println();

            switch (choice) {
                case 1:
                    showAllMembers();
                    break;
                case 2:
                    showAllSessions();
                    break;
                case 3:
                    showAvailableSessions();
                    break;
                case 4:
                    opretMember();
                    break;
                case 5:
                    createSession();
                    break;
                case 6:
                    bookSession();
                    break;
                case 7:
                    cancelBooking();
                    break;
                case 8:
                    showBookings();
                    break;
                case 0:
                    running = false;
                    System.out.println("Farvel!");
                    break;

                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("===== "+ center.getName() + " ====="); // TODO: Vis centerets navn i stedet
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
        center.printAllMembers();
    }

    private void showAllSessions() {
        center.printAllSessions();
    }

    private void showAvailableSessions() {
        center.printAvailableSessions();
    }

    private void opretMember() {
        String name = readText("Navn: ");
        int memberId = readInt("Medlemsnummer: ");
        String memberType;


        int memberTypeChoice= readInt("medlemstype: 1= Basic, 2= Premium : ");

        if (memberTypeChoice==1){
            memberType="Basic";
        }else
            memberType="Premium";


        Member m1= new Member(name, memberId, memberType);

        center.addMember(m1);

        /*String memberType= readText("indtast din medlemstype: ");
        Member m1= new Member(name, memberId, memberType);

        center.addMember(m1);*/


    }

    private void createSession() {
        String title = readText("Titel: ");
        String instructor = readText("Instruktør: ");
        int capacity = readInt("Antal pladser: ");

        TrainingSession session1= new TrainingSession(title, instructor, capacity);
        center.addSession(session1);

    }

    private void bookSession() {
        int memberId = readInt("Medlemsnummer: ");
        String title = readText("Træningstime: ");

        Member member = center.findMember(memberId);
        TrainingSession session =center.findSession(title);

        center.bookSession(member, session);
        System.out.println("session booked");

    }

    private void cancelBooking() {
        int memberId = readInt("Medlemsnummer: ");
        String title = readText("Træningstime der skal afmeldes: ");

        Member member = center.findMember(memberId);
        TrainingSession session =center.findSession(title);
        session.removeParticipant(member);
    }

    private void showBookings() {
        int memberId = readInt("Medlemsnummer: ");
        Member member = center.findMember(memberId);
        member.printBookings();

    }

    /*private void notImplemented() {

        System.out.println("Dette menupunkt er ikke lavet endnu.");
    }*/


    // TODO:Finder medlemmet og skriver en besked, hvis det ikke findes
    /*private Member findMember(int memberId) {
        //TODO
    }

    // TODO:Finder træningstimen og skriver en besked, hvis den ikke findes
    private TrainingSession findSession(String title) {
        //TODO
    }*/


    // ---------- Hjælpemetoder til input ----------

    // Bliver ved med at spørge, indtil brugeren skriver et helt tal
    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scan.hasNextInt()) {
            scan.nextLine();
            System.out.print("Skriv et tal: ");
        }
        int number = scan.nextInt();
        scan.nextLine(); // fjern linjeskiftet efter tallet
        return number;
    }

    // Bliver ved med at spørge, indtil brugeren skriver noget
    private String readText(String prompt) {
        System.out.print(prompt);
        String text = scan.nextLine().trim();
        while (text.isEmpty()) {
            System.out.print("Feltet må ikke være tomt: ");
            text = scan.nextLine().trim();
        }
        return text;
    }
}
