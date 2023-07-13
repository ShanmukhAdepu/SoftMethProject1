package src;

/**
This is the Family class that is the subclass of Member and holds every attribute required for Family members of a gym.
With this class a user will be able to access a members first name, last name, date of birth,
location, number of guest passes, and date of gym member expiration.

@author Shanmukh Adepu

@author Manvay Dangi

@since  10/16/2022
 */
public class Family extends Member{
    
    /**
    Stores the amount of guest passes a member of the Family Membership has
     */
    private int guestPass;
    
    /**
     This Family constructor creates a Member with a Family membership by intaking the members first name, 
            last name, date of birth, date of membership expiration, number of guest passes, 
            and location of the given gym
    
     @param fname This is the first name of the member that holds a Family membership

     @param lname This is the last name of the member that holds a Family membership

     @param dob This is the date of birth of the member that holds a Family membership

     @param expire This is the membership expiration date of the member that holds a Family membership

     @param location This is the location of the gym that the member with the Family membership holds their 
                        membership at

     @param numGuestPass This is the amount of guest passes that the member with the Family membership holds
     */
    public Family(String fname, String lname, Date dob, Date expire, Location location, int numGuestPass){
        super(fname, lname, dob, expire, location);
        guestPass = numGuestPass;
    }

    /**
     This membershipFee() method returns the membership fee

     @return this returns the membership fee
     */
    @Override
    public double membershipFee(){
        double oneTimeFee = 29.99;
        double monthlyPayment = 59.99;
        double quarter = 4;
        double totalFee = (monthlyPayment * quarter) + oneTimeFee;
        return totalFee;
    }

    /**
     This getAmountOfGuestPass() method returns the amount of guest passes the Family member is allowed to bring

     @return this returns the amount of guest passes the Family member is allowed to bring
     */
    public int getAmountOfGuestPass(){
        return this.guestPass;
    }

    public void setGuestPass(int guestPass) {
        this.guestPass = guestPass;
    }

    /**
     This toString() method returns the information about the member with a family membership.
     This information includes the first and last names, date of birth, date of expiration, membership location,
        and the amount of guest passes the member has remaining.

     @return this returns a string with the members first and last name, date of birth, date of expiration, gym location
     */
    @Override
    public String toString(){
        String fullName = getFname() + " " + getLname() + ", ";
        String dateOfBirth = "DOB: " + getDob() + ", ";
        Date today = new Date();
        String expiration;
        if (today.compareTo(getExpire()) == -1){
            expiration = "Membership expired " + getExpire() + ", ";
        }else{
            expiration = "Membership expires " + getExpire() + ", ";
        }
        String loc = "" + getLocation();
        String ans = fullName + dateOfBirth + expiration + "Location: "
                + loc.toUpperCase() +", "
                + getLocation().getZipCode() + ", "
                + getLocation().getCounty().toUpperCase()
                + ",(Family) guest-pass remaining: " + this.getAmountOfGuestPass();
        return ans;
    }

    /**
     This getExpire() method returns the expiration date of the Family members gym membership

     @return this returns the expiration date of the Family members gym membership
     */
    public Date getExpire(){
        return super.getExpire();
    }

    /**
     This getLocation() method returns the location of the Family members gym

     @return this returns the location of the Family members gym
     */
    public Location getLocation(){
        return super.getLocation();
    }

    /**
     This getFname() method returns the first name of the Family member

     @return this returns the first name of the Family member
     */
    public String getFname() {
        return super.getFname();
    }

    /**
     This getLname() method returns the last name of the Family member

     @return this returns the last name of the Family member
     */
    public String getLname() {
        return super.getLname();
    }

    /**
     This getDOB() method returns the date of birth of the Family member

     @return this returns the date of birth of the Family member
     */
    public Date getDob() {
        return super.getDob();
    }
}
