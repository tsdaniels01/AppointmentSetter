package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** This method is used to establish or close the connection to the database.
 *
 */
public class  DBConnection {

        // JDBC URL parts
        private static final String protocol = "jdbc";
        private static final String vendorName = ":mysql:";
        private static final String ipAddress = "//wgudb.ucertify.com:3306/";
        private static final String dbName = "WJ06J1L";
        private static final String jbdcURL = protocol + vendorName + ipAddress + dbName;

        // Driver Interface Reference
        private static final String MYSQLJBDCDriver = "com.mysql.cj.jdbc.Driver";
        // Connection Interface
        private static Connection conn = null;
        // Username
        private static final String username = "";
        // Password
        private static final String password = "";

        // Setting up Database Connection
        public static Connection getConnection() {

            try { // Possible exception
                Class.forName(MYSQLJBDCDriver); // Sets the driver interface
                conn = DriverManager.getConnection(jbdcURL, username, password);
                // Catches any possible exceptions and prints to the stackTrace
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return conn; // returns the connection to the database
        }
    // Closing Database connection
        public static void closeConnection() {
            try{ // Possible exception
                conn.close(); // Closes the connection
            }
            // Catches any possible exceptions
            catch (SQLException e){

            }
            DBConnection.closeConnection(); // Executes
        }

}





