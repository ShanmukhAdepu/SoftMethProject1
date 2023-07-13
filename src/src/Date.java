package src;
import java.util.Calendar;

/**
This Date class implements the Comparable interface and holds the necessary date of births and expiration dates
        of memberships for each member

@author Shanmukh Adepu

@author Manvay Dangi

@since 10/3/2022
*/
public class Date implements Comparable<Date>{

    /**
     Stores the year, month, and day of the date.
     */
    private int year;
    private int month;
    private int day;
    
    /**
    This Date constructor stores today's date.

    */
    public Date(){ //create an object with today's date (see Calendar class)
    
        this.year = Calendar.getInstance().get(Calendar.YEAR);
        this.month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        this.day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    }

    /**
    This date constructor creates a date for the date of birth of a member or an expiration date of a members
            membership. 

    @param date This is the date of the members membership expiration date or their date of birth. This can be in the form of 
                "mm/dd/yyyy", "m/dd/yyyy", "m/d/yyyy", or "mm/d/yyyy".

    @param type This type is a 1 if the date represents a members date of birth and is a 2 if the date represents a members
                membership expiration date.

    @throws IllegalArgumentException if the date of birth is future date or todays date

    @throws IllegalArgumentException if the date of birth shows that the member is not 18 or older

    @throws IllegalArgumentException if the date of birth or expiration date is an illegal calendar date
    */
    public Date(String date, int type){ //take "mm/dd/yyyy" "m/dd/yyyy" "m/d/yyyy" "mm/d/yyyy" and create a src.Date object and an integer n to determine the type of date
        int dob = 1;
        int expiration = 2;
        if(date.equals("")){
            this.year = Calendar.getInstance().get(Calendar.YEAR);
            this.month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            this.day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            if (this.month == 10 || this.month == 11 || this.month == 12){
                this.month = (this.month+3)-12;
                this.year = this.year+1;
                if(!(this.inJanMarMayJulyAugOctDec()) && this.day == 31){
                    this.day = 1;
                    this.month = this.month+1;
                    if(this.month == 13){
                        this.month = 1;
                        this.year = this.year+1;
                    }
                }
            }
        }else if (date.equals(" ")){
            this.year = Calendar.getInstance().get(Calendar.YEAR) + 1;
            this.month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            this.day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        } else {
            parseAndSetDate(date);
            if (this.isValid()) {
                if (!(futurePresentDate())) { //1 means dob
                    if (type == dob) {
                        throw new IllegalArgumentException("DOB " + this + ": cannot be today or a future "
                                + "date!");
                    }
                } else {
                    if (!(checkAge())) {
                        if (type == dob) {
                            throw new IllegalArgumentException("DOB " + this + ": must be 18 or older to join!");
                        }
                    }
                }
            } else {
                if (type == dob) {
                    throw new IllegalArgumentException("DOB " + this + ": invalid calendar date!");
                } else if (type == expiration) {
                    throw new IllegalArgumentException("Expiration " + this + ": invalid calendar date!");
                }
            }
        }
    }
    
    /**
    This compareTo() method compares two dates to see which one is earlier than the other

    @param date is the second date that is being compared

    @return returns 1 if the first date is earlier, -1 if the second date is earlier, and
            0 if both dates are the same
    */
    @Override
    public int compareTo(Date date){
        int thisIsGreater = 1;
        int dateIsGreater = -1;
        int same = 0;
        if(this.year <  date.year){ //this year is earlier than date year
            return thisIsGreater;
        }else if(this.year == date.year){
            if(this.month < date.month){ //same year but this month is earlier than date month
                return thisIsGreater;
            }else if (this.month > date.month){
                return dateIsGreater;
            }else if(this.month == date.month){
                if(this.day < date.day){ //same year and month but the day is earlier
                    return thisIsGreater;
                }else if(this.day > date.day){
                    return dateIsGreater;
                }
            }
        }else if(this.year > date.year){
            return dateIsGreater;
        }
        return same;
    }

    /**
    This isValid() function checks to see if the given date can actually be a real date or not

    @return returns true if the date is a valid date and false otherwise
    */
    public boolean isValid(){ //check if a date is a valid calendar date
        int QUADRENNIAL = 4;
        int CENTENNIAL = 100;
        int QUATERCENTENNIAL = 400;
        boolean leapYear = false;
        //this is to see if this is a leapyear or not
        if(this.year % QUADRENNIAL == 0){
            if(this.year % CENTENNIAL == 0){
                if(this.year % QUATERCENTENNIAL == 0){
                    leapYear = true;
                }
            }
        }
        int jan = 1, feb = 2;
        int mar = 3;
        int april = 4;
        int may = 5;
        int june = 6;
        int july = 7;
        int aug = 8;
        int sept = 9;
        int oct = 10;
        int nov = 11;
        int dec = 12;
        if(this.day > 31 || this.day < 1){ //checks validity with the amount of days
            return false;
        }else if(this.month > dec || this.month < jan){
            return false;
        }else if(this.month == feb){
            return inFeb(this, leapYear);
        }else if(this.month == april || this.month == june || this.month == sept
                || this.month == nov){
            return this.inAprJuneSeptNov();
        }else if(this.month == jan || this.month == mar || this.month == may
                || this.month == july || this.month == aug || this.month == oct || this.month == dec){
            return this.inJanMarMayJulyAugOctDec();
        }
        return false;
    }

    /**
    This futurePresentDate() function checks to see if the date is in the future or matches todays date

    @return returns false if the date matches todays date or is in the future and true otherwise
    */
    public boolean futurePresentDate(){
        Date today = new Date();
        if(this.year > today.year){
            return false;
        }else if(this.year == today.year && this.month > today.month){
            return false;
        }else if(this.year == today.year && this.month == today.month && this.day > today.day){
            return false;
        }else if(this.year == today.year && this.month == today.month){
            if(this.day == today.day){
                return false;
            }
        }
        return true;
    }

    /**
    This checkAge() function checks to see if the member is 18 or over 18 using their date of birth

    @return returns true if the user is or over 18 and false otherwise
    */
    public boolean checkAge(){ 
        int legalAdult = 18;
        Date today = new Date();
        if(today.year - this.year > legalAdult) {
            return true;
        }else if(today.year - this.year == legalAdult){
            if(this.month < today.month){
                return true;
            }else if(this.month == today.month){
                if(this.day < today.day){
                    return true;
                }else if(this.day == today.day){
                    return true;
                }
            }
        }
        return false;
    }

    /**
    This inFeb() function checks to see if the given February date is valid

    @param date this is the date that being checked to see if it is a valid February date

    @param leapYear this is true if this date falls within a leapyear and false otherwise

    @return this returns true if the given date is valid for the month of February and false otherwise
    */
    public boolean inFeb(Date date, boolean leapYear){
        int daysInFeb = 28;
        int minDays = 1;
        if(leapYear == true){
            daysInFeb = 29;
            if(date.day > daysInFeb || date.day < minDays){
                return false;
            }else{
                return true;
            }
        }else{
            if(date.day > daysInFeb || date.day < minDays){
                return false;
            }else{
                return true;
            }
        }
    }

    /**
    This inJanMarMayJulyAugOctDec() function checks to see if the given date can exist in January, 
            March, May, July, August, October, or December.

    @return this returns true if the date can exist in January, March, May, July, August, October, 
            or December, and false otherwise
    */
    public boolean inJanMarMayJulyAugOctDec(){
        int daysInJanMarMayJulyAugOctDec = 31;
        if(this.day > daysInJanMarMayJulyAugOctDec){
            return false;
        }else{
            return true;
        }
    }

    /**
    This inAprJuneSeptNov() function checks to see if the given date can exist in April, June, September, 
            or November.

    @return this returns true if the date can exist in April, June, September, or November, and false otherwise
    */
    public boolean inAprJuneSeptNov(){
        int daysInAprJuneSeptNov = 30;
        if(this.day > daysInAprJuneSeptNov){
            return false;
        }else{
            return true;
        }
    }

    /**
    This equals() function checks to see if two dates are equal to each other

    @param date this is the second date that is being compared

    @return this returns true if both dates are equal to each other
    */
    public boolean equals(Date date){
        if (this.year == date.year && this.month == date.month && this.day == date.day){
            return true;
        }else{
            return false;
        }
    }

    /**
    This toString function makes the date into a string

     @return this returns the date as a string
    */
    public String toString(){
        String ans = this.month + "/" + this.day + "/" + this.year;
        return ans;
    }

    /**
    This parse_and_set_Date() function takes in the date from the terminal as a string and converts 
     it into a date object.

    @param date this is the date that is being taken in and converted into a date object
    */
    private void parseAndSetDate(String date){
        int tenDigitDate = 10;
        int eightDigitDate = 8;
        int nineDigitDate = 9;
        if(date.length() == tenDigitDate){ //if mm/dd/yyyy
            this.year = Integer.parseInt(date.substring(6));
            this.month = Integer.parseInt(date.substring(0, 2));
            this.day = Integer.parseInt(date.substring(3, 5));
        }else if(date.length() == eightDigitDate) { //if m/d/yyyy
            this.year = Integer.parseInt(date.substring(4));
            this.month = Integer.parseInt(date.substring(0, 1));
            this.day = Integer.parseInt(date.substring(2, 3));
        }else if(date.length() == nineDigitDate){
            try{ //if m/dd/yyyy
                this.year = Integer.parseInt(date.substring(5));
                this.month = Integer.parseInt(date.substring(0, 1));
                this.day = Integer.parseInt(date.substring(2, 4));
            }catch (Exception e){ //if mm/d/yyyy
                this.year = Integer.parseInt(date.substring(5));
                this.month = Integer.parseInt(date.substring(0, 2));
                this.day = Integer.parseInt(date.substring(3, 4));
            }
        }
    }


    /**
     This test bed tests the isValid() method within the src.Date.java class
     */
    public static void main(String[] args){

        Date test1 = new Date("13/1/2020", 1);
        System.out.println("The given output should be false since 13/1/2020 has an invalid month. "
                            + "The given output is: " + test1.isValid()); //Test Case 1

        Date test2 = new Date("2/29/2020", 1);
        System.out.println("The given output should be true since 2/29/2020 has a valid day and is during a leap year. "
                + "The given output is: " + test2.isValid()); //Test Case 2

        Date test3 = new Date("2/29/2022", 1);
        System.out.println("The given output should be false since 2/29/2022 doesnt have a valid day and " +
                " 2022 is not a leap year. " + "The given output is: " + test3.isValid()); //Test Case 3

        Date test4 = new Date("01/32/2022", 1);
        System.out.println("The given output should be false since 01/32/2022 has an invalid day. "
                + "The given output is: " + test4.isValid()); //Test Case 4

        Date test5 = new Date("12/32/2020", 1);
        System.out.println("The given output should be false since 12/32/2020 has a invalid day in December. "
                + "The given output is: " + test5.isValid()); //Test Case 5

        Date test6 = new Date("09/31/2021", 1);
        System.out.println("The given output should be false since 09/31/2021 has a invalid day in September. "
                + "The given output is: " + test6.isValid()); //Test Case 6
    }
    
}
