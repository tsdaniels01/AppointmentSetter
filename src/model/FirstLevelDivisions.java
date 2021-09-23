package model;

import dbCalls.DBFirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/** This class is a value object for FirstLevelDivision and contains an observable arraylist of firstLevelDivisions
 * for POJO reference types
 * */
public class FirstLevelDivisions {

    private int divisionID; // Creates a variable named divisionID
    private String division; // Creates a variable named division
    private String createDate; // Creates a variable named createDate
    private String createdBy; // Creates a variable named createdBy
    private String lastUpdate; // Creates a variable named lastUpdate
    private int countryID; // Creates a variable name countryID
    private String countryName;


    /**
     * This method is a constructor for the FirstLevelDivision object and assigns the parameters to the variable
     * above
     */
    public FirstLevelDivisions(int divisionID, String division, String createDate, String createdBy, String lastUpdate,
                               int countryID) {
        this.divisionID = divisionID; // Assigns the parameter to the variable listed above with the same name
        this.division = division; // Assigns the parameter to the variable listed above with the same name
        this.createDate = createDate; // Assigns the parameter to the variable listed above with the same name
        this.createdBy = createdBy; // Assigns the parameter to the variable listed above with the same name
        this.lastUpdate = lastUpdate; // Assigns the parameter to the variable listed above with the same name
        this.countryID = countryID; // Assigns the parameter to the variable listed above with the same name
    }

    // Creates an observable list of firstLevelDivisions
    public static ObservableList<FirstLevelDivisions> allDivisions = FXCollections.observableArrayList();

    /**
     * This method is used to getDivisions from the allDivisions observable list
     *
     * @return allDivisions observable list
     */
    public static ObservableList<FirstLevelDivisions> getDivisions() {
        return allDivisions;
    }
    /**
     * This method is used to override the toString method in order to populate fxml controls
     *
     * @return division parameter
     */
    public static void addDivisions (FirstLevelDivisions divisions) { allDivisions.add(divisions);}

    // Creates an observable list of filtered firstLevelDivisions
    public static ObservableList<FirstLevelDivisions> allFilteredDivisions = FXCollections.observableArrayList();

    /**
     * This method gets allFilteredDivisions from the allFilteredDivisions list
     *
     * @return allFilteredDivision observable list
     */
    public static ObservableList<FirstLevelDivisions> getAllFilteredDivisions() {
        return allFilteredDivisions;
    }

    /**
     * This method is used to override the toString method in order to populate fxml controls
     *
     * @return division parameter
     */
    @Override
    public String toString() {
        return (division);
    }

    /**
     * This method is a getter for the divisionID variable
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * This method is a setter for the divisionID variable
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * This method is a getter for the division variable
     */
    public String getDivision() {
        return division;
    }

    /**
     * This method is a setter for the division variable
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * This method is a getter for the createDate variable
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method is a setter for the createDate variable
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * This method is a getter for the createdBy variable
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method is a setter for the createdBy variable
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method is a getter for the lastUpdate variable
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * This method is a setter for the lastUpdate variable
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * This method is a getter for the getCountryID variable
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * This method is a setter for the getCountry variable
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * This method is used to locate the divisions of a provided country
     *
     * @param index country to filter the divisions
     * @return allFilteredDivisions observable list
     */
    public static ObservableList<FirstLevelDivisions> fdList(int index) {

        if (!(getAllFilteredDivisions().isEmpty())) // Checks to see if the list is clear
            getAllFilteredDivisions().clear(); // If not, clears the list

        for (FirstLevelDivisions firstLevelDivisions : getDivisions()) { // Gets all Divisions
            if (firstLevelDivisions.getCountryID() == index) // locates the division object
                getAllFilteredDivisions().add(firstLevelDivisions); // adds the division to the filtered list
        }
        return getAllFilteredDivisions(); // the filtered list
    }

    /** This method is used to take a divisionID and locate the FirstLevelDivision object
     *
     * @param id the divisionID
     * @return the FirstLevelDivisions objects
     */
    public static FirstLevelDivisions idToName (int id){

        for (FirstLevelDivisions firstLevelDivisions: getDivisions()) // Gets all Divisions
            if (firstLevelDivisions.getDivisionID() == id) { // Locates the division by ID
                return firstLevelDivisions; // Returns the object
            }
        return null; // Returns null if not object is found
    }
}

