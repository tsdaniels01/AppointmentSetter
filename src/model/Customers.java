package model;

import dbCalls.DBCustomers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class is a value object for Customers and contains an observable list of customers to hold and manage POJO
 * reference types.
 * */
public abstract class Customers {

    private int customerID; // Creates a variable named customerID
    private String customerName; // Creates a variable named customerName
    private String address; // Creates a variable named address
    private String postalCode; // Creates a variable named postalCode
    private String phone; // Creates a variable named phone
    private String createDate; // Creates a variable named createDate
    private String createdBy; // Creates a variable named createdBy
    private String lastUpdate; // Creates a variable named lastUpdate
    private String lastUpdateBy; // Creates a variable named lastUpdateBy
    private int divisionID; // Creates a variable named divisionID
    private int countryID; // Creates a variable named countryID
    private String division; // Creates a variable named divisionID
    private String country; // Creates a variable named country
    private String type; // Creates a variable named type
    private String propertyType; // Creates variable name propertyType
    private String listingID; // Creates variable name listingNumber

    /**
     * This method is a constructor for the Customer object and assigns the parameters to the variable above
     *
     * @param customerID   int parameter
     * @param customerName String parameter
     * @param address      String parameter
     * @param postalCode   String parameter
     * @param phone        String parameter
     * @param createDate   String parameter
     * @param createdBy    String parameter
     * @param lastUpdate   String parameter
     * @param lastUpdateBy String parameter
     * @param divisionID   int parameter
     * @param division     String parameter
     * @param countryID    int parameter
     * @param country      String parameter
     */
    public Customers(int customerID, String customerName, String address, String postalCode, String phone,
                     String createDate, String createdBy, String lastUpdate, String lastUpdateBy, int divisionID,
                     String division, int countryID, String country, String type) {
        this.customerID = customerID; // Assigns parameter to the variable above with the same name
        this.customerName = customerName; // Assigns parameter to the variable above with the same name
        this.address = address; // Assigns parameter to the variable above with the same name
        this.postalCode = postalCode; // Assigns parameter to the variable above with the same name
        this.phone = phone; // Assigns parameter to the variable above with the same name
        this.createDate = createDate; // Assigns parameter to the variable above with the same name
        this.createdBy = createdBy; // Assigns parameter to the variable above with the same name
        this.lastUpdate = lastUpdate; // Assigns parameter to the variable above with the same name
        this.lastUpdateBy = lastUpdateBy; // Assigns parameter to the variable above with the same name
        this.divisionID = divisionID; // Assigns parameter to the variable above with the same name
        this.division = division; // Assigns parameter to the variable above with the same name
        this.countryID = countryID; // Assigns parameter to the variable above with the same name
        this.country = country; // Assigns parameter to the variable above with the same name
        this.type = type; // Assigns parameter to the variable above with the same name
    }

    /**
     * This method is an overloaded constructor for the Customer object and assigns the parameters to the variable
     * above. It's purpose is to allow calls on the database with all table parameters
     *
     * @param customerID   int parameter
     * @param customerName String parameter
     * @param address      String parameter
     * @param postalCode   String parameter
     * @param phone        String parameter
     * @param createDate   String parameter
     * @param createdBy    String parameter
     * @param lastUpdate   String parameter
     * @param lastUpdateBy String parameter
     * @param divisionID   int parameter
     * @param division     String parameter
     * @param countryID    int parameter
     * @param country      String parameter
     * @param type         String parameter
     * @param propertyType String parameter
     * @param listingID    String parameter
     */
    public Customers(int customerID, String customerName, String address, String postalCode, String phone,
                     String createDate, String createdBy, String lastUpdate, String lastUpdateBy, int divisionID,
                     String division, int countryID, String country, String type, String propertyType, String listingID) {
        this.customerID = customerID; // Assigns parameter to the variable above with the same name
        this.customerName = customerName; // Assigns parameter to the variable above with the same name
        this.address = address; // Assigns parameter to the variable above with the same name
        this.postalCode = postalCode; // Assigns parameter to the variable above with the same name
        this.phone = phone; // Assigns parameter to the variable above with the same name
        this.createDate = createDate; // Assigns parameter to the variable above with the same name
        this.createdBy = createdBy; // Assigns parameter to the variable above with the same name
        this.lastUpdate = lastUpdate; // Assigns parameter to the variable above with the same name
        this.lastUpdateBy = lastUpdateBy; // Assigns parameter to the variable above with the same name
        this.divisionID = divisionID; // Assigns parameter to the variable above with the same name
        this.division = division; // Assigns parameter to the variable above with the same name
        this.countryID = countryID; // Assigns parameter to the variable above with the same name
        this.country = country; // Assigns parameter to the variable above with the same name
        this.type = type; // Assigns parameter to the variable above with the same name
        this.propertyType = propertyType; // Assigns parameter to the variable above with the same name
        this.listingID = listingID; // Assigns parameter to the variable above with the same name
    }

    /**
     * This method is used to override the toString command in order to populate a combo box with the customerName
     *
     * @return customerName for combo box
     */
    @Override
    public String toString() {
        return "["+customerID+"] "+customerName;
    }

    // Creates an observable arraylist to hold the customer table from the database
    public static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    /**
     * This method retrieves the customers from the allCustomers list
     *
     * @return the allCustomers observable arraylist
     */
    public static ObservableList<Customers> getCustomers() {
        return allCustomers;
    }

    /**
     * This method is used to execute the call to the database to retrieve the customers table as well as
     * call the customers table from the database when an update has occurred.
     *
     * @return allCustomers arraylist directly from the database
     */
    public static ObservableList<Customers> getDBCustomers() {
        return DBCustomers.getDBCustomers();
    }

    /**
     * This method is used to addCustomers to the allCustomers observable list
     *
     * @param customers
     */
    public static void addCustomers(Customers customers) {
        allCustomers.add(customers);
    }

    /**
     * This method is used to call the createCustomers database method
     *
     * @param customers is the customers to be created
     */
    public static void createCustomers(Customers customers) {
        DBCustomers.addCustomers(customers);
    }

    // Creates an observable arraylist to hold filtered Customers
    public static ObservableList<Customers> allFilteredCustomers = FXCollections.observableArrayList();

    /**
     * This method retrieves the customers from the allFilteredCustomers list
     *
     * @return the allCustomers observable arraylist
     */
    public static ObservableList<Customers> getFilteredCustomers() {
        return allFilteredCustomers;
    }
    // Creates an observable arraylist to hold searched criteria from the database
    public static ObservableList<Customers> allSearchRecords = FXCollections.observableArrayList();

    /**
     * This method retrieves a list of searched customers
     *
     * @return the allSearchRecords observable arraylist
     */
    public static ObservableList<Customers> getSearchRecords() {
        return allSearchRecords;
    }

    /**
     * This method is a getter for the customerID variable
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * This method is a setter for the customerID variable
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * This method is a getter for the customerName variable
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * This method is a setter for the customerName variable
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * This method is a getter for the address variable
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method is a setter for the address variable
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method is a getter for the postalCode variable
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * This method is a setter for the postalCode variable
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * This method is a getter for the phone variable
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method is a setter for the phone variable
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * This method is a getter for the countryID variable
     *
     * @return divisionID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * This method is a setter for the countryID variable
     *
     * @param countryID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * This method is a getter for the division variable
     *
     * @return countryID
     */
    public String getDivision() {
        return division;
    }

    /**
     * This method is a setter for the division variable
     *
     * @return division
     */

    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * This method is a getter for the country variable
     *
     * @return division
     */

    public String getCountry() {
        return country;
    }

    /**
     * This method is a setter for the country variable
     *
     * @return country
     */

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * This method is a getter for the Type variable
     *
     * @return Type
     */
    public String getType() {
        return type;
    }

    /**
     * This method is a setter for the Type variable
     *
     * @return Type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method is a getter for the PropertyType variable
     *
     * @return PropertyType
     */
    public String getPropertyType() {
        return propertyType;
    }

    /**
     * This method is a setter for the PropertyType variable
     *
     * @return PropertyType
     */
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    /**
     * This method is a getter for the ListingNumber variable
     *
     * @return ListingNumber
     */
    public String getListingID() {
        return listingID;
    }

    /**
     * This method is a setter for the ListingNumber variable
     *
     * @return ListingNumber
     */
    public void setListingID(String listingNumber) {
        this.listingID = listingID;
    }

    /**
     * This method is used to update a customer in the database
     *
     * @param selectedCustomers is the customer to be updated
     */
    public static void updateCustomers(Customers selectedCustomers) {
        DBCustomers.updateCustomers(selectedCustomers);
    }

    /**
     * This method is used to delete a customer in the database
     *
     * @param selectedCustomer is the customer to be deleted
     */
    public static void deleteCustomers(Customers selectedCustomer) {
        DBCustomers.deleteCustomer(selectedCustomer);
    }

    /**
     * This method is used to determine if the customerID provided is an actual customer
     *
     * @param i the customerID
     * @return boolean value based on results
     */
    public static boolean isCustomerValid(int i) {

        for (Customers customers : getCustomers())
            if (customers.getCustomerID() == i) {
                return true;
            }
        return false;
    }

    /**
     * This method is used to locate a customer's name by the customer's ID
     *
     * @param id the customer's ID
     * @return the customer's name
     */
    public static String idToName(int id) {

        for (Customers customers : getCustomers())
            if (customers.getCustomerID() == id) {
                return customers.getCustomerName();
            }
        return null;
    }

    /**
     * This method is used to get a filtered list of Buyers to populate a tableView
     * @return the list of Buyers
     */
    public static ObservableList<Customers> getBuyers() {

        if (!(getFilteredCustomers()).isEmpty()) // Checks to see if the list is empty
            getFilteredCustomers().clear(); // If not, clears the list

        for (Customers customers : getCustomers()) // Retrieves all customers
            if (customers.getType().equals("buyer")) // Locates only buyers
                getFilteredCustomers().add(customers); // adds to filtered list

        if (getFilteredCustomers().isEmpty()) // Returns null if no appointments were found
            return null;
        else
            return getFilteredCustomers(); // Else, returns the list of weekly appointments

    }

    /**
     * This method is used to get a filtered list of sellers to populate a tableView
     * @return the list of Sellers
     */
    public static ObservableList<Customers> getSellers() {

        if (!(getFilteredCustomers()).isEmpty()) // Checks to see if the list is empty
            getFilteredCustomers().clear(); // If not, clears the list

        for (Customers customers : getCustomers()) // Retrieves all customers
            if (customers.getType().equals("seller")) // Locates only sellers
                getFilteredCustomers().add(customers); // adds to filtered list

        if (getFilteredCustomers().isEmpty()) // Returns null if no appointments were found
            return null;
        else
            return getFilteredCustomers(); // Else, returns the list of weekly appointments

    }

    /**
     * This method is used to clear the allCustomers List
     */
    public static void clearCustomers() {
        if (!(getCustomers()).isEmpty()) // Checks to see if the list is empty
            getCustomers().clear(); // If not, clears the list
        Customers.getDBCustomers(); // Calls on DB to retrieve updated list
    }

    /**
     * This method is used to check if a listingID already exists
     * @param id the listingID to check
     * @return boolean value
     */
    public static boolean checkListingID(String id) {
       for(Customers customers: getSellers()) // Retrieves all Sellers
           if (customers.getListingID().equals(id)){ // Checks if it exists
               return true; // True if does
           }
       return false; // False if not
    }

    /**
     * This method is used to retrieve a list of Buyers by customer name
     * @param name the customer name
     * @return list of buyers
     */
    public static ObservableList<Customers> getBuyerByName(String name){

        if (!(getSearchRecords()).isEmpty()) // Checks to see if the list is empty
            getSearchRecords().clear(); // If not, clears the list

        for (Customers customers : getBuyers()) // Retrieves all buyers
            if (customers.getCustomerName().contains(name)) // Locates buyer by name
                getSearchRecords().add(customers); // adds to filtered list

        if (getSearchRecords().isEmpty()) // Returns null if not found
            return null;
        else
            return getSearchRecords(); // Else, returns the list of searched buyers

    }

    /**
     * This method is used to retrieve a list of buyers by customer ID
     * @param id the customer Id
     * @return list of buyers
     */
    public static ObservableList<Customers> getBuyerByID(int id){
        if (!(getSearchRecords()).isEmpty()) // Checks to see if the list is empty
            getSearchRecords().clear(); // If not, clears the list

        for (Customers customers : getBuyers()) // Retrieves all buyers
            if (customers.getCustomerID()== id) // Locates buyer by name
                getSearchRecords().add(customers); // adds to filtered list

        if (getSearchRecords().isEmpty()) // Returns null if not found
            return null;
        else
            return getSearchRecords(); // Else, returns the list of searched buyers

    }

    /**
     * This method is used to retrieve a list of Sellers by customer name
     * @param name the name of the customer
     * @return the list of sellers
     */
    public static ObservableList<Customers> getSellerByName(String name){

        if (!(getSearchRecords()).isEmpty()) // Checks to see if the list is empty
            getSearchRecords().clear(); // If not, clears the list

        for (Customers customers : getSellers()) // Retrieves all buyers
            if (customers.getCustomerName().contains(name)) // Locates buyer by name
                getSearchRecords().add(customers); // adds to filtered list

        if (getSearchRecords().isEmpty()) // Returns null if not found
            return null;
        else
            return getSearchRecords(); // Else, returns the list of searched buyers

    }

    /**
     * This method is used to retrieve a list of Sellers by customer ID
     * @param id the id of the customer
     * @return list of Sellers
     */
    public static ObservableList<Customers> getSellerByID(int id){
        if (!(getSearchRecords()).isEmpty()) // Checks to see if the list is empty
            getSearchRecords().clear(); // If not, clears the list

        for (Customers customers : getSellers()) // Retrieves all buyers
            if (customers.getCustomerID()== id) // Locates buyer by name
                getSearchRecords().add(customers); // adds to filtered list

        if (getSearchRecords().isEmpty()) // Returns null if not found
            return null;
        else
            return getSearchRecords(); // Else, returns the list of searched buyers

    }

    /**
     * This method is used to locate a customer object by customer ID
     * @param id the id of the customer
     * @return the Customer object
     */
    public static Customers getCustomerObject(int id){
        for (Customers customers:getCustomers()) // retrieves all customers
            if(customers.getCustomerID() == id){ // locates customer by id
                return customers; // returns customer if found
            }
        return null; // returns null if not
    }
}
