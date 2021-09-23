package dbCalls;

import javafx.collections.ObservableList;
import model.Contacts;
import utilities.DBConnection;
import utilities.DBQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This class is used to retrieve the contacts table from the database and add it to an observable array list.
 *
 */
public class DBContacts {

    /** This method is used to retrieve the contacts table from the database and add it to the allContacts list.
     *
     * @return the allContacts observable arraylist
     */
    public static ObservableList<Contacts> getAllContacts() {

        try { // Used in the event of an SQLException
            // Prepared Statement to select all contacts from the contacts table
            String selectStatement = "SELECT * FROM contacts";
            // Setting the database connection and prepared statement
            DBQuery.setPreparedStatement(DBConnection.getConnection(), selectStatement);
            // Variable to hold the prepared statement
            PreparedStatement ps = DBQuery.getPreparedStatement();
            // Executing the database query
            ps.executeQuery();
            // Variable to hold the results of the query
            ResultSet rs = ps.getResultSet();
            // Iteration loop to receive all contacts from the query
            while (rs.next()) { // Loop to gather contacts
                int contactID = rs.getInt("Contact_ID"); // Variable to hold Contact ID
                String contactName = rs.getString("Contact_Name"); //Variable to hold Contact Name
                String email = rs.getString("Email"); // Variable to hold email
                // Creates a new Contacts object amd adds it to the Contacts list
                Contacts.addContacts(new Contacts(contactID, contactName, email));
            }
        }
        catch ( SQLException e){ // Catches the SQLException and prints it to the stackTrace
            e.printStackTrace();
        }

        return Contacts.allContacts; // Returns allCustomers list when getAllCustomers is called
    }

}

