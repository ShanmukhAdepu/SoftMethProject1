package src;

/**
This is the Premium class that is the subclass of Family and holds every attribute required for Premium members of a gym.
With this class a user will be able to access a members first name, last name, date of birth,
location, number of guest passes, and date of gym member expiration.

@author Shanmukh Adepu

@author Manvay Dangi

@since  10/16/2022
 */

public class Premium extends Family{

    /**
    Stores the amount of guest passes a member of the Premium Membership has
     */
    private int guestPass;
    
    /**
     This Premium constructor creates a Member with a Premium membership by intaking the members first name, 
            last name, date of birth, date of membership expiration, number of guest passes, 
            and location of the given gym
    
     @param fname This is the first name of the member that holds a Premium membership

     @param lname This is the last name of the member that holds a Premium membership

     @param dob This is the date of birth of the member that holds a Premium membership

     @param expire This is the membership expiration date of the member that holds a Premium membership

     @param location This is the location of the gym that the member with the Premium membership holds their 
                        membership at

     @param numGuestPass This is the amount of guest passes that the member with the Premium membership holds
     */
    public Premium(String fname, String lname, Date dob, Date expire, Location location, int numGuestPass){
        super(fname, lname, dob, expire, location, numGuestPass);
        guestPass = numGuestPass;
    }

    /**
     This membershipFee() method returns the membership fee

     @return this returns the membership fee
     */
    @Override
    public double membershipFee(){
        double totalFee = 59.99;
        double monthsInAYear = 12;
        double monthOff = 1;
        double yearly = monthsInAYear - monthOff;
        return yearly*totalFee;
    }

    /**
     This getAmountOfGuestPass() method returns the amount of guest passes the Premium member is allowed to bring

     @return this returns the amount of guest passes the Premium member is allowed to bring
     */
    @Override
    public int getAmountOfGuestPass(){
        return this.guestPass;
    }

    @Override
    public void setGuestPass(int guestPass) {
        this.guestPass = guestPass;
    }

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
                + ",(Premium) Guest-pass remaining: " + this.getAmountOfGuestPass();
        return ans;
    }
    /**
     This getExpire() method returns the expiration date of the Premium members gym membership

     @return this returns the expiration date of the Premium members gym membership
     */
    public Date getExpire(){
        return super.getExpire();
    }

    /**
     This getLocation() method returns the location of the Premium members gym

     @return this returns the location of the Premium members gym
     */
    public Location getLocation(){
        return super.getLocation();
    }

    /**
     This getFname() method returns the first name of the Premium member

     @return this returns the first name of the Premium member
     */
    public String getFname() {
        return super.getFname();
    }

    /**
     This getLname() method returns the last name of the Premium member

     @return this returns the last name of the Premium member
     */
    public String getLname() {
        return super.getLname();
    }

    /**
     This getDOB() method returns the date of birth of the Premium member

     @return this returns the date of birth of the Premium member
     */
    public Date getDob() {
        return super.getDob();
    }
}

