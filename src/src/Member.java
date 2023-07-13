package src;

/**
This is the Member class which holds every attribute required for members of a gym.
With this class a user will be able to access a members first name, last name, date of birth,
location, and date of gym member expiration.

@author Shanmukh Adepu

@author Manvay Dangi

@since  10/16/2022
 */

public class Member implements Comparable<Member> {

    /**
     Stores the first name, last name, date of birth, membership expiration date, and gym location
     */
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    /**
    This Member constructor creates a Member by intaking the members first name, last name, date of birth,
            date of membership expiration, and location of the given gym
    
    @param fname This is the first name of the member

    @param lname This is the last name of the member

    @param dob This is the date of birth of the member in the form mm/dd/yyyy

    @param expire This is the expiration date of the members membership in the form mm/dd/yyyy

    @param location This is the location of the members gym
    
    */
    public Member(String fname, String lname, Date dob, Date expire, Location location){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }
    /**
    This toString() function takes in the members full name, date of birth, membership expiration date, 
    and location in order to return a string.

    @return a string in the form of "First name Last name, DOB: mm/dd/yyyy, Membership expires mm/dd/yyyy, 
            Location: location"
    */

    public String toString() {
        String fullName = fname + " " + lname + ", ";
        String dateOfBirth = "DOB: " + dob + ", ";
        Date today = new Date();
        String expiration;
        if (today.compareTo(this.expire) == -1){
             expiration = "Membership expired " + expire + ", ";
        }else{
             expiration = "Membership expires " + expire + ", ";
        }
        String loc = "" + location;
        String ans = fullName + dateOfBirth + expiration + "Location: "
                + loc.toUpperCase() +", "
                + getLocation().getZipCode() + ", "
                + getLocation().getCounty().toUpperCase();
        return ans;
    }

    /**
    This equals() method compares the first names, last names, and date of birth of two members 
            to see if both members are the same

    @param obj this is the member in the function that is being compared to the first
                and holds all necessary member attributes

    @return this function returns true if the members have the same date of birth, last name, and first name, 
            and false otherwise
    */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Member){
            Member mem = (Member) obj;
            String thisFirstNormal = this.fname.toLowerCase();
            String thisLastNormal = this.lname.toLowerCase();
            String memFirstNormal = mem.fname.toLowerCase();
            String memLastNormal = mem.lname.toLowerCase();
            return ((memLastNormal.equals(thisLastNormal)) && (thisFirstNormal.equals(memFirstNormal)) &&
                    (this.dob.equals(mem.dob)));
        }else{
            return false;
        }
    }

    /**
    This compareTo() method compares two members to see which member is alphabetically inferior to the other

    @param member this is the second member that is being compared and holds the first and last name of that 
                member

    @return this function returns a 1 if this is alphabetically superior to member and -1 otherwise
    */
    @Override
    public int compareTo(Member member){ //used to sort names
        int len;
        int thisIsInferior = 1;
        int memIsInferior = -1;
        if(member.lname.equals(this.lname)){
            if(this.fname.length() <= member.fname.length()){
                len = this.fname.length();
            }else{
                len = member.fname.length();
            }
            for(int j = 0; j < len; j++){
                if(Character.compare(this.fname.charAt(j), 
                    member.fname.charAt(j)) > 0){
                    return thisIsInferior;
                }else if(Character.compare(this.fname.charAt(j), 
                        member.fname.charAt(j)) < 0){
                    return memIsInferior;
                }
            }
        }else{
            if(this.lname.length() <= member.lname.length()){
                len = this.lname.length();
            }else{
                len = member.lname.length();
            }
            for(int i = 0; i < len; i++){
                if(Character.compare(this.lname.charAt(i), 
                        member.lname.charAt(i)) > 0){
                    return thisIsInferior;
                }else if (Character.compare(this.lname.charAt(i), 
                        member.lname.charAt(i)) < 0){
                    return memIsInferior;
                }
            }
        }
        return 0;
    }

    /**
    This getExpire() method returns the expiration date of the members gym membership

    @return this returns the membership expiration date
    */
    public Date getExpire(){
        return this.expire;
    }

    /**
     This getLocation() method returns the location of the members gym.

     @return this returns the members gym
     */
    public Location getLocation(){
        return this.location;
    }

    /**
     This getFname() method returns the first name of the member

     @return this returns the members first name
     */
    public String getFname() {
        return fname;
    }

    /**
     This getLname() method returns the last name of the member

     @return this returns the members last name
     */
    public String getLname() {
        return lname;
    }

    /**
     This getDob() method returns the members date of birth

     @return this returns the members date of birth
     */
    public Date getDob() {
        return dob;
    }

    /**
     This membershipFee() method returns the membership fee

     @return this returns the membership fee
     */
    public double membershipFee(){
        double oneTimeFee = 29.99;
        double monthlyPayment = 39.99;
        double totalFee = monthlyPayment * 3;
        totalFee+=oneTimeFee;
        return totalFee;
    }

    /**
     This test bed tests the compareto() method within the Member.java class
     */
    public static void main (String[] args){

        Member manvayTest12 = new Member("Manvay", "Dangi", new Date("01/06/2002", 1),
                                    new Date ("01/06/2050", 2), Location.Bridgewater);
        Member shanmukhTest12 = new Member("Shanmukh", "Adepu", new Date("01/06/2002", 1),
                new Date ("01/06/2050", 2), Location.Bridgewater);
        System.out.println("The given output should be 1 since Manvay has an alphabetically inferior " +
                            "last name to Shanmukh. The output is: " + manvayTest12.compareTo(shanmukhTest12)); //Test Case 1
        System.out.println("The given output should be -1 since Manvay has an alphabetically inferior " +
                            "last name to Shanmukh. The output is: " + shanmukhTest12.compareTo(manvayTest12)); //Test Case 2

        Member manvayTest3 = new Member("Manvay", "Dangi", new Date("01/06/2002", 1),
                                        new Date ("01/06/2050", 2), Location.Bridgewater);
        Member shanmukhTest3 = new Member("Shanmukh", "Dangi", new Date("01/06/2002", 1),
                new Date ("01/06/2050", 2), Location.Bridgewater);
        System.out.println("The given output should be 1 since Shanmukh has an alphabetically inferior first name" +
                            "to Manvay and both share the same last name. The output is: "
                            + shanmukhTest3.compareTo(manvayTest3)); //Test Case 3

        Member manvayTest4 = new Member("Manvay", "Adepu", new Date("01/06/2002", 1),
                new Date ("01/06/2050", 2), Location.Bridgewater); //Test case 4
        Member shanmukhTest4 = new Member("Shanmukh", "Adepu", new Date("01/06/2002", 1),
                new Date ("01/06/2050", 2), Location.Bridgewater);
        System.out.println("The given output should be 1 since Shanmukh has an alphabetically inferior first name" +
                "to Manvay and both share the same last name. The output is: "
                + manvayTest3.compareTo(shanmukhTest3)); //Test Case 4

    }
}
