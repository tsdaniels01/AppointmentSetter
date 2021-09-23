package dbCalls;

import javafx.collections.ObservableList;
import model.CustomerAppointments;
import model.Customers;
import utilities.DBConnection;
import utilities.DBQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is used to retrieve customer appointments from the appointments table and group them by type and month,
 * specifically for the customerAppointments report.
 */
public class DBCustomerAppointments {

    /** This method is used to retrieve the customer appointments and add them to an observable arraylist
     *
     * @return the allCustomerAppointments observable arraylist
     */
    public static ObservableList<CustomerAppointments> getAllCustomerAppointments() {

        try { // Used in the event of an SQLException
            // Prepared Statement to select all from the appointments table
            String selectStatement = "SELECT Customer_ID, Type, COUNT(appointments.Customer_ID), " +
                    "EXTRACT(MONTH FROM appointments.Start) \n" +
                    "FROM appointments\n" +
                    "GROUP By type, Customer_ID, EXTRACT(MONTH FROM appointments.Start) \n" +
                    "ORDER BY COUNT(appointments.Customer_ID) DESC;";
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
                // Creates a variable to hold the customerID
                int customerID = rs.getInt("Customer_ID");
                // Creates a variable to hold the customerName from the customerID
                String customerName = Customers.idToName(customerID);
                // Creates a variable to hold the type
                String type = rs.getString("Type");
                // Creates a variable to hold the month
                String month = rs.getString("EXTRACT(MONTH FROM appointments.Start)");
                // Creates a variable to hold the count
                int count = rs.getInt("COUNT(appointments.Customer_ID)");
                // Creates a new CustomerAppointment and adds it to the allCustomerAppointments list
                CustomerAppointments.addCustomerAppointments(new CustomerAppointments
                        (customerID, customerName, type, month, count));


            }
        } catch (SQLException e) { // Catches the SQLException and prints it to the stackTrace
            e.printStackTrace();
        }
        // Returns allCustomerAppointments list when getAllCustomers is called
        return CustomerAppointments.allCustomerAppointments;
    }
}
