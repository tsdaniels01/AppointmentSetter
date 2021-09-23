package dbCalls;

import javafx.collections.ObservableList;
import model.Countries;
import utilities.DBConnection;
import utilities.DBQuery;
import utilities.DateTimeConversions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/** This class is used to retrieve the countries table from the database and add it to an observable arraylist
 */
public class DBCountries {

    /** This method is used to retrieve the countries table from the database and add it to the allCountries list
     *
     * @return the allCountries observable arraylist
     */
    public static ObservableList<Countries> getAllCountries() {

        try { // Used in the event of an SQLException
            // Prepared Statement that selects all countries from the countries table
            String selectStatement = "SELECT * FROM countries";
            // Sets the database connection and prepared statement
            DBQuery.setPreparedStatement(DBConnection.getConnection(), selectStatement);
            // Creates a variable to hold the prepared statement
            PreparedStatement ps = DBQuery.getPreparedStatement();
            // Executes the database query
            ps.executeQuery();
            // Creates a variable to hold the results of the query
            ResultSet rs = ps.getResultSet();
            // Iteration loop to receive all of the countries from the countries table
            while (rs.next()) { // Loop to gather countries
                int countryId = rs.getInt("Country_ID"); // Variable to hold the Country ID
                String countryName = rs.getString("Country"); // Variable to hold the Country
                // Variable to hold the Create Date
                String date = DateTimeConversions.toDefault(rs.getString("Create_Date"));
                String createdBy = rs.getString("Created_By"); // Variable to hold the Created By
                // Variable to hold the Last Update
                String lastUpdate = DateTimeConversions.toDefault(rs.getString("Last_Update"));
                String updatedBy = rs.getString("Last_Updated_By"); // Variable to hold the Last Updated By
                // Creates a new Countries object and adds it to the Countries list
                Countries.addCountries(new Countries(countryId, countryName, date, createdBy, lastUpdate, updatedBy));
            }
        }
        catch ( SQLException e){ // Catches the SQLException and prints it to the stackTrace
            e.printStackTrace();
        }

        return Countries.allCountries; // Returns allCountries list when getAllCountries is called
    }
}




