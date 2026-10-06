package composition;

import java.util.ArrayList;
import java.util.EnumMap;

public class Member {
    private String name;
    private int memberId;
    private ArrayList<Booking> bookings; // HAS-A: et medlem har bookinger
    private String membershipType;

    public Member(String name, int memberId, String membershipType){
        this.name=name;
        this.memberId=memberId;
        this.membershipType=membershipType;
        this.bookings=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }


    //nr. 19
    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    //nr. 20

    public int getActiveBookingCount(){
        int count=0;

        for (Booking b: bookings){
            if (b.isActive()) {
                count++;
            }
        }
        return count;
    }

    //nr.22

    public boolean hasReachedMaxBookings(){
        return getActiveBookingCount() >= getMaxBookings();
    }

    //nr.21
    /*public int getMaxBookings(){
        return 3;
    }*/


    public int getMaxBookings() {
        if (membershipType.equals("Basic")) {
            return 2;
        } else if (membershipType.equals("Premium")) {
            return 5;
        }
        return 0;
    }





    //nr.23: Finder medlemmets aktive booking af en bestemt træningstime, eller null
    public Booking findActiveBooking(TrainingSession session){
        for (Booking b: bookings){
            if(b.isActive() && b.getTrainingSession() == session)
                return b;
        }
        return null;
    }

    //nr.24:
    public boolean hasBooked(TrainingSession session) {
        return findActiveBooking(session) != null;
    }

    //nr.25
    public void printBookings(){
        System.out.println("Aktive bookings for "+name +": ");
        if(getActiveBookingCount()==0){
            System.out.println("ingen aktive bookinger");
            return;
        }

        for (Booking b: bookings){
            if (b.isActive()){
                System.out.println("\n");
                b.printBooking();
            }
        }
    }

    //nr.26
    public void printMember() {
        System.out.println("#" + memberId + " " + name +  ": "
                + getActiveBookingCount() + "/" + getMaxBookings() + " aktive bookinger. MemberType: "+ membershipType +"\n");
    }



}
