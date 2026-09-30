import java.util.ArrayList;
import java.util.Scanner;

/*
Trin 1: Hele træningscenteret skrevet i ÉN klasse.

Programmet virker, men læg mærke til, hvor meget der ligger samme sted:
medlemmer, træningstimer, bookinger, regler og udskrifter.
Data om ét medlem er spredt ud over tre forskellige lister, og
medlemstypen er en String,
som vi hele tiden skal tjekke med if/else.

Brug klassen som udgangspunkt for trin 2 i README.md.
 */
public class ClassIntroduction {
    private String centerName;

    // Medlemmer: samme index i alle tre lister hører til det samme medlem
    private ArrayList<String> memberNames;
    private ArrayList<Integer> memberIds;
    private ArrayList<String> memberTypes; // "Basic" eller "Premium"

    // Træningstimer: samme index i alle fire lister hører til den samme time
    private ArrayList<String> sessionTitles;
    private ArrayList<String> sessionInstructors;
    private ArrayList<Integer> sessionCapacities;
    private ArrayList<Integer> sessionParticipantCounts;

    // Bookinger: samme index i alle tre lister hører til den samme booking
    private ArrayList<Integer> bookingMemberIds;
    private ArrayList<String> bookingSessionTitles;
    private ArrayList<Boolean> bookingActive;

    public ClassIntroduction(String centerName) {
        this.centerName = centerName;
        memberNames = new ArrayList<>();
        memberIds = new ArrayList<>();
        memberTypes = new ArrayList<>();
        sessionTitles = new ArrayList<>();
        sessionInstructors = new ArrayList<>();
        sessionCapacities = new ArrayList<>();
        sessionParticipantCounts = new ArrayList<>();
        bookingMemberIds = new ArrayList<>();
        bookingSessionTitles = new ArrayList<>();
        bookingActive = new ArrayList<>();
    }

    // ---------- Medlemmer ----------

    public boolean addMember(String name, int memberId, String type) {
        if (!type.equals("Basic") && !type.equals("Premium")) {
            System.out.println("Ukendt medlemstype: " + type);
            return false;
        }
        if (findMemberIndex(memberId) != -1) {
            System.out.println("Medlemsnummer " + memberId + " findes allerede.");
            return false;
        }
        memberNames.add(name);
        memberIds.add(memberId);
        memberTypes.add(type);
        return true;
    }

    public int findMemberIndex(int memberId) {
        for (int i = 0; i < memberIds.size(); i++) {
            if (memberIds.get(i) == memberId) {
                return i;
            }
        }
        return -1;
    }

    // Hver gang der kommer en ny medlemstype, skal denne metode rettes...
    public int getMaxBookings(String type) {
        if (type.equals("Basic")) {
            return 2;
        } else if (type.equals("Premium")) {
            return 5;
        }
        return 0;
    }

    // ...og denne metode også
    public double getMonthlyPrice(String type) {
        if (type.equals("Basic")) {
            return 199;
        } else if (type.equals("Premium")) {
            return 349;
        }
        return 0;
    }

    public int getActiveBookingCount(int memberId) {
        int count = 0;
        for (int i = 0; i < bookingMemberIds.size(); i++) {
            if (bookingMemberIds.get(i) == memberId && bookingActive.get(i)) {
                count++;
            }
        }
        return count;
    }

    public void printAllMembers() {
        System.out.println("Medlemmer i " + centerName + ":");
        for (int i = 0; i < memberNames.size(); i++) {
            String type = memberTypes.get(i);
            int id = memberIds.get(i);
            System.out.println("  #" + id + " " + memberNames.get(i) + " (" + type + ")"
                    + " - " + String.format("%.0f", getMonthlyPrice(type)) + " kr./md."
                    + ", " + getActiveBookingCount(id) + "/" + getMaxBookings(type) + " aktive bookinger");
        }
    }

    // ---------- Træningstimer ----------

    public boolean addSession(String title, String instructor, int capacity) {
        if (capacity < 1) {
            System.out.println("En træningstime skal have mindst 1 plads.");
            return false;
        }
        if (findSessionIndex(title) != -1) {
            System.out.println("Træningstimen " + title + " findes allerede.");
            return false;
        }
        sessionTitles.add(title);
        sessionInstructors.add(instructor);
        sessionCapacities.add(capacity);
        sessionParticipantCounts.add(0);
        return true;
    }

    public int findSessionIndex(String title) {
        for (int i = 0; i < sessionTitles.size(); i++) {
            if (sessionTitles.get(i).equals(title)) {
                return i;
            }
        }
        return -1;
    }

    public void printSession(int index) {
        int count = sessionParticipantCounts.get(index);
        int capacity = sessionCapacities.get(index);
        System.out.println(sessionTitles.get(index) + " med " + sessionInstructors.get(index)
                + " - " + count + "/" + capacity + " tilmeldt"
                + " (" + (capacity - count) + " ledige pladser)");
    }

    public void printAllSessions() {
        System.out.println("Træningstimer i " + centerName + ":");
        for (int i = 0; i < sessionTitles.size(); i++) {
            System.out.print("  ");
            printSession(i);
        }
    }

    public void printAvailableSessions() {
        System.out.println("Træningstimer med ledige pladser:");
        for (int i = 0; i < sessionTitles.size(); i++) {
            if (sessionParticipantCounts.get(i) < sessionCapacities.get(i)) {
                System.out.print("  ");
                printSession(i);
            }
        }
    }

    // ---------- Bookinger ----------

    public int findActiveBookingIndex(int memberId, String sessionTitle) {
        for (int i = 0; i < bookingMemberIds.size(); i++) {
            if (bookingMemberIds.get(i) == memberId
                    && bookingSessionTitles.get(i).equals(sessionTitle)
                    && bookingActive.get(i)) {
                return i;
            }
        }
        return -1;
    }

    public boolean bookSession(int memberId, String sessionTitle) {
        int memberIndex = findMemberIndex(memberId);
        int sessionIndex = findSessionIndex(sessionTitle);
        if (memberIndex == -1 || sessionIndex == -1) {
            System.out.println("Medlem eller træningstime findes ikke.");
            return false;
        }

        String name = memberNames.get(memberIndex);
        String type = memberTypes.get(memberIndex);

        if (findActiveBookingIndex(memberId, sessionTitle) != -1) {
            System.out.println("Bookingen blev afvist.");
            System.out.println(name + " har allerede booket " + sessionTitle + ".");
            return false;
        }
        if (sessionParticipantCounts.get(sessionIndex) >= sessionCapacities.get(sessionIndex)) {
            System.out.println("Bookingen blev afvist.");
            System.out.println(sessionTitle + " er fuldt booket.");
            return false;
        }
        if (getActiveBookingCount(memberId) >= getMaxBookings(type)) {
            System.out.println("Bookingen blev afvist.");
            System.out.println(name + " må højst have " + getMaxBookings(type) + " aktive bookinger.");
            return false;
        }

        bookingMemberIds.add(memberId);
        bookingSessionTitles.add(sessionTitle);
        bookingActive.add(true);
        sessionParticipantCounts.set(sessionIndex, sessionParticipantCounts.get(sessionIndex) + 1);

        System.out.println("Bookingen er gennemført.");
        System.out.println(name + " er nu tilmeldt " + sessionTitle + ".");
        return true;
    }

    public boolean cancelBooking(int memberId, String sessionTitle) {
        int memberIndex = findMemberIndex(memberId);
        int sessionIndex = findSessionIndex(sessionTitle);
        if (memberIndex == -1 || sessionIndex == -1) {
            System.out.println("Medlem eller træningstime findes ikke.");
            return false;
        }

        String name = memberNames.get(memberIndex);
        int bookingIndex = findActiveBookingIndex(memberId, sessionTitle);
        if (bookingIndex == -1) {
            System.out.println("Afmeldingen blev afvist.");
            System.out.println(name + " har ingen aktiv booking af " + sessionTitle + ".");
            return false;
        }

        bookingActive.set(bookingIndex, false);
        sessionParticipantCounts.set(sessionIndex, sessionParticipantCounts.get(sessionIndex) - 1);

        System.out.println("Afmeldingen er gennemført.");
        System.out.println(name + " er nu afmeldt " + sessionTitle + ".");
        return true;
    }

    public void printBookings(int memberId) {
        int memberIndex = findMemberIndex(memberId);
        if (memberIndex == -1) {
            System.out.println("Medlem " + memberId + " findes ikke.");
            return;
        }
        String name = memberNames.get(memberIndex);
        System.out.println("Aktive bookinger for " + name + ":");
        if (getActiveBookingCount(memberId) == 0) {
            System.out.println("  (ingen aktive bookinger)");
            return;
        }
        for (int i = 0; i < bookingMemberIds.size(); i++) {
            if (bookingMemberIds.get(i) == memberId && bookingActive.get(i)) {
                String title = bookingSessionTitles.get(i);
                String instructor = sessionInstructors.get(findSessionIndex(title));
                System.out.println("  " + name + " -> " + title + " med " + instructor + " (aktiv)");
            }
        }
    }


    // ---------- Brugergrænseflade (menu i konsollen) ----------

    public void runMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("===== " + centerName + " =====");
            System.out.println("1. Vis alle medlemmer");
            System.out.println("2. Vis alle træningstimer");
            System.out.println("3. Vis træningstimer med ledige pladser");
            System.out.println("4. Opret medlem");
            System.out.println("5. Opret træningstime");
            System.out.println("6. Book en træningstime");
            System.out.println("7. Afmeld en træningstime");
            System.out.println("8. Vis et medlems aktive bookinger");
            System.out.println("0. Afslut");
            int choice = readInt(scanner, "Vælg: ");
            System.out.println();

            if (choice == 1) {
                printAllMembers();
            } else if (choice == 2) {
                printAllSessions();
            } else if (choice == 3) {
                printAvailableSessions();
            } else if (choice == 4) {
                String name = readText(scanner, "Navn: ");
                int memberId = readInt(scanner, "Medlemsnummer: ");
                String type = readText(scanner, "Medlemstype (Basic/Premium): ");
                if (addMember(name, memberId, type)) {
                    System.out.println(name + " er oprettet som " + type + "-medlem.");
                }
            } else if (choice == 5) {
                String title = readText(scanner, "Titel: ");
                String instructor = readText(scanner, "Instruktør: ");
                int capacity = readInt(scanner, "Antal pladser: ");
                if (addSession(title, instructor, capacity)) {
                    System.out.println(title + " er oprettet.");
                }
            } else if (choice == 6) {
                printAllMembers();
                int memberId = readInt(scanner, "Medlemsnummer: ");
                printAvailableSessions();
                String title = readText(scanner, "Træningstime: ");
                bookSession(memberId, title);
            } else if (choice == 7) {
                int memberId = readInt(scanner, "Medlemsnummer: ");
                printBookings(memberId);
                String title = readText(scanner, "Træningstime der skal afmeldes: ");
                cancelBooking(memberId, title);
            } else if (choice == 8) {
                int memberId = readInt(scanner, "Medlemsnummer: ");
                printBookings(memberId);
            } else if (choice == 0) {
                running = false;
                System.out.println("Farvel!");
            } else {
                System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    // Bliver ved med at spørge, indtil brugeren skriver et helt tal
    public static int readInt(Scanner scanner, String prompt) {
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
    public static String readText(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String text = scanner.nextLine().trim();
        while (text.isEmpty()) {
            System.out.print("Feltet må ikke være tomt: ");
            text = scanner.nextLine().trim();
        }
        return text;
    }

    public void addSampleData() {
        addMember("Sara", 101, "Basic");
        addMember("Ali", 102, "Premium");
        addMember("Emma", 103, "Basic");

        addSession("Yoga", "Mette", 2);
        addSession("Spinning", "Jonas", 1);
        addSession("Crossfit", "Lars", 10);
        addSession("Pilates", "Nadia", 5);
    }

    // ---------- Test ----------

    public static void main(String[] args) {
        ClassIntroduction center = new ClassIntroduction("PowerGym");
        center.addSampleData();
        center.addMember("Ole", 104, "Studerende"); // Afvises, typen findes ikke

        printHeader("Medlemmer og træningstimer");
        center.printAllMembers();
        System.out.println();
        center.printAllSessions();

        printHeader("Test 1: Begge medlemstyper kan booke");
        center.bookSession(101, "Yoga");
        center.bookSession(102, "Spinning");

        printHeader("Test 2: En fuld træningstime afviser flere bookinger");
        center.bookSession(103, "Spinning");
        center.bookSession(102, "Yoga");
        center.bookSession(103, "Yoga");

        printHeader("Test 3: Et medlem kan ikke booke samme time to gange");
        center.bookSession(101, "Yoga");

        printHeader("Test 4: Et medlem kan ikke overskride sin bookinggrænse");
        center.bookSession(101, "Crossfit");
        center.bookSession(101, "Pilates"); // Sara er Basic: max 2
        System.out.println();
        center.bookSession(102, "Crossfit");
        center.bookSession(102, "Pilates"); // Ali er Premium: max 5

        printHeader("Test 5: Aktive bookinger kan vises");
        center.printBookings(101);
        center.printBookings(102);

        printHeader("Test 6: En booking kan annulleres");
        System.out.print("Før: ");
        center.printSession(center.findSessionIndex("Yoga"));
        center.cancelBooking(101, "Yoga");
        center.cancelBooking(101, "Yoga");

        printHeader("Test 7: Den ledige plads kommer tilbage efter annullering");
        System.out.print("Efter: ");
        center.printSession(center.findSessionIndex("Yoga"));
        center.printAvailableSessions();
        System.out.println();
        center.bookSession(103, "Yoga");

        // Start menuen med de data, testene har lavet
        Scanner scanner = new Scanner(System.in);
        center.runMenu(scanner);
    }

    private static void printHeader(String title) {
        System.out.println();
        System.out.println("===== " + title + " =====");
    }
}
