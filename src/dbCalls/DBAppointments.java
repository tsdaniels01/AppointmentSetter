package dbCalls;

import javafx.collections.ObservableList;
import model.Appointments;
import model.Users;
import utilities.DBConnection;
import utilities.DBQuery;
import utilities.DateTimeConversions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/** This class is used to retrieve the appointments table from the database and place it into an observable arraylist.
 * It allows for creating, updating and deleting appointments directly from the database.
 */
public class DBAppointments {

    /** This method is used to retrieve the appointments table from the database and adds it to the allAppointments
     * observable array list
     *
     * @return the allAppointments observable arraylist
     */
    public static ObservableList<Appointments> getAllAppointments() {

        try { // Used in the event of an SQLException
            // Prepared Statement to select all from the appointments table
            String selectStatement = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, " +
                    "Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, " +
                    "appointments.Contact_ID, contacts.Contact_Name FROM appointments, " +
                    "contacts WHERE appointments.Contact_ID = contacts.Contact_ID;";
            // Setting the database connection and prepared statement
            DBQuery.setPreparedStatement(DBConnection.getConnection(), selectStatement);
            // Variable to hold the prepared statement
            PreparedStatement ps = DBQuery.getPreparedStatement();
            // Executing the database query
            ps.executeQuery();
            // Variable to hold the results of the query
            ResultSet rs = ps.getResultSet();
            // Iteration loop to receive all appointments from the query
            while (rs.next()) { // Loop to gather appointments
                int appointmentID = rs.getInt("Appointment_ID"); // Variable to hold AppointmentID
                String title = rs.getString("Title"); // Variable to hold Title
                String description = rs.getString("Description"); // Variable to hold Description
                String location = rs.getString("Location"); // Variable to hold Location
                String type = rs.getString("Type"); // Variable to hold Type
                // Variable to hold Start
                String startDateTime = DateTimeConversions.toDefault(rs.getString("Start"));
                // Variable to hold End
                String endDateTime = DateTimeConversions.toDefault(rs.getString("End"));
                // Variable to hold CreateDate
                String createDate = DateTimeConversions.toDefault(rs.getString("Create_Date"));
                String createdBy = rs.getString("Created_By"); // Variable to hold CreatedBy
                // Variable to hold Last Update
                String lastUpdate = DateTimeConversions.toDefault(rs.getString("Last_Update"));
                String lastUpdateBy = rs.getString("Last_Updated_By"); // Variable to hold Last Updated By
                int customerID = rs.getInt("Customer_ID"); // Variable to hold Customer ID
                int userID = rs.getInt("User_ID"); // Variable to hold User ID
                int contactID = rs.getInt("Contact_ID"); // Variable to hold Contact ID
                String contactName = rs.getString("contacts.Contact_Name"); // Variable to hold the
                // contactName
                // Creates a new appointments object for each iteration and adds it to the Appointment list
                Appointments.addAppointments(new Appointments(appointmentID, title, description, location, type,
                        startDateTime, endDateTime, createDate, createdBy, lastUpdate, lastUpdateBy, customerID,
                        userID, contactID, contactName));
            }
        }
        catch ( SQLException e){ // Catches the SQLException and prints it to the stackTrace
            e.printStackTrace();
        }

        return Appointments.allAppointments; // Returns allCustomers list when getAllCustomers is called
    }
    /** This method is used to add Appointments to the appointments table in the database.
     *
     * @param appointments the appointment to be added
     */
    public static void addAppointments(Appointments appointments) {
        try { // In case of a possible SQLException
            // Creates a variable to hold the SQL insert statement
            String insertStatement = "INSERT INTO appointments(Appointment_ID, Title, Description, Location, Type, " +
                    "Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, " +
                    "Contact_ID)" + " VALUES (Null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            // Sets the database connection and insertStatement
            DBQuery.setPreparedStatement(DBConnection.getConnection(), insertStatement);
            // Creates a variable to hold the prepared statement
            PreparedStatement ps = DBQuery.getPreparedStatement();
            // Creates a variable to hold the title
            String title = appointments.getTitle();
            // Creates a variable to hold the description
            String description = appointments.getDescription();
            // Creates a variable to hold the location
            String location = appointments.getLocation();
            // Creates a variable to hold the type
            String type = appointments.getType();
            // Creates a variable to hold the startDateTime
            String startDateTime = DateTimeConversions.toUTC(appointments.getStartDateTime());
            // Creates a variable to hold the endDateTime
            String endDateTime = DateTimeConversions.toUTC(appointments.getEndDateTime());
            // Creates a varialble to hold the createDate
            String createDate = DateTimeConversions.getUTC();
            // Creates a variable to hold the createdBy
            String createdBy = Users.fetchCurrentUser();
            // Creates a variable to hold the lastUpdate
            String lastUpdate = DateTimeConversions.getUTC();
            // Creates a variable to hold the lastUpdateBy
            String lastUpdateBy = Users.fetchCurrentUser();
            // Creates a variable to hold the customerID
            int customerID = appointments.getCustomerID();
            // Creates a variable to hold the userID
            int userID = appointments.getUserID();
            // Creates a variable to hold the contactID
            int contactID = appointments.getContactID();

            ps.setString(1, title); // Sets the title to index 1
            ps.setString(2, description); // Sets the description to index 2
            ps.setString(3, location); // Sets the location to index 3
            ps.setString(4, type); // Sets the type to index 4
            ps.setString(5, startDateTime); // Sets the startDateTime to index 5
            ps.setString(6, endDateTime); // Sets the endDateTime to index 6
            ps.setString(7, createDate); // Sets the createDate to index 7
            ps.setString(8, createdBy); // Sets the createdBy index to 8
            ps.setString(9, lastUpdate); // Sets the lastUpdate to index 9
            ps.setString(10, lastUpdateBy); // Sets the lastUpdateBy to index 10
            ps.setInt(11, customerID); // Sets the customerID to index 11
            ps.setInt(12, userID); // Sets the userId to index 12
            ps.setInt(13, contactID); // Sets the contactID to index 13
            ps.execute(); // Executes the prepared statement
        }
        catch (SQLException e){ // Catches any possible SQLException and prints it to the stackTrace
            e.printStackTrace(); // Executes
        }
    }

    /**
     * This method updates a preexisting Appointment in the appointment table from the database based on
     * Appointment_ID;.
     * @param selectedAppointment is the appointment to be updated
     */
    public static void updateAppointments(Appointments selectedAppointment)  {

        try { // For any possible SQLExceptions that may occur
            // Creates a variable to hold the SQL prepared statement
            String updateStatement = "UPDATE appointments SET Title = ?, Description = ?, " +
                    "Location = ?, Type = ?, Start = ?, End = ? , Last_Update = ?, Last_Updated_By = ?, " +
                    "Customer_ID = ?, User_ID = ?, Contact_ID = ? " +
                    "WHERE Appointment_ID = ?";
            // Sets the database connection and preparedStatement
            DBQuery.setPreparedStatement(DBConnection.getConnection(), updateStatement);
            // Creates a variable to hold the prepared statement
            PreparedStatement ps = DBQuery.getPreparedStatement();
            // Creates a variable to hold the appointmentID
            int appointmentID = selectedAppointment.getAppointmentID();
            // Creates a variable to hold the title
            String title = selectedAppointment.getTitle();
            // Creates a variable to hold the description
            String description = selectedAppointment.getDescription();
            // Creates a variable to hold the location
            String location = selectedAppointment.getLocation();
            // Creates a variable to hold the type
            String type = selectedAppointment.getType();
            // Creates a variable to hold start
            String start = DateTimeConversions.toUTC(selectedAppointment.getStartDateTime());
            // Creates a variable to hold the end
            String end = DateTimeConversions.toUTC(selectedAppointment.getEndDateTime());
            // Creates a variable to hold the lastUpdate
            String lastUpdate = DateTimeConversions.getUTC();
            // Creates a variable to hold the lastUpdateBy
            String lastUpdatedBy = selectedAppointment.getLastUpdateBy();
            // Creates a variable to hold the customerID
            int customerID = selectedAppointment.getCustomerID();
            // Creates a variable to hold the userID
            int userID = selectedAppointment.getUserID();
            // Creates a variable to hold the contactID
            int contactID = selectedAppointment.getContactID();

            ps.setString(1,title); // Sets the title at index 1
            ps.setString(2, description); // Sets the description at index 2get
            ps.setString(3, location); // Sets the location at index 3
            ps.setString(4, type); // Sets the type at index 4
            ps.setString(5, start); // Sets the start at index 5
            ps.setString(6, end); // Sets the end at index 6
            ps.setString(7,lastUpdate); // Sets the lastUpdate at index 7
            ps.setString(8,lastUpdatedBy); // Sets the lastUpdatedBy at index 8
            ps.setInt(9, customerID); // Sets the customerID at index 9
            ps.setInt(10, userID); // Sets the userID at index 10
            ps.setInt(11, contactID); // Sets the contactID at index 11
            ps.setInt(12, appointmentID); // Sets the appointmentID at index 12
            ps.executeUpdate(); // Executes the update

    }
    catch(SQLException e){ // Catches any possible SQLExceptions
        e.printStackTrace(); // Executes printStackTrace

        }

    }

     /**
     * This method deletes an Appointment from the appointments table in the database.
     *
     * @param selectedAppointments is the appointment to be deleted
     */
    public static void deleteAppointments(Appointments selectedAppointments)  {

        try { // In the event of an SQL exception
            // Retrieves all appointments from the allAppointments observable list
            for (Appointments appointments : getAllAppointments())
                // Finds the selectedAppointment in the observable list
                if (appointments == selectedAppointments) {
                    // Assigns the found appointmentID to a local variable
                    int id = appointments.getAppointmentID();
                    // Prepared SQL delete statement
                    String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";
                    // Sets the database connection and deleteStatement
                    DBQuery.setPreparedStatement(DBConnection.getConnection(), deleteStatement);
                    // Creates a variable to hold the prepared statement
                    PreparedStatement ps = DBQuery.getPreparedStatement();
                    // Creates a local variable to hold the appointmentID to be deleted
                    int appointmentID = id;
                    // Assigns the appointmentID to index 1
                    ps.setInt(1, appointmentID);
                    // Executes the prepared delete statement
                    ps.execute();
                }
        }
        catch (SQLException e){ // Catches any SQL exceptions and prints it to the stack trace
            e.printStackTrace();
        }

    }
}


