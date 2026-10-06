package composition;

import java.util.ArrayList;

public class FitnessCenter {
    private String name;
    private ArrayList<Member> members;           // HAS-A: et træningscenter har medlemmer
    private ArrayList<TrainingSession> sessions; // HAS-A: et træningscenter har træningstimer

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FitnessCenter (String name){
        this.name=name;
        this.members=new ArrayList<>();
        this.sessions=new ArrayList<>();
    }


    public void printAllMembers() {
        System.out.println("Medlemmer i " + name + ":");
        for (Member member : members) {
            member.printMember();
        }
    }

    public void printAllSessions() {
        System.out.println("Træningstimer i " + name + ":");
        for (TrainingSession session : sessions) {
            session.printSession();
            System.out.println();
        }
    }


    public void printAvailableSessions() {
        System.out.println("Træningstimer med ledige pladser:");
        for (TrainingSession session : sessions) {
            if (session.hasAvailableSpace()) {
                session.printSession();
                System.out.println();
            }
        }
    }



    //nr. 34: Finder et medlem ud fra medlemsnummeret, eller null
    public Member findMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }


    //NR. 35
    public boolean addMember(Member member) {
        if (findMember(member.getMemberId()) != null) {
            System.out.println("Medlemsnummer " + member.getMemberId() + " findes allerede.");
            return false;
        }
        members.add(member);
        return true;
    }

    //Nr 36:Finder en træningstime ud fra titlen, eller null
    public TrainingSession findSession(String title) {
        for (TrainingSession session : sessions) {
            if (session.getTitle().equalsIgnoreCase(title)) {
                return session;
            }
        }
        return null;
    }


    //Nr 37:
    public boolean addSession(TrainingSession session) {
        if (findSession(session.getTitle()) != null) {
            System.out.println("Træningstimen " + session.getTitle() + " findes allerede.");
            return false;
        }
        sessions.add(session);
        return true;
    }


    //Del 6
    public boolean bookSession(Member member, TrainingSession session) {
        if (member.hasBooked(session)) {
            System.out.println("Bookingen blev afvist.");
            System.out.println(member.getName() + " har allerede booket " + session.getTitle() + ".");
            return false;
        }
        if (!session.hasAvailableSpace()) {
            System.out.println("Bookingen blev afvist.");
            System.out.println(session.getTitle() + " er fuldt booket.");
            return false;
        }

        if (member.hasReachedMaxBookings()) {
            System.out.println("Bookingen blev afvist.");
            System.out.println(member.getName() + " må højst have "
                    + member.getMaxBookings() + " aktive bookinger.");
            return false;
        }

        Booking booking = new Booking(member, session);
        member.addBooking(booking);
        session.addParticipant(member);

        System.out.println("Bookingen er gennemført.");
        System.out.println(member.getName() + " er nu tilmeldt " + session.getTitle() + ".");
        return true;
    }

    public boolean cancelBooking(Member member, TrainingSession session) {
        Booking booking = member.findActiveBooking(session);
        if (booking == null) {
            System.out.println("Afmeldingen blev afvist.");
            System.out.println(member.getName() + " har ingen aktiv booking af " + session.getTitle() + ".");
            return false;
        }
        booking.cancel();
        System.out.println("Afmeldingen er gennemført.");
        System.out.println(member.getName() + " er nu afmeldt " + session.getTitle() + ".");
        return true;
    }

}
