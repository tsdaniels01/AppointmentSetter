package model;

import dbCalls.DBCountries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/** This class is a value object for Countries. It is used for plain old java object reference types.*/
public class Countries {

    private int countryID; // Creates a variable name countryID;
    private String countryName; // Creates a variable name countryName;
    private String createDateTime; // Creates a variable name createDateTime;
    private String createdBy; // Creates a variable name createdBy;
    private String lastUpdate; // Creates a variable name lastUpdate;
    private String lastUpdateBy; // Creates a variable name lastUpdateBy;

    /**This method is a constructor for the Countries object and assigns the parameters to the variable above
     *
     * @param countryID int parameter
     * @param countryName String parameter
     * @param createDateTime String parameter
     * @param createdBy String parameter
     * @param lastUpdate String parameter
     * @param lastUpdateBy String parameter
     */
    public Countries(int countryID, String countryName, String createDateTime, String createdBy, String lastUpdate,
                     String lastUpdateBy) {
        this.countryID = countryID; // Assigns parameter to the variable above with the same name
        this.countryName = countryName; // Assigns parameter to the variable above with the same name
        this.createDateTime = createDateTime; // Assigns parameter to the variable above with the same name
        this.createdBy = createdBy; // Assigns parameter to the variable above with the same name
        this.lastUpdate = lastUpdate; // Assigns parameter to the variable above with the same name
        this.lastUpdateBy = lastUpdateBy; // Assigns parameter to the variable above with the same name
    }

    /**
     * This method is used to create a Countries observable list in order to set items in fxml controls
     */
    public static ObservableList<Countries> allCountries = FXCollections.observableArrayList();

    /**
     * This method is used to get the Countries observable list
     *
     * @return
     */
    public static ObservableList<Countries> getCountries() {
        return allCountries;

    }

    public static void addCountries(Countries countries) { allCountries.add(countries); }

    /**
     * This method is used to override the toString method in order to populate fxml controls
     *
     * @return
     */
    @Override
    public String toString() {
        return (countryName);
    }

    /**
     * This method is a getter for the countryID variable
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * This method is a setter for the countryID variable
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * This method is a getter for the countryName variable
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * This method is a setter for the countryName variable
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * This method is a getter for the createDateTime variable
     */
    public String getCreateDateTime() {
        return createDateTime;
    }

    /**
     * This method is a setter for the createDateTime variable
     */
    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
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
     * This method is a getter for the lastUpdateBy variable
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * This method is a setter for the lastUpdateBy variable
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /** This method is used to take a countryID and locate the countryName
     *
     * @param id the countryID
     * @return the country object
     */
    public static Countries idToName (int id){

        for (Countries countries: getCountries()) // Gets all countries
            if (countries.getCountryID() == id) { // Locates the country by ID
                Countries idToName = countries; // Creates a variable object to hold the country
                return idToName; // Returns the country object
            }
        return null; // Returns null if not found
    }

}
