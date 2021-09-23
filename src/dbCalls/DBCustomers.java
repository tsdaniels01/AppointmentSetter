package dbCalls;


import javafx.collections.ObservableList;
import model.Buyers;
import model.Customers;
import model.Sellers;
import utilities.DBConnection;
import utilities.DBQuery;
import utilities.DateTimeConversions;

import java.security.spec.ECField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static model.Customers.allCustomers;
import static model.Customers.getCustomers;

/** This class is used to retrieve the customers table from the database and add it to an observable arraylist. It
 * allows for creating, updating and deleting customer records directly from the database.
 */
public class DBCustomers  {


    /** This method is used to retrieve the customers table from the database and add it to the allCustomers list
     *
     * @return the allCustomers observable arraylist
     */
    public static ObservableList<Customers> getDBCustomers() {

        try { // Used in the event of an SQLException
            // Prepared Statement to select all customers from the customers table
            String selectStatement = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, " +
                    "customers.Postal_Code, customers.Phone, customers.Create_Date, customers.Created_By, " +
                    "customers.Last_Update, customers.Last_Updated_By, customers.Division_ID, customers.Type, customers.Property_Type, customers.Listing_ID, " +
                    "first_level_divisions.Country_ID, first_level_divisions.Division, " +
                    "countries.Country FROM customers, first_level_divisions," +
                    " countries WHERE customers.Division_ID = first_level_divisions.Division_ID AND " +
                    "first_level_divisions.Country_ID = countries.Country_ID;";

            // Sets the connection to the database and the prepared statement
            DBQuery.setPreparedStatement(DBConnection.getConnection(), selectStatement);
            // Creates a variable to hold the prepared statement
            PreparedStatement ps = DBQuery.getPreparedStatement();
            // Executes the database query
            ps.executeQuery();
            // Variable to hold the results of the database query
            ResultSet rs = ps.getResultSet();
            // Iteration loop to receive all of the customers from the customers table
            while (rs.next()) { // calls the result set
                int customerID = rs.getInt("Customer_ID"); // Variable to hold the Customer ID
                String customerName = rs.getString("Customer_Name"); // Variable to hold the Customer Name
                String address = rs.getString("Address"); // Variable to hold the Address
                String postalCode = rs.getString("Postal_Code"); // Variable to hold the Postal Code
                String phone = rs.getString("Phone"); // Variable to hold the phone
                // Variable to hold the Create Date
                String createDate = DateTimeConversions.toDefault(rs.getString("Create_Date"));
                String createdBy = rs.getString("Created_By"); // Variable to hold the Created By
                // Variable to hold the Last Update By
                String lastUpdate = DateTimeConversions.toDefault(rs.getString("Last_Update"));
                String lastUpdateBy = rs.getString("Last_Updated_By"); //Variable to hold the Last Updated By
                int divisionID = rs.getInt("Division_ID"); // Variable to hold the Division ID'
                // Variable to hold the firstLevelDivision
                String division = rs.getString("first_level_divisions.Division");
                // Variable to hold the countryID
                int countryID = rs.getInt ("first_level_divisions.Country_ID");
                // Variable to hold the country
                String country = rs.getString("countries.Country");
                String type = rs.getString("Type");
                String propertyType = rs.getString("Property_Type");
                String listingNumber = rs.getString("Listing_ID");
                // adds result set to new Customers objects
                if (type.equals("buyer")) {
                    Customers.addCustomers(new Buyers(customerID, customerName, address, postalCode, phone, createDate,
                            createdBy, lastUpdate, lastUpdateBy, divisionID, division, countryID, country, type, propertyType));
                }
                else
                    Customers.addCustomers(new Sellers(customerID, customerName, address, postalCode, phone, createDate,
                            createdBy, lastUpdate, lastUpdateBy, divisionID, division, countryID, country, type, listingNumber));
            }
        } catch (SQLException e) { // Catches the SQLException and prints it to the stackTrace
            e.printStackTrace();
        }
        return allCustomers; // returns allCustomers observable list
    }

    /** This method is used to create a new customer and insert it into the customer table in the database
     *
     * @param customers is the new customer to be inserted into the database
     */
    public static void addCustomers (Customers customers){

        try { // Used in the event of an SQL exception
            // Prepared SQL insert statement
            String insertStatement = "INSERT INTO customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, " +
                    " Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID, Type, Property_Type, Listing_ID)" +
                    " VALUES (Null,?,?,?,?,?,?," +
                    "?,?,?,?,?,?)";
            // Sets the prepared Statement and calls the database connection
            DBQuery.setPreparedStatement(DBConnection.getConnection(), insertStatement);
            // Creates a variable to hold the prepared statement
            PreparedStatement ps = DBQuery.getPreparedStatement();
            // Creates a variable to hold the customer name to be inserted
            String customerName = customers.getCustomerName();
            // Creates a variable to hold the address to be inserted
            String address = customers.getAddress();
            // Creates a variable to hold the postal code to be inserted
            String postalCode = customers.getPostalCode();
            // Creates a variable to hold the phone to be inserted
            String phone = customers.getPhone();
            // Creates a variable to hold the createDate to be inserted
            String createDate = DateTimeConversions.getUTC();
            // Creates a variable to hold the createdBy to be inserted
            String createdBy = customers.getCreatedBy();
            // Creates a variable to hold the lastUpdate to be inserted
            String lastUpdate = DateTimeConversions.getUTC();
            // Creates a variable to hold the lastUpdatedBy to be inserted
            String lastUpdateBy = customers.getLastUpdateBy();
            // Creates a variable to hold the divisionID to be inserted
            int divisionID = customers.getDivisionID();
            String type = customers.getType();
            String propertyType = customers.getPropertyType();
            String listingNumber = customers.getListingID();

            ps.setString(1, customerName);// Assigns customerName to index 1
            ps.setString(2, address); // Assigns address to index 2
            ps.setString(3, postalCode); // Assigns postalCode to index 3
            ps.setString(4, phone); // Assigns phone to index 4
            ps.setString(5, createDate); // Assigns createDate to index 5
            ps.setString(6, createdBy); // Assigns createdBy to index 6
            ps.setString(7, lastUpdate); // Assigns lastUpdate to index 7
            ps.setString(8, lastUpdateBy); // Assigns lastUpdateBy to index 8
            ps.setInt(9, divisionID); // Assigns divisionID to index 9
            ps.setString(10, type);
            ps.setString(11, propertyType);
            ps.setString(12, listingNumber);
            ps.execute(); // Executes the prepared statement
        }
        catch (SQLException e) { // Catches any SQL exceptions and prints it to the stack trace
            e.printStackTrace();
        }
    }

    /**
     * This method updates a preexisting customer in the customer table from the database based on Customer_ID;.
     * @param customers is the customer to be updated
     */
    public static void updateCustomers(Customers customers) {

        try { // Used in the event of an SQL exception
            // Prepared SQL update statement
            String updateStatement = "UPDATE customers SET Customer_Name = ?, Address = ?, " +
                    "Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ? , Division_ID = ?, Type = ?, Property_Type = ?, Listing_ID = ? " +
                    "WHERE Customer_ID = ?";
            // Sets the database call and prepared statement
            DBQuery.setPreparedStatement(DBConnection.getConnection(), updateStatement);
            // Creates a variable to hold the prepared statement
            PreparedStatement ps = DBQuery.getPreparedStatement();
            // Creates a variable to hold the customerID to be updated
            int customerId = customers.getCustomerID();
            // Creates a variable to hold the customerName be updated
            String customerName = customers.getCustomerName();
            // Creates a variable to hold the address to be updated
            String address = customers.getAddress();
            // Creates a variable to hold the postalCode to be updated
            String postalCode = customers.getPostalCode();
            // Creates a variable to hold the phone to be updated
            String phone = customers.getPhone();
            // Creates a variable to hold the lastUpdate to be updated
            String lastUpdate = DateTimeConversions.getUTC();
            // Creates a variable to hold the lastUpdatedBy to be updated
            String lastUpdatedBy = customers.getLastUpdateBy();
            // Creates a variable to hold the divisionID to be updated
            int divisionID = customers.getDivisionID();
            String type = customers.getType();
            String propertyType = customers.getPropertyType();
            String listingNumber = customers.getListingID();


            ps.setString(1, customerName);// Assigns the customerName to index 1
            ps.setString(2, address); // Assigns the address to index 2
            ps.setString(3, postalCode); // Assigns the postalCode to index 3
            ps.setString(4, phone); // Assigns the phone to index 4
            ps.setString(5, lastUpdate); // Assigns the lastUpdate to index 5
            ps.setString(6, lastUpdatedBy); // Assigns the lastUpdatedBy to index 6
            ps.setInt(7, divisionID); // Assigns the divisionID to index 7
            ps.setString(8, type);
            ps.setString(9, propertyType);
            ps.setString(10, listingNumber);
            ps.setInt(11, customerId); // Assigns the customerID to index 8
            // Executes the prepared Update statement
            ps.executeUpdate();

        }
        catch(Exception e){ // Catches any SQL exceptions and prints it to the stack trace
            e.printStackTrace();
        }
            }

    /**
     * This method deletes a customer from the customer table in the database.
     *
     * @param selectedCustomer is the customer to be deleted
     */
    public static void deleteCustomer(Customers selectedCustomer)  {

        try { // In the event of an SQL exception
            // Retrieves all customers from the allCustomers observable list
            for (Customers customers : getCustomers())
                // Finds the selectedCustomer in the observable list
                if (customers == selectedCustomer) {
                    // Assigns the found customerID to a local variable
                    int id = customers.getCustomerID();
                    // Prepared SQL delete statement
                    String deleteStatement = "DELETE FROM customers WHERE Customer_ID = ?";
                    // Sets the database connection and deleteStatement
                    DBQuery.setPreparedStatement(DBConnection.getConnection(), deleteStatement);
                    // Creates a variable to hold the prepared statement
                    PreparedStatement ps = DBQuery.getPreparedStatement();
                    // Creates a local variable to hold the customerID to be deleted
                    int customerID = id;
                    // Assigns the customerID to index 1
                    ps.setInt(1, customerID);
                    // Executes the prepared delete statement
                    ps.execute();
                }
        }
        catch (SQLException e){ // Catches any SQL exceptions and prints it to the stack trace
                e.printStackTrace();
            }

    }
}




