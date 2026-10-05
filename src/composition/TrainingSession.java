package composition;

import java.util.ArrayList;

public class TrainingSession {
    private String title;
    private String instructor;
    private int capacity;
    private ArrayList<Member> participants; // HAS-A: en træningstime har deltagere

    public TrainingSession (String title, String instructor, int capacity){
        this.title=title;
        this.instructor=instructor;
        this.capacity=capacity;
        this.participants=new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    //nr.8
    public boolean hasAvailableSpace() {
        return participants.size() < capacity;
    }

    /*
    public boolean hasAvailableSpace() {
       if (participants.size() < capacity){
           return true;
       }
       else return false;
    }*/

    //nr.9
    public int getAvailableSpaces() {

        return (capacity - participants.size());
    }

    //nr.10: Tilføjer kun medlemmet, hvis der er en ledig plads og medlemmet ikke allerede deltager
    public boolean addParticipant(Member member){
        if (!hasAvailableSpace()|| participants.contains(member)){ //if no place or if he is already in the hold
            return false; //not added
        }
        else participants.add(member);
        return true;
    }

    //nr.11: Returnér resultatet fra participants.remove(member).
    public boolean removeParticipant(Member member) {
        return participants.remove(member);
    }

    //nr.12: printSession:
    public void printSession(){
        System.out.println(title + " med: "+ instructor
                +"\n"
                + "antal deltagere: "+ participants.size() +"\n"
                + "ledige pladser: " + getAvailableSpaces());
    }

}
