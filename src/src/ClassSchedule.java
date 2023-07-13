package src;

public class ClassSchedule {
    private FitnessClass [] classes;
    private int numClasses;

    public ClassSchedule(){
        this.classes = new FitnessClass[15];
        this.numClasses = 15;
    }

    public FitnessClass[] getClasses() {
        return classes;
    }
    public boolean checkIn(FitnessClass fitnessClass){
        int ind = findClass(fitnessClass);
        if(ind != -1){
           Member mem = fitnessClass.getMdb().getMember(new Member(fitnessClass.getMfname(),fitnessClass.getMlname(),fitnessClass.getMdob(),new Date(),Location.Bridgewater));
           if(mem == null){
               throw new IllegalArgumentException(fitnessClass.getMfname() + " " + fitnessClass.getMlname() + " " + fitnessClass.getMdob() + " is not in the database");
           }
            if(mem.getExpire().compareTo(new Date()) > 0){
                throw new IllegalArgumentException(fitnessClass.getMfname() + " " + fitnessClass.getMlname() + " " + fitnessClass.getMdob() + " membership expired.");
            }
           if(!(mem instanceof Family || mem instanceof Premium) && !(fitnessClass.getLocation().toString().equals(mem.getLocation().toString()))){
               throw new IllegalArgumentException(fitnessClass.getMfname() + " " + fitnessClass.getMlname() + " is checking in " + fitnessClass.getLocation() + ", " + fitnessClass.getLocation().getZipCode() + ", " + fitnessClass.getLocation().getCounty() + " - standard membership location restriction");
           }
           if(classes[ind].check(mem)) {
               throw new IllegalArgumentException(fitnessClass.getMfname() + " " + fitnessClass.getMlname() + " is already checked in");
           }
            for(int i = 0; i < this.numClasses; i++){
                if(this.classes[i].check(mem) && this.classes[i].getTime().compareTo(classes[ind].getTime()) == 0 && i != ind){
                    throw new IllegalArgumentException("Time Conflict - " + classes[ind].getName().toUpperCase() + " - " + this.classes[ind].getTeacher().toUpperCase() + " " + this.classes[ind].getTime() + ", " + this.classes[ind].getLocation().toString().toUpperCase() + ", " + this.classes[ind].getLocation().getZipCode() + ", " + this.classes[ind].getLocation().getCounty());
                }
            }
            if(classes[ind].checkIn(mem)) {
                System.out.println(fitnessClass.getMfname() + " " + fitnessClass.getMlname() + " checked in " + classes[ind].getName() + " - " + classes[ind].getTeacher() + " " + classes[ind].getTime() + ", " + classes[ind].getLocation());
                classes[ind].print();
                return true;
           }
        }
        return false;
    }
    public boolean checkInGuest(FitnessClass fitnessClass){
        int ind = findClass(fitnessClass);
        if(ind != -1){
            Member mem = fitnessClass.getMdb().getMember(new Member(fitnessClass.getMfname(),fitnessClass.getMlname(),fitnessClass.getMdob(),new Date(),Location.Bridgewater));
            if(mem == null){
                throw new IllegalArgumentException(fitnessClass.getMfname() + " " + fitnessClass.getMlname() + " " + fitnessClass.getMdob() + " is not in the database");
            }
            if(mem.getExpire().compareTo(new Date()) > 0){
                throw new IllegalArgumentException("Membership has expired!");
            }
            if(!(mem instanceof Family || mem instanceof Premium) && !(fitnessClass.getLocation().toString().equals(mem.getLocation().toString()))){
                throw new IllegalArgumentException("Standard membership - Guest check-in is not allowed");
            }
            if((mem instanceof Family || mem instanceof Premium) && !(fitnessClass.getLocation().toString().equals(mem.getLocation().toString()))){
                throw new IllegalArgumentException(fitnessClass.getMfname() + " " + fitnessClass.getMlname() + " Guest checking in " + fitnessClass.getLocation().toString().toUpperCase() + ", " + fitnessClass.getLocation().getZipCode() + ", " + fitnessClass.getLocation().getCounty() + " - guest location restriction");
            }
            if(mem instanceof Premium){
                Premium p = (Premium) mem;
                if(p.getAmountOfGuestPass() == 0){
                    throw new IllegalArgumentException(fitnessClass.getMfname() + " " + fitnessClass.getMlname() + " ran out of guest pass");
                }
            } else if (mem instanceof Family){
                Family f = (Family) mem;
                if(f.getAmountOfGuestPass() == 0){
                    throw new IllegalArgumentException(fitnessClass.getMfname() + " " + fitnessClass.getMlname() + " ran out of guest pass");
                }
            }
            if(classes[ind].checkInGuest(mem)) {
                System.out.println(fitnessClass.getMfname() + " " + fitnessClass.getMlname() + " (guest) checked in " + classes[ind].getName() + " - " + classes[ind].getTeacher() + " " + classes[ind].getTime() + ", " + classes[ind].getLocation());
                classes[ind].print();
                return true;
            }
        }
        return false;
    }
    public boolean checkOut(FitnessClass fitnessClass) {
        int ind = findClass(fitnessClass);
        if (ind != -1) {
            Member mem = fitnessClass.getMdb().getMember(new Member(fitnessClass.getMfname(), fitnessClass.getMlname(), fitnessClass.getMdob(), new Date(), Location.Bridgewater));
            if (mem == null) {
                throw new IllegalArgumentException(fitnessClass.getMfname() + " " + fitnessClass.getMlname() + " " + fitnessClass.getMdob() + " is not in the database");
            }
            if(!(classes[ind].check(mem))) {
                throw new IllegalArgumentException(fitnessClass.getMfname() + " " + fitnessClass.getMlname() + " did not check in");
            }
            if (classes[ind].remove(mem)) {
                System.out.println(fitnessClass.getMfname() + " " + fitnessClass.getMlname() + " done with the class");
                return true;
            }
        }
        return false;
    }
    public boolean checkOutGuest(FitnessClass fitnessClass) {
        int ind = findClass(fitnessClass);
        if (ind != -1) {
            Member mem = fitnessClass.getMdb().getMember(new Member(fitnessClass.getMfname(), fitnessClass.getMlname(), fitnessClass.getMdob(), new Date(), Location.Bridgewater));
            if (mem == null) {
                throw new IllegalArgumentException(fitnessClass.getMfname() + " " + fitnessClass.getMlname() + " " + fitnessClass.getMdob() + " is not in the database");
            }
            if(!(mem instanceof Family || mem instanceof Premium) && !(fitnessClass.getLocation().toString().equals(mem.getLocation().toString()))){
                throw new IllegalArgumentException("Standard membership - Guest check-out is not allowed");
            }
            if (classes[ind].removeGuest(mem)) {
                System.out.println(fitnessClass.getMfname() + " " + fitnessClass.getMlname() + " Guest done with the class");
                return true;
            }
        }
        return false;
    }

    private int findClass(FitnessClass fitnessClass){
        for(int i = 0; i < this.numClasses; i++){
            if(this.classes[i].equals(fitnessClass)){
                return i;
            }
        }
        return -1;
    }
}
