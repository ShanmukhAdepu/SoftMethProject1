package src;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

/**
 This is the UserInterface class for the project.
 
 @author Shanmukh Adepu

 @author Manvay Dangi
 
 @since  10/16/2022
 */
public class GymManager {

    /**
     Stores the fitness classes
     */
    private FitnessClass[] classes;

    /**
     * The method that instantiates the entire program and takes input from System.in and validates it.
     */
    public void run() {
        ClassSchedule cs = new ClassSchedule();
        Scanner scan = new Scanner(System.in);
        System.out.println("Gym Manager Running...");
        MemberDatabase db = new MemberDatabase();
        while (2 == 2) {
            String w = scan.next();
            if (!(w.equals("A") || w.equals("R") || w.equals("P") || w.equals("PC")
                    || w.equals("PN") || w.equals("PD") || w.equals("S")
                    || w.equals("C") || w.equals("D") || w.equals("Q") || w.equals("LS") || w.equals("LM") || w.equals("AF") || w.equals("AP") || w.equals("PF") || w.equals("CG") ||w.equals("DG"))) {
                System.out.println(w + " is an invalid command!");
                continue;
            }
            if (w.equals("Q")) {
                System.out.println("Gym Manager terminated.");
                break;
            }
            commandInput(scan,db,w,cs);
            scan.nextLine();
        }
    }


    /**
     * Method that handles the A command to add a member to the src.MemberDatabase
     * @param scan takes in the scanner instance to read parameters from System.in
     * @param db takes the database instance to add members to it
     */

    private void A(Scanner scan, MemberDatabase db) {
        try {
            String fname = scan.next();
            String lname = scan.next();
            String dob = scan.next();
            String location = scan.next();
            String locc = location;
            location = location.toLowerCase();
            String location1 = location.substring(0, 1).toUpperCase() + location.substring(1);
            try {
                Date dobb = new Date(dob, 1);
                Date expirationn = new Date("", 2);
                try {
                    Location loc = Location.valueOf(location1);
                    boolean x = db.add(new Member(fname, lname, dobb,
                            expirationn, loc));
                    if (x == true) {
                        System.out.println(fname + " " + lname +
                                " added.");
                    } else {
                        System.out.println(fname + " " + lname +
                                " is already in the database.");
                    }
                } catch (Exception e) {
                    System.out.println(locc + ":invalid location!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Invalid command input!");
        }
    }
    private void AF(Scanner scan,MemberDatabase db){
        try {
            String fname = scan.next();
            String lname = scan.next();
            String dob = scan.next();
            String location = scan.next();
            String locc = location;
            location = location.toLowerCase();
            String location1 = location.substring(0, 1).toUpperCase() + location.substring(1);
            try {
                Date dobb = new Date(dob, 1);
                Date expirationn = new Date("", 2);
                try {
                    Location loc = Location.valueOf(location1);
                    boolean x = db.add(new Family(fname, lname, dobb,
                            expirationn, loc,1));
                    if (x == true) {
                        System.out.println(fname + " " + lname +
                                " added.");
                    } else {
                        System.out.println(fname + " " + lname +
                                " is already in the database.");
                    }
                } catch (Exception e) {
                    System.out.println(locc + ":invalid location!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Invalid command input!");
        }
    }
    private void AP(Scanner scan, MemberDatabase db){
        try {
            String fname = scan.next();
            String lname = scan.next();
            String dob = scan.next();
            String location = scan.next();
            String locc = location;
            location = location.toLowerCase();
            String location1 = location.substring(0, 1).toUpperCase() + location.substring(1);
            try {
                Date dobb = new Date(dob, 1);
                Date expirationn = new Date(" ", 2);
                try {
                    Location loc = Location.valueOf(location1);
                    boolean x = db.add(new Premium(fname, lname, dobb,
                            expirationn, loc,3));
                    if (x == true) {
                        System.out.println(fname + " " + lname +
                                " added.");
                    } else {
                        System.out.println(fname + " " + lname +
                                " is already in the database.");
                    }
                } catch (Exception e) {
                    System.out.println(locc + ":invalid location!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Invalid command input!");
        }
    }

    /**
     * Method to handle the P command which prints the database as is
     * @param db the database instance to print
     */
    private void P(MemberDatabase db) {
        try {
            System.out.println("-list of members-");
            db.print();
            System.out.println("-end of list-");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to handle the PD, print sorted by expiration date, command
     * @param db instance to sort and print on
     */
    private void PD(MemberDatabase db) {
        try {
            db.printByExpirationDate();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to handle the PN, print sorted by last name, then first, command
     * @param db instance to sort and print on
     */
    private void PN(MemberDatabase db) {
        try {
            db.printByName();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to handle the PC, print sorted by county, then zip code, command
     * @param db instance to sort and print on
     */
    private void PC(MemberDatabase db) {
        try {
            db.printByCounty();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private void PF(MemberDatabase db){
        try {
            db.printFee();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to handle the R command which removes members from the member database
     * @param scan the scanner instance to take parameters from System.in
     * @param db the database instance to preform the removal on
     */
    private void R(Scanner scan, MemberDatabase db) {
        String fname = scan.next();
        String lname = scan.next();
        String dob = scan.next();
        Date dobb = null;
        try {
            dobb = new Date(dob, 1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        if (db.remove(new Member(fname, lname, dobb, new Date(), Location.Edison))) {
            System.out.println(fname + " " + lname + " removed.");
        }else{
            System.out.println(fname + " " + lname + " is not in the database.");
        }
    }

    /**
     * Method to handle the S command which prints out all Fitness Classes and the participants if any.
     */
    private void S(FitnessClass[] classes) {
        if(isNull(classes)) {
            System.out.println("Fitness class schedule is empty.");
            return;
        }
        for (int i = 0; i < classes.length; i++) {
            System.out.println(classes[i]);
        }
    }

    /**
     * Method to handle the C, check in, command
     * @param scan the scanner instance to take in parameters from System.in
     * @param db the database instance to reference for validation
     */
    private void C(Scanner scan, MemberDatabase db,ClassSchedule cs) {
        String classs = scan.next();
        String teacher = scan.next();
        String location = scan.next().toLowerCase();
        String loc = location.substring(0,1).toUpperCase() + location.substring(1);
        Location locc = null;
        try{
            locc = Location.valueOf(loc);
        }catch (Exception e){
            System.out.println(loc+" - invalid location.");
            return;
        }
        String fname = scan.next();
        String lname = scan.next();
        String dob = scan.next();
        try {
            Date dobb = new Date(dob, 1);
             try {
                 if (classs.equalsIgnoreCase("cardio") || classs.equalsIgnoreCase("spinning") ||classs.equalsIgnoreCase("pilates") ) {
                     if (cs.checkIn(new FitnessClass(classs, teacher, locc, fname, lname, dobb, db))) {
                         return;
                     } else if(!(teacher.equalsIgnoreCase("Jennifer")||teacher.equalsIgnoreCase("Kim")||teacher.equalsIgnoreCase("Davis")||teacher.equalsIgnoreCase("Denis")||teacher.equalsIgnoreCase("Emma"))){
                         System.out.println(teacher +" - instructor does not exist");
                     }else{
                         System.out.println(classs + " by " + teacher + " does not exist at " + locc);
                     }
                 }else{
                     System.out.println(classs + " - class does not exist.");
                 }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private void CG(Scanner scan, MemberDatabase db,ClassSchedule cs) {
        String classs = scan.next();
        String teacher = scan.next();
        String location = scan.next().toLowerCase();
        String loc = location.substring(0,1).toUpperCase() + location.substring(1);
        Location locc = null;
        try{
            locc = Location.valueOf(loc);
        }catch (Exception e){
            System.out.println(loc+" - invalid location.");
            return;
        }
        String fname = scan.next();
        String lname = scan.next();
        String dob = scan.next();
        try {
            Date dobb = new Date(dob, 1);
            try {
                if (classs.equalsIgnoreCase("cardio") || classs.equalsIgnoreCase("spinning") ||classs.equalsIgnoreCase("pilates") ) {
                    if (cs.checkInGuest(new FitnessClass(classs, teacher, locc, fname, lname, dobb, db))) {
                        return;
                    } else if(!(teacher.equalsIgnoreCase("Jennifer")||teacher.equalsIgnoreCase("Kim")||teacher.equalsIgnoreCase("Davis")||teacher.equalsIgnoreCase("Denis")||teacher.equalsIgnoreCase("Emma"))){
                        System.out.println(teacher +" - instructor does not exist");
                    }else{
                        System.out.println(classs + " by " + teacher + " does not exist at " + locc);
                    }
                }else{
                    System.out.println(classs + " - class does not exist.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Method to handle the D, drop member from fitness class, command
     * @param scan Scanner instance to take parameters in for the command
     */

    private void D(Scanner scan, MemberDatabase db,ClassSchedule cs) {
        String classs = scan.next();
        String teacher = scan.next();
        String location = scan.next().toLowerCase();
        String loc = location.substring(0,1).toUpperCase() + location.substring(1);
        Location locc = null;
        try{
            locc = Location.valueOf(loc);
        }catch (Exception e){
            System.out.println(loc+" - invalid location.");
            return;
        }
        String fname = scan.next();
        String lname = scan.next();
        String dob = scan.next();
        try {
            Date dobb = new Date(dob, 1);
            try {
                if (classs.equalsIgnoreCase("cardio") || classs.equalsIgnoreCase("spinning") ||classs.equalsIgnoreCase("pilates") ) {
                    if (cs.checkOut(new FitnessClass(classs, teacher, locc, fname, lname, dobb, db))) {
                        return;
                    } else if(!(teacher.equalsIgnoreCase("Jennifer")||teacher.equalsIgnoreCase("Kim")||teacher.equalsIgnoreCase("Davis")||teacher.equalsIgnoreCase("Denis")||teacher.equalsIgnoreCase("Emma"))){
                        System.out.println(teacher +" - instructor does not exist");
                    }else{
                        System.out.println(classs + " by " + teacher + " does not exist at " + locc);
                    }
                }else{
                    System.out.println(classs + " - class does not exist.");
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }catch(IllegalArgumentException e){
        System.out.println(e.getMessage());
    }
}

private void DG(Scanner scan, MemberDatabase db,ClassSchedule cs) {
    String classs = scan.next();
    String teacher = scan.next();
    String location = scan.next().toLowerCase();
    String loc = location.substring(0,1).toUpperCase() + location.substring(1);
    Location locc = null;
    try{
        locc = Location.valueOf(loc);
    }catch (Exception e){
        System.out.println(loc+" - invalid location.");
        return;
    }
    String fname = scan.next();
    String lname = scan.next();
    String dob = scan.next();
    try {
        Date dobb = new Date(dob, 1);
        try {
            if (classs.equalsIgnoreCase("cardio") || classs.equalsIgnoreCase("spinning") ||classs.equalsIgnoreCase("pilates") ) {
                if (cs.checkOutGuest(new FitnessClass(classs, teacher, locc, fname, lname, dobb, db))) {
                    return;
                } else if(!(teacher.equalsIgnoreCase("Jennifer")||teacher.equalsIgnoreCase("Kim")||teacher.equalsIgnoreCase("Davis")||teacher.equalsIgnoreCase("Denis")||teacher.equalsIgnoreCase("Emma"))){
                    System.out.println(teacher +" - instructor does not exist");
                }else{
                    System.out.println(classs + " by " + teacher + " does not exist at " + locc);
                }
            }else{
                System.out.println(classs + " - class does not exist.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }catch(IllegalArgumentException e){
        System.out.println(e.getMessage());
    }
}
private void LS(ClassSchedule cs) {
    try {
        File file = new File("classSchedule.txt");
        Scanner scan = new Scanner(file);
        int i = 0;
        while (scan.hasNextLine()) {
            String[] result = scan.nextLine().split("\\s");
            if (i != 15)
                cs.getClasses()[i] = new FitnessClass(result[0], result[1], Time.valueOf(result[2]), Location.valueOf(result[3]));
            i++;
        }
    } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
    }
    System.out.println();
    System.out.println("-Fitness classes loaded-");
    S(cs.getClasses());
    System.out.println("-end of class list-");
    System.out.println();
}
private void LM(MemberDatabase db){
    try {
        File file = new File("memberList.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            String[] result = scan.nextLine().split("[\\s]+");
            String loc = result[4].toLowerCase();
            String location = loc.substring(0,1).toUpperCase() + loc.substring(1);

            db.add(new Member(result[0],result[1],new Date(result[2],1),new Date(result[3],2),Location.valueOf(location)));
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    System.out.println();
    System.out.println("-list of members loaded-");
    db.print();
    System.out.println("-end of list-");
}


    /**
     * Method to instantiate all instances of Fitness Classes into an array for easy inter-object operations
     */


    /**
     * Helper method to read commands from command input and call the relevant operation methods.
     * @param scan Scanner instance to pass along to the functions
     * @param db database instance to pass along to the functions
     * @param w The command in a String from System.in
     */
    private void commandInput(Scanner scan,MemberDatabase db,String w,ClassSchedule cs) {
        if (w.equals("A")) {
            A(scan, db);
        }
        if (w.equals("P")) {
            P(db);
        }
        if (w.equals("PD")) {
            PD(db);
        }
        if (w.equals("PN")) {
            PN(db);
        }
        if (w.equals("PC")) {
            PC(db);
        }
        if (w.equals("R")) {
            R(scan, db);
        }
        if (w.equals("S")) {
            System.out.println("-Fitness Classes-");
            S(cs.getClasses());
            System.out.println("-end of class list-");
        }
        if (w.equals("C")) {
            C(scan, db,cs);
        }
        if(w.equals("D")){
            D(scan,db,cs);
        }
        commandInput2(scan,db,w,cs);
    }
    private void commandInput2(Scanner scan,MemberDatabase db,String w,ClassSchedule cs){
        if(w.equals("LS")){
            LS(cs);
        }
        if(w.equals("LM")){
            LM(db);
        }
        if(w.equals("AF")){
            AF(scan,db);
        }
        if(w.equals("AP")){
            AP(scan,db);
        }
        if(w.equals("PF")){
            PF(db);
        }
        if(w.equals("CG")){
            CG(scan,db,cs);
        }
        if(w.equals("DG")){
            DG(scan,db,cs);
        }
    }
    private boolean isNull(FitnessClass[] classes){
        boolean x = true;
        for (FitnessClass aClass : classes) {
            if (aClass != null) {
                x = false;
            }
        }
        return x;
    }
}

