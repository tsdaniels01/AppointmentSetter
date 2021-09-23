package model;

import dbCalls.DBAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DateTimeConversions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Locale;


/** This class is used as a value object for Appointments and provides POJO functions to handle and manage appointments.
 * The appointments are added to an observable arraylist from the database.
 * */
public class Appointments {

    private int appointmentID; // Creates a variable for appointment ID
    private String title; // Creates a variable for title
    private String description; // Creates a variable for title
    private String location; // Creates a variable for location
    private String type; // Creates a variable for type
    private String startDateTime;// Creates a variable for start date and time
    private String endDateTime; // Creates a variable for end date and time
    private String createDateTime; // Creates a variable for created date and time
    private String createdBy; // Creates a variable for created by
    private String lastUpdate; // Creates a variable for last update
    private String lastUpdateBy; // Creates a variable for last update by
    private int customerID; // Creates a variable for the customerID
    private int userID; // Creates a variable for the userID
    private int contactID; // Creates a variable for the contactID
    private String contactName; // Creates a variable for the contactName

    /**
     * This method is a constructor for the appointments object and assigns the parameters to the variable above
     *
     * @param appointmentID  Integer parameter
     * @param title          String parameter
     * @param description    String parameter
     * @param location       String parameter
     * @param type           String parameter
     * @param startDateTime  String parameter
     * @param endDateTime    String parameter
     * @param createDateTime String parameter
     * @param createdBy      String parameter
     * @param lastUpdate     String parameter
     * @param lastUpdateBy   String parameter
     * @param customerID     String parameter
     * @param userID         Integer parameter
     * @param contactID      Integer parameter
     * @param contactName    Integer parameter
     */

    public Appointments(int appointmentID, String title, String description, String location, String type,
                        String startDateTime, String endDateTime, String createDateTime, String createdBy,
                        String lastUpdate, String lastUpdateBy, int customerID, int userID, int contactID,
                        String contactName) {
        this.appointmentID = appointmentID; // Assigns parameter to the variable above with the same name
        this.title = title; // Assigns parameter to the variable above with the same name
        this.description = description; // Assigns parameter to the variable above with the same name
        this.location = location; // Assigns parameter to the variable above with the same name
        this.type = type; // Assigns parameter to the variable above with the same name
        this.startDateTime = startDateTime; // Assigns parameter to the variable above with the same name
        this.endDateTime = endDateTime; // Assigns parameter to the variable above with the same name
        this.createDateTime = createDateTime; // Assigns parameter to the variable above with the same name
        this.createdBy = createdBy; // Assigns parameter to the variable above with the same name
        this.lastUpdate = lastUpdate; // Assigns parameter to the variable above with the same name
        this.lastUpdateBy = lastUpdateBy;  // Assigns parameter to the variable above with the same name
        this.customerID = customerID; // Assigns parameter to the variable above with the same name
        this.userID = userID; // Assigns parameter to the variable above with the same name
        this.contactID = contactID; // Assigns parameter to the variable above with the same name
        this.contactName = contactName; // Assigns parameter to the variable above with the same name
    }
    // Creates an observable arraylist variable to hold appointments from the database
    public static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    /** This method is used to retrieve the allAppointments observable arraylist to use in POJO functions
     *
     * @return the allAppointments observable arraylist
     */
    public static ObservableList<Appointments> getAllAppointments() {
        return allAppointments;
    }

    /** This method is used to add appointments to the allAppointments observable arraylist
     *
     * @param appointments the appointment to be added to the list
     */
    public static void addAppointments(Appointments appointments) {
        allAppointments.add(appointments);
    }

    /** This method is used to create a new appointment in the database
     *
     * @param appointments the new appointment to be created
     */
    public static void createAppointments(Appointments appointments) {
        DBAppointments.addAppointments(appointments);
    }

    // Creates an observable arraylist of appointments to filter by week
    public static ObservableList<Appointments> allFilteredAppointments = FXCollections.observableArrayList();

    /** This method is used to retrieve a filtered observable arraylist
     *
     * @return the allFilteredAppointments list
     */
    public static ObservableList<Appointments> getAllFilteredAppointments() {
        return allFilteredAppointments;
    }
    // Creates an observable arraylist of appointments to filter by month
    public static ObservableList<Appointments> allCollectedAppointments = FXCollections.observableArrayList();

    /** This method is used to retrieve a filtered observable arraylist
     *
     * @return the allCollectedAppointments list
     */
    public static ObservableList<Appointments> getAllCollectedAppointments() {
        return allCollectedAppointments;
    }

    /** This method is used to override the toString method in order to populate combo boxes
     *
     * @return contactName
     */
    @Override
    public String toString() {
        return contactName;
    }

    /**
     * This is a getter for the appointmentID variable
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * This is a setter for the appointmentID variable
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * This is a getter for the title variable
     */
    public String getTitle() {
        return title;
    }

    /**
     * This is a setter for the title variable
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This is a getter for the description variable
     */
    public String getDescription() {
        return description;
    }

    /**
     * This is a setter for the description variable
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This is a getter for the location variable
     */
    public String getLocation() {
        return location;
    }

    /**
     * This is a setter for the location variable
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This is a getter for the type variable
     */
    public String getType() {
        return type;
    }

    /**
     * This is a setter for the type variable
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This is a getter for the startDateTime variable
     */
    public String getStartDateTime() {
        return startDateTime;
    }

    /**
     * This is a setter for the startDateTime variable
     */
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * This is a getter for the endDateTime variable
     */
    public String getEndDateTime() {
        return endDateTime;
    }

    /**
     * This is a setter for the endDateTime variable
     */
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * This is a getter for the createDateTime variable
     */
    public String getCreateDateTime() {
        return createDateTime;
    }

    /**
     * This is a setter for the createDateTime variable
     */
    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    /**
     * This is a getter for the createdBy variable
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This is a setter for the createdBy variable
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This is a getter for the lastUpdate variable
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * This is a setter for the lastUpdate variable
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * This is a getter for the lastUpdateBy variable
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * This is a setter for the lastUpdateBy variable
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * This is a getter for the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * This is a setter for the customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * this is a getter for the userID variable
     */
    public int getUserID() {
        return userID;
    }

    /**
     * this is a setter for the userID variable
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * this is a getter for the contactID variable
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * this is a setter for the contactID variable
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * This is a getter for the contactName variable
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * This is a setter for the contactName variable
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * This method is used to delete an Appointment in the database
     *
     * @param selectedAppointment is the customer to be deleted
     */
    public static void deleteAppointments(Appointments selectedAppointment) {
        DBAppointments.deleteAppointments(selectedAppointment); // Delete call method to the database
    }

    /**
     * This method is used to update an Appointment in the database
     *
     * @param selectedAppointment is the appointment to be updated
     */
    public static void updateAppointments(Appointments selectedAppointment) {
        DBAppointments.updateAppointments(selectedAppointment); // Update call method to the database
    }

    /** This method is used to retrieve a list of appointments by month
     *
     * @param m is the month number
     * @return allMonthlyAppointments observable arraylist
     */
    public static ObservableList<Appointments> getMonthlyAppointments(int m) {

        if (!(getAllCollectedAppointments()).isEmpty()) // Checking to see if the list is empty
            getAllCollectedAppointments().clear(); // If not, clears the list

        for (Appointments appointments : getAllAppointments()) { // Gets all appointments
            // Calls a method to extract the date and compares it to the passed in month number
            if (DateTimeConversions.extractDate(appointments.getStartDateTime()).getMonthValue() == m)
                getAllCollectedAppointments().add(appointments); // Adds matching appointments to list
        }
        if (getAllCollectedAppointments().isEmpty()) // If no appointments are found, returns null
            return null;
        else
            return getAllCollectedAppointments(); // Else, returns found appointments
    }

        /** This method is used to retrieve a list of appointments by week
         *
         * @param m is the week number
         * @return allWeeklyAppointments observable arraylist
         */
    public static ObservableList<Appointments> getWeeklyAppointments(int m) {

        if (!(getAllFilteredAppointments()).isEmpty()) // Checks to see if the list is empty
            getAllFilteredAppointments().clear(); // If not, clears the list

        for (Appointments appointments : getAllAppointments()) { // Retrieves allAppointments
            Locale locale = Locale.US; // Sets the locale to US
            // Calls a method to extract the date and compares it to the week of the passed in week number
            if (DateTimeConversions.extractDate(appointments.getStartDateTime()).get(WeekFields.of(locale)
                    .weekOfWeekBasedYear()) == m)
                getAllFilteredAppointments().add(appointments); // Adds matching appointments to list
        }
        if (getAllFilteredAppointments().isEmpty()) // Returns null if no appointments were found
            return null;
        else
            return getAllFilteredAppointments(); // Else, returns the list of weekly appointments


    }

    /** This method is used to get the currentWeek of the user
     *
     * @return a week number
     */
    public static int getCurrentWeek() {

        LocalDate localDate = LocalDate.now(); // Gets the local date of now
        Locale locale = Locale.US; // Sets the locale to US
        // Returns the current week number
        return localDate.get(WeekFields.of(locale).weekOfWeekBasedYear());
    }

    /** This method is used to get the currentMonth of the user
     *
     * @return the current month number
     */
    public static int getCurrentMonth() {

        LocalDate localDate = LocalDate.now(); // Gets the local date of now
        return localDate.getMonthValue(); // Returns the current month of the user
    }

    /** This method is used to confirm that the customer does not have an overlapping appointment
     *
     * @param customerID the ID of the customer
     * @param appointID the appointment ID used to make sure it is not the same appointment
     * @param t the startTime of the appointment
     * @return boolean value
     */
    public static boolean confirmStart(int customerID, int appointID, String t) {

        // Creates a pattern formatter for the utc string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Creates a variable to hold and convert the utc string to LocalDateTime variable
        LocalDateTime dateTime = LocalDateTime.parse(t, formatter);

        for (Appointments appointments : getAllAppointments()) { // Retrieves all appointments
            if (appointments.getCustomerID() == customerID) { // Gets appointments by customer ID
                // Creates a variable to hold the customer's startTimes
                LocalDateTime customerStart = LocalDateTime.parse(appointments.getStartDateTime(), formatter);
                // Creates a variable to hold the customer's endTime
                LocalDateTime customerEnd = LocalDateTime.parse(appointments.getEndDateTime(), formatter);
                // Verifies that the appointment is different and compares the appointments to the passed in appointment
                if ((!(appointments.getAppointmentID() == appointID)) && (dateTime.equals(customerStart)
                        || dateTime.isAfter(customerStart)) && dateTime.isBefore(customerEnd))
                    return false; // Returns false if an appointment is the same or overlaps
            }
        }
        return true; // If the appointment time is available

    }

    /**This method is used to confirm that the customer does not have an overlapping appointment
     *
     * @param i Id of the customer
     * @param appointID appointment ID to verify it is not the same appointment
     * @param t the endTime of the appointment
     * @return boolean value
     */
    public static boolean confirmEnd(int i, int appointID, String t) {

        // Creates a pattern formatter for the utc string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Creates a variable to hold and convert the utc string to LocalDateTime variable
        LocalDateTime dateTime = LocalDateTime.parse(t, formatter);

        for (Appointments appointments : getAllAppointments()) { // Gets all appointments
            if (appointments.getCustomerID() == i) { // Gets the appointments of the customer
                // Creates a variable to hold the customer's appointments startTimes
                LocalDateTime customerStart = LocalDateTime.parse(appointments.getStartDateTime(), formatter);
                // Creates a variable to hold the customer's appointments endTimes
                LocalDateTime customerEnd = LocalDateTime.parse(appointments.getEndDateTime(), formatter);
                // Verifies that the appointments is different and compares the dates
                if ((!(appointments.getAppointmentID() == appointID)) && dateTime.isAfter(customerStart) &&
                        (dateTime.isBefore(customerEnd) || dateTime.equals(customerEnd)))
                    return false; // Returns false if the appointment is the same or overlaps
            }
        }
        return true; // Returns true if the appointment time is available

    }

    /** This method is used to retrieve a contact's appointments by their contactID
     *
     * @param id the contact's ID
     * @return an observable list of the contact's appointments
     */
    public static ObservableList<Appointments> getContactAppointments(int id) {
        if (!(getAllFilteredAppointments()).isEmpty()) // Checks to see if the list is empty
            getAllFilteredAppointments().clear(); // If not, clears the list

        for (Appointments appointments : getAllAppointments()) // Gets all appointments
            if (appointments.getContactID() == id) // Finds the appointments of the contact's
                getAllFilteredAppointments().add(appointments); // Adds any appointments found to the list

        if (getAllFilteredAppointments().isEmpty()) // Returns null if no appointments were found
            return null;
        else
                return getAllFilteredAppointments(); // Else, returns the list of weekly appointments

    }

    /** This method is used to check if a user has any appointments within 15 minutes their login
     *
     * @param id the ID of the user
     * @return appointments if found, else null;
     */
    public static Appointments checkUserAppointments (int id) {

        for (Appointments appointments : getAllAppointments()) // Gets all appointments
            if (appointments.getUserID() == id) { // Gets the user's appointments
                // Creates a variable to hold the user's appointments startTimes
                LocalDateTime start = DateTimeConversions.toLDT(appointments.getStartDateTime());
                // Creates a variable to hold the current localDateTime of the user
                LocalDateTime ldt = LocalDateTime.now();
                // Creates a variable to utilize the ChronoUnit method to compare to times by minutes
                long timeDif = ChronoUnit.MINUTES.between(ldt, start);
                if (timeDif > 0 && timeDif <= 15) { // Checks to see if the time is less than or equal to 15 minutes
                    return appointments; // Returns an appointment if found
                }
            }
        return null; // Returns null if no appointments are found
    }

    /** This method is used to check if a customer has any appointments before they can be deleted from the database
     *
     * @param id the ID of the customer
     * @return boolean value
     */
    public static boolean checkCustomerAppointments (int id) {

        for (Appointments appointments: getAllAppointments()) // Gets all appointments
            if (appointments.getCustomerID() == id){ // Checks to see if the customer has appointments
                return true; // returns true if they have appointments
            }
        return false; // false if they don't
    }
    /**
     * This method is used to clear the allAppointments List
     */
    public static void clearAppointments() {
        if (!(getAllAppointments()).isEmpty()) // Checks to see if the list is empty
            getAllAppointments().clear(); // If not, clears the list
        DBAppointments.getAllAppointments(); // Calls on DB to retrieve updated list
    }
}



