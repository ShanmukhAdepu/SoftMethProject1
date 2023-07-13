package src;

/**
 This Enum Time holds all the times in which the fitness classes start

 @author Shanmukh Adepu

 @author Manvay Dangi

 @since 10/3/2922

 */

public enum Time {

    morning("09","30"),
    afternoon("14","00"),
    evening("18","30");

    private final String hour;
    private final String minute;

    /**
     This time constructor stores the hour and minutes of the times for Pilates, Spinning, and Cardio

     @param hour this is the hour that the given class starts

     @param minute this is the mminute that the given class starts
     */
    Time(String hour, String minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     This toString() method returns the time as a string type

     @return this returns the time as a string
     */
    public String toString(){

        return Integer.valueOf(hour) + ":" + minute;

    }
}
