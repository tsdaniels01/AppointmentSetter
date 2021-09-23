package dbCalls;

import javafx.collections.ObservableList;
import model.Users;
import utilities.DBConnection;
import utilities.DBQuery;
import utilities.DateTimeConversions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static model.Users.allUsers;

/** This class is used to retrieve the users table in the database and adds it to an observable arraylist
 */
public class DBUsers {

    /** This method is used to retrieve the Users table from the database and adds it to the
     * allUsers list
     * @return the allUsers observable arraylist
     */
    public static ObservableList<Users> getUsers() {

        try { // Used in the event of an SQLException
            // Prepared statement that selects all users from the users table
            String selectStatement = "SELECT * FROM users";
            // Sets the database connection and the prepared statement
            DBQuery.setPreparedStatement(DBConnection.getConnection(), selectStatement);
            // Creates a variable to hold the prepared statement
            PreparedStatement ps = DBQuery.getPreparedStatement();
            // Executes the database query
            ps.executeQuery();
            // Creates a variable to hold the results of the query
            ResultSet rs = ps.getResultSet();
            // Iteration loop to receive all users from the users table
            while (rs.next()) {
                int userID = rs.getInt("User_ID"); // Variable to hold User ID
                String username = rs.getString("User_Name"); // Variable to hold User Name
                String password = rs.getString("Password"); // Variable to hold Password
                // Variable to hold Create Date
                String createDate = DateTimeConversions.toDefault(rs.getString("Create_Date"));
                String createdBy = rs.getString("Created_By"); // Variable to hold Created By
                // Variable to hold Last Update
                String lastUpdate = DateTimeConversions.toDefault(rs.getString("Last_Update"));
                String lastUpdatedBy = rs.getString("Last_Updated_By"); // Variable to hold Last Updated By
                // Creates a new Users object and adds it to the Users list
                Users.addUsers(new Users(userID, username, password, createDate, createdBy, lastUpdate,
                        lastUpdatedBy));

            }
        }
        catch ( SQLException e){ // Catches the SQLException and prints it to the stackTrace
            e.printStackTrace();
        }

        return allUsers; // Returns allCustomers list when getAllCustomers is called
    }
}

