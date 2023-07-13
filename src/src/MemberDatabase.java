package src;

/**
 This is the MemberDatabase class which holds every attribute required for Database.

 With this class a user will be able to access a list of members and the amount of members.

 @author Shanmukh Adepu

 @author Manvay Dangi

 @since  10/3/2022
 */

public class MemberDatabase {
    private Member [] mlist;
    private int size;

    /**
     This MemberDatabase() constructor creates a database to store the members in with a
     starting array size of 4.
     */
    public MemberDatabase(){
        this.mlist = new Member [4];
        this.size = mlist.length;
    }

    /**
     This find() constructor allows the user to find a member within the database and returns the index
     at which the member is stored within the database.

     @param member this is the member being searched for within the member database

     @return this returns the index of where the searched for member is stored within the member database
                if they are there. If the member is not within the database then -1 is returned.
     */
    private int find(Member member) {
        int notFound = -1;
        for (int i = 0; i < this.size;i++){
            if(member.equals(this.mlist[i])) {
                return i;
            }
        }
        return notFound;
    }

    /**
     This grow() function grows the size of the array for the member database by 4;

     */
    private void grow() {
        Member [] grown = new Member[this.size+4];
        for(int i = 0; i < this.size;i++){
            grown[i] = this.mlist[i];
        }
        this.mlist = grown;
        this.size = this.mlist.length;
    }

    /**
     This add() function adds a member into the member database

     @param member this is the member that is looking to be added into the member database

     @return this returns a false if the member is already within the member database and a true if the member
            is not within the database and has been added.
     */
    public boolean add(Member member) {
        if (this.find(member) != -1){
            return false;
        }
        if (this.mlist[size-1] == null){
            for (int i = 0;i < this.size;i++){
                if(this.mlist[i] == null){
                    this.mlist[i] = member;
                    return true;
                }
            }
        }else{
            grow();
            return add(member);
        }
        return false;
    }

    /**
     This remove() function removes a member from the member database

     @param member this is the member that is going to be removed from the member database

     @return this returns a false if the member is not within the member database and a true if the member is
            within the database and has been removed.
     */
    public boolean remove(Member member) {
        int ind = find(member);
        if(ind == -1){
            return false;
        }else{
            for(int i = ind;i < size;i++ ){
                mlist[i] = null;
                if(i != size-1){
                    Member temp = mlist[i];
                    mlist[i] = mlist[i+1];
                    mlist[i+1] = temp;
                }
            }
            return true;
        }
    }

    /**
     This print() function prints out the entire member database

     @throws IllegalArgumentException if the member database is empty
     */
    public void print () {
        if(isNull()){
            throw new IllegalArgumentException("Member Database is Empty!");
        }
        printDb();
    } //print the array contents as is
    public void printFee(){
        if(isNull()){
            throw new IllegalArgumentException("Member Database is Empty!");
        }
        printDbWithFee();
    } //print the array contents sorted by fee


    /**
     This printByCounty() function prints out the member database by order of county's

     @throws IllegalArgumentException if there are no members within the member database
     */
    public void printByCounty() {
        for (int i = 0; i < this.size - 1; i++){
            for (int j = 0; j < this.size - i - 1; j++){
                if (this.mlist[j] == null || this.mlist[j+1] == null) {
                    break;
                }
                if (this.mlist[j].getLocation().compareLocations
                        (this.mlist[j + 1].getLocation()) == -1) {
                    Member temp = this.mlist[j];
                    this.mlist[j] = this.mlist[j + 1];
                    this.mlist[j + 1] = temp;
                    //swap and iterate everytime a swap occurs
                }
            }
        }
        if(isNull()){
            throw new IllegalArgumentException("Member Database is Empty!");
        }
        System.out.println();
        System.out.println("-list of members sorted by " +
                "county and zipcode-");
        printDb();
        System.out.println("-end of list-");
    } //sort by county and then zipcode

    /**
     This printByExpirationDate() function prints the member database in order of membership expiration dates

     @throws IllegalArgumentException if the member database is empty
     */
    public void printByExpirationDate() {
        for (int i = 0; i < this.size - 1; i++){
            for (int j = 0; j < this.size - i - 1; j++){
                if (this.mlist[j] == null || this.mlist[j+1] == null) {
                    break;
                }
                if (this.mlist[j].getExpire().compareTo
                        (this.mlist[j + 1].getExpire()) == -1) {
                    // swap arr[j+1] and arr[j]
                    Member temp = this.mlist[j];
                    this.mlist[j] = this.mlist[j + 1];
                    this.mlist[j + 1] = temp;
                }
            }
        }
        if(isNull()){
            throw new IllegalArgumentException("Member Database is Empty!");
        }
        System.out.println();
        System.out.println("-list of members sorted by " +
                "membership expiration date-");
        printDb();
        System.out.println("-end of list-");
    } //sort by the expiration date

    /**
     This printByName() function prints out the member database in alphabetical order

     @throws IllegalArgumentException if the member database is empty
     */
    public void printByName() {
        if(mlist == null){
            System.out.println("Database is empty");
        }
        for (int i = 0; i < this.size - 1; i++){
            for (int j = 0; j < this.size - i - 1; j++){
                if (this.mlist[j] == null || this.mlist[j+1] == null) {
                    break;
                }
                if (this.mlist[j].compareTo(this.mlist[j + 1]) == 1) {
                    Member temp = this.mlist[j];
                    this.mlist[j] = this.mlist[j + 1];
                    this.mlist[j + 1] = temp;
                }
            }
        }
        if(isNull()){
            throw new IllegalArgumentException("Member Database is Empty!");
        }
        System.out.println();
        System.out.println("-list of members sorted by last name, and first name-");
        printDb();
        System.out.println("-end of list-");

    } //sort by last name and then first name

    /**
     This printDb() function modularizes the prints for the member database
     */
    private void printDb() {
        for(int i = 0; i < this.size;i++){
            if(this.mlist[i] != null) {
                System.out.println(this.mlist[i]);
            }
        }
    }
    private void printDbWithFee() {

        for(int i = 0; i < this.size;i++){
            if(this.mlist[i] != null) {
                System.out.println(this.mlist[i] + ", Membership Fee: $" + mlist[i].membershipFee());
            }
        }
    }
    /**
     This getMlist() function gets the member database

     @return this returns the member database
     */
    public Member[] getMlist(){
        return mlist;
    }

    /**
     This getMember() function returns the member at the given index of the member database

     @param ind this is the index at which the user is trying to receive the member

     @return this returns the member at index ind of the member database
     */

    /**
     This isNull() function checks to see if the member database has any null spots

     @return This returns true if the member database is null and false otherwise
     */
    private boolean isNull(){
        boolean is = true;
        for (int i = 0; i < mlist.length; i++) {
            if(mlist[i] != null){
                is = false;
                return is;
            }
        }
        return is;
    }
    public Member getMember(Member member) {
        int notFound = -1;
        for (int i = 0; i < this.size;i++){
            if(member.equals(this.mlist[i])) {
                return mlist[i];
            }
        }
        return null;
    }
}
