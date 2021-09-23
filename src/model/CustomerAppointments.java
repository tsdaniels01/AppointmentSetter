package model;


import dbCalls.DBCustomerAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**This class is used to create an observable list of customerAppointments which is used to provide a report and allows
 * for any POJO reference types.
 */

public class CustomerAppointments {

    private int customerID; // Creates a variable to hold the customerID
    private String customerName; // Creates a variable to hold the customerName
    private String type; // Creates a variable to hold the type
    private String month; // Creates a variable to hold the month
    private int count; // Creates a variable to hold the count


    /** This is a constuctor for the customer object and sets the parameters of the object
     *
     * @param customerID int parameter
     * @param customerName String parameter
     * @param type String parameter
     * @param month String parameter
     * @param count int parameter
     */
    public CustomerAppointments(int customerID, String customerName, String type, String month, int count) {
        this.customerID = customerID; // Assigns to the variable with the same name above
        this.customerName = customerName; // Assigns to the variable with the same name above
        this.type = type; // Assigns to the variable with the same name above
        this.month = month; // Assigns to the variable with the same name above
        this.count = count; // Assigns to the variable with the same name above
    }
    // Creates an observable list of customerAppointments
    public static ObservableList<CustomerAppointments> allCustomerAppointments = FXCollections.observableArrayList();
    // Creates an observable list of filtered CustomerAppointments
    public static ObservableList<CustomerAppointments> allFilteredCustomerAppointments =
            FXCollections.observableArrayList();

    /** This method is used to retrieve the allCustomerAppointments observable arraylist
     *
     * @return allCustomerAppointments list
     */
    public static ObservableList<CustomerAppointments> getAllCustomerAppointments() {
        return allCustomerAppointments;
    }
    /** This method is used to retrieve the allFilteredCustomerAppointments observable arraylist
     *
     * @return allFilteredCustomerAppointments list
     */
    public static ObservableList<CustomerAppointments> getAllFilteredCustomerAppointments() {
        return allFilteredCustomerAppointments;
    }

    /** This method is used to add a new customerAppointment to the allCustomerAppointments list
     *
     * @param appointments the appointment that is added to the list
     */
    public static void addCustomerAppointments(CustomerAppointments appointments) {
        allCustomerAppointments.add(appointments);
    }

    /** This a getter for the CustomerID variable
     *
     * @return customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /** This is a setter for the CustomerID variable
     *
     * @param customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    /** This a getter for the CustomerName variable
     *
     * @return CustomerName
     */
    public String getCustomerName() {
        return customerName;
    }
    /** This a setter for the CustomerName variable
     *
     * @return CustomerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /** This a getter for the Type variable
     *
     * @return Type
     */
    public String getType() {
        return type;
    }
    /** This a setter for the Type variable
     *
     * @return Type
     */
    public void setType(String type) {
        this.type = type;
    }
    /** This a getter for the Month variable
     *
     * @return Month
     */
    public String getMonth() {
        return month;
    }
    /** This a setter for the Month variable
     *
     * @return Month
     */
    public void setMonth(String month) {
        this.month = month;
    }
    /** This a getter for the Count variable
     *
     * @return Count
     */
    public int getCount() {
        return count;
    }
    /** This a setter for the Count variable
     *
     * @return Count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /** This method is used to provide an observable list of filtered CustomerAppointments based on the customerID
     *
     * @param id the customer's ID
     * @return an observable list of customerAppointments
     */
    public static ObservableList<CustomerAppointments> getCustomerAppointments(int id) {

        if (!(getAllFilteredCustomerAppointments()).isEmpty()) // Checks to see if the list is empty
            getAllFilteredCustomerAppointments().clear(); // If not, clears the list

        for (CustomerAppointments appointments: getAllCustomerAppointments()) // Gets all customerAppointments
            if (appointments.getCustomerID() == id){ // Locates the customer's appointments
                allFilteredCustomerAppointments.add(appointments); // adds any appointments found to the list

            }
        if (getAllFilteredCustomerAppointments().isEmpty()) // If nothing is found, returns null
            return null;
        else
            return getAllFilteredCustomerAppointments(); // Returns list of appointments found
    }
}

