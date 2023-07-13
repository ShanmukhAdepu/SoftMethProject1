package src;

/**
This Location class holds the locations of the five gyms that members can attend

@author Shanmukh Adepu

@author Manvay Dangi

@since 10/3/2922

*/

public enum Location {
    Bridgewater("08807", "Somerset"),
    Edison("08837", "Middlesex"),
    Franklin("08873", "Somerset"),
    Piscataway("08854", "Middlesex"),
    Somerville("08876", "Somerset");
    /**
     * Stores the ZipCode of the location
     */
    private final String zipCode;
    private final String county;

    /**
    This location constructor stores the zipcode and county of the five gym locations which are in Bridgewater, 
            Edison, Franklin, Piscataway, and Somerville.

    @param zipCode This is the zipcode for the given gym

    @param county This is the county for the given gym
     */
    Location(String zipCode, String county){
        this.zipCode = zipCode;
        this.county = county;
    }

    /**
    This compareLocations() functions takes in two locations and sees which one is alphabetically 
            superior to the another 

    @param loc This is the second location that is being compared alphabetically

    @return returns 1 if this is alphabetically superior to loc, 0 if both are equal, and -1 otherwise
    */

    public int compareLocations(Location loc){
        if(this.county == loc.county){ //goes through zipcodes if the county's are the same
            for(int i = 0; i < this.zipCode.length(); i++){
                if(this.zipCode.charAt(i) < loc.zipCode.charAt(i)){
                    return 1;
                }else if(this.zipCode.charAt(i) > loc.zipCode.charAt(i)){
                    return -1;
                }
            }
        }else{ //goes through the county's
            if(this.county == "Middlesex" && loc.county == "Somerset"){
                return 1;
            }else if(this.county == "Somerset" && loc.county == "Middlesex"){
                return -1;
            }
        }
        return 0;
    }

    /**
     This getCounty() method returns the county of the gyms location

     @return this returns the county of the gyms location
     */
    public String getCounty() {
        return county;
    }

    /**
     This getZipCode() method returns the zip code of the gyms location

     @return this returns the zip code of the gyms location
     */
    public String getZipCode() {

        return zipCode;
    }
}
