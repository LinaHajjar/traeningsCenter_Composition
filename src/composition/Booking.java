package composition;

public class Booking {
    private Member member;                   // HAS-A: en booking tilhører et medlem
    private TrainingSession trainingSession; // HAS-A: en booking gælder en træningstime
    private boolean active;

    public Booking (Member member, TrainingSession trainingSession){
        this.member=member;
        this.trainingSession=trainingSession;
        this.active=true;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TrainingSession getTrainingSession() {
        return trainingSession;
    }

    public void setTrainingSession(TrainingSession trainingSession) {
        this.trainingSession = trainingSession;
    }


    //TODO cancel()
    // nr.31: Annullerer bookingen og fjerner medlemmet fra træningstimens deltagerliste
    public void cancel() {
        if (active) {
            active = false;
            trainingSession.removeParticipant(member);
        }
    }



    public boolean isActive(){
        return active;
    }

/*
    //TODO printBooking(); nr. 24
    public void printBooking() {
        //String status = active ? "aktiv" : "annulleret";
        System.out.println(member.getName() + " -> " + trainingSession.getTitle()
                //+ " med " + trainingSession.getInstructor() + " (" + status + ")");
                + " med " + trainingSession.getInstructor());
    }
    */

    public void printBooking() {
        String status;

        if (active) {
            status = "aktiv";
        } else {
            status = "annulleret";
        }

        System.out.println(member.getName() + " -> "
                + trainingSession.getTitle()
                + " med " + trainingSession.getInstructor()
                + " (" + status + ")");
    }


}
