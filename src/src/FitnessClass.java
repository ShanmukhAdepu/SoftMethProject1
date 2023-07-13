package src;

/**
 This is the FitnessClass class which holds every attribute required for a FitnessClass.

 With this class a user will be able to create a class via Instantiation and add and remove members with built in validation.

 @author Shanmukh Adepu

 @author Manvay Dangi

 @since  10/3/2022
 */
public class FitnessClass {

    /**
     Stores the participants for each class, the time of each class, the teachers name, and the members name.
     */
    private Member[] Participants;
    private Member[] Guests;
    private Time time;
    private String teacher;
    private String name;

    private Location location;

    private String mfname;
    private String mlname;
    private Date mdob;

    private MemberDatabase mdb;


    /**
     * This is the constructor for a Fitness Class. It also instantiates
     *
     * @param name This is the name of the Fitness Class
     * @param teacher This is the teacher of the class
     * @param time This is the time the class is hosted during the day
     */
    public FitnessClass(String name,String teacher,Time time,Location location){
        this.name = name;
        this.teacher = teacher;
        this.time = time;
        this.Participants = new Member[4];
        this.location = location;
        this.Guests = new Member[4];
    }
    public FitnessClass(String name,String teacher,Location loc,String mfname,String mlname,Date mdob,MemberDatabase mdb){
        this.name = name;
        this.teacher = teacher;
        this.location = loc;
        this.Participants = new Member[4];
        this.mfname = mfname;
        this.mlname = mlname;
        this.mdob = mdob;
        this.mdb = mdb;
        this.Guests = new Member[4];
    }


    /**
     * This class checks members into the class
     * @param member the member to check in
     * @return true if the member is checked in
     * @throws IllegalArgumentException if the member passed in does not pass validation such as not being in the database,
     * an expired membership, or already being checked into the class
     */
    public boolean checkIn(Member member){
        if (add(member)){

            return true;
        }
        return false;
    }
    public boolean checkInGuest(Member member){
        if (addGuest(member)){
            if(member instanceof Premium){
                Premium f = (Premium) member;
                f.setGuestPass(f.getAmountOfGuestPass()-1);
            }else if(member instanceof Family){
                Family p = (Family) member;
                p.setGuestPass(p.getAmountOfGuestPass()-1);
            }
            return true;
        }
        return false;
    }

    /**
     * This helper method checks if a member is already checked in
     * @param member member to check
     * @return true if the member is in the db or false if they are not
     *
     */
    public boolean check(Member member){
        if(this.find(member,this.Participants) != -1){
            return true;
        }
        return false;
    }

    /**
     * This method drops the member from the fitness class
     * @param member member to be removed
     * @return true if removal is successful
     * @throws IllegalArgumentException if the individual is not checked into the database
     */
    public boolean remove(Member member){
        int x = this.find(member,this.Participants);
            for(int i = x;i < this.Participants.length;i++ ){
                Participants[i] = null;
                if(i != Participants.length-1){
                    Member temp = Participants[i];
                    Participants[i] = Participants[i+1];
                    Participants[i+1] = temp;
                }
            }
            return true;
    }
    public boolean removeGuest(Member member){
        int x = this.find(member,this.Guests);
        for(int i = x;i < this.Guests.length;i++ ){
            Guests[i] = null;
            if(i != Guests.length-1){
                Member temp = Guests[i];
                Guests[i] = Guests[i+1];
                Guests[i+1] = temp;
            }
        }
        if(member instanceof Family){
            Family f = (Family) member;
            f.setGuestPass(f.getAmountOfGuestPass()+1);
        }else if(member instanceof Premium){
            Premium p = (Premium) member;
            p.setGuestPass(p.getAmountOfGuestPass()+1);
        }
        return true;
    }

    /**
     * toString method for the Class
     * @return the string representation of this class
     */
    public String toString(){
        String loc = "" + location;
        if(isNull(Participants) && isNull(Guests)){
            return name + " - " + teacher + " " + time + ", " + loc.toUpperCase();
        }else{
            System.out.println(name + " - " + teacher + " " + time + loc.toUpperCase());
            print();
            return "";
        }
    }

    /**
     * Method to search the member database during checkin
     * @param member the member to search
     * @param mlist the database to search
     * @return -1 if not found otherwise the index of the member in the database
     */
    private int find(Member member,Member[] mlist) {
        for (int i = 0; i < mlist.length;i++){
            if(member.equals(mlist[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Helper method to dynamically grow the list of participants
     */
    private void grow() {
        Member [] grown = new Member[Participants.length+4];
        for(int i = 0; i < Participants.length;i++){
            grown[i] = this.Participants[i];
        }
        this.Participants = grown;
    }
    private void growGuest() {
        Member [] grown = new Member[Guests.length+4];
        for(int i = 0; i < Guests.length;i++){
            grown[i] = this.Guests[i];
        }
        this.Guests = grown;
    }

    /**
     * Helper method to checkIn to add members to the list of participants
     * @param member the member to add
     * @return true when the member has been added
     */
    private boolean add(Member member) {
        if (this.find(member,this.Participants) != -1){
            return false;
        }
        if (this.Participants[Participants.length-1] == null){
            for (int i = 0;i < Participants.length;i++){
                if(this.Participants[i] == null){
                    this.Participants[i] = member;
                    return true;
                }
            }
        }else{
            grow();
            return add(member);
        }
        return false;
    }
    private boolean addGuest(Member member) {
        if (this.Guests[Guests.length-1] == null){
            for (int i = 0;i < Guests.length;i++){
                if(this.Guests[i] == null){
                    this.Guests[i] = member;
                    return true;
                }
            }
        }else{
            growGuest();
            return addGuest(member);
        }
        return false;
    }

    /**
     * Helper method that determines if the participant list is null
     * @return true if it is or false if it is not
     */
    private boolean isNull(Member[] Participants){
        boolean is = true;
        for (int i = 0; i < Participants.length; i++) {
            if(Participants[i] != null){
                is = false;
                return is;
            }
        }
        return is;
    }

    /**
     * Helper method for toString to help print participants
     */
    public void print(){
        if((!isNull(Participants))) {
            System.out.println("-Participants-");
            for (int i = 0; i < Participants.length; i++) {
                if (Participants[i] != null)
                    System.out.println("   " + Participants[i]);
            }
        }
        if((!isNull(Guests))) {
            System.out.println("-guests-");
            for (int i = 0; i < Guests.length; i++) {
                if (Guests[i] != null)
                    System.out.println("   " + Guests[i]);
            }
        }
    }

    public String getName() {
        return name;
    }
    public boolean equals(FitnessClass other){
        if(this.name.equalsIgnoreCase(other.name) && this.teacher.equalsIgnoreCase(other.teacher) && this.location.toString().equalsIgnoreCase(other.location.toString())){
            return true;
        }
        return false;
    }

    public Time getTime() {
        return time;
    }

    public Location getLocation() {
        return location;
    }

    public String getTeacher() {
        return teacher;
    }

    public Date getMdob() {
        return mdob;
    }

    public String getMfname() {
        return mfname;
    }

    public String getMlname() {
        return mlname;
    }

    public MemberDatabase getMdb() {
        return mdb;
    }

}