package dbCalls;

import javafx.collections.ObservableList;
import model.FirstLevelDivisions;
import utilities.DBConnection;
import utilities.DBQuery;
import utilities.DateTimeConversions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is used to retrieve the first_Level_Divisions table from the database and adds it to an observable
 * arraylist
 */
public class DBFirstLevelDivisions {

    /** This method is used to retrieve the FirstLevelDivisions table from the database and adds it to the
     * allDivisions list
     * @return the allDivisions observable arraylist
     */
    public static ObservableList<FirstLevelDivisions> getAllFirstLevelDivisions() {

        try { // Used in the event of an SQLException
            // Prepared Statement that selects all firstLevelDivision from the firstLevelDivision table
            String selectStatement = "SELECT * FROM first_level_divisions";
            // Sets the database connection and the prepared statement
            DBQuery.setPreparedStatement(DBConnection.getConnection(), selectStatement);
            // Creates a variable to hold the prepared statement
            PreparedStatement ps = DBQuery.getPreparedStatement();
            // Executes the database query
            ps.executeQuery();
            // Creates a variable to hold the results of the query
            ResultSet rs = ps.getResultSet();
            // Iteration loop to receive all firstLevelDivisions from the firstLevelDivisions table
            while (rs.next()) { //
                int divisionID = rs.getInt("Division_ID"); // Variable to hold the Division ID
                String division = rs.getString("Division"); // Variable to hold the Division
                // Variable to hold the Create Date
                String createDate = DateTimeConversions.toDefault(rs.getString("Create_Date"));
                String createdBy = rs.getString("Created_By"); // Variable to hold the Created By
                // Variable to hold the Last Update
                String lastUpdate = DateTimeConversions.toDefault(rs.getString("Last_Update"));
                int countryID   = rs.getInt("Country_ID"); // Variable to hold the CountryID
                // Creates a new FirstLevelDivisions object and adds it to the FirstLevelDivisions list
                FirstLevelDivisions.addDivisions(new FirstLevelDivisions(divisionID, division, createDate,
                        createdBy, lastUpdate, countryID));
            }
        }
        catch ( SQLException e){ // Catches the SQLException and prints it to the stackTrace
            e.printStackTrace();
        }

        return FirstLevelDivisions.allDivisions; // Returns allCustomers list when getAllCustomers is called
    }
}


