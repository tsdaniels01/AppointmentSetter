package utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/** This method is used to set a preparedStatement Query for the database
 *
 */
public class DBQuery {


    private static PreparedStatement statement; // Statement reference

    /** This method is used to set the connection and SQL statement
     *
     * @param conn the connection
     * @param sqlStatement prepared SQL statement
     * @throws SQLException possible exception
     */
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {

        statement = conn.prepareStatement(sqlStatement);
    }

    /** This method returns the statement object
     *
     * @return statement object
     */
    public static PreparedStatement getPreparedStatement() {
        return statement;
    }


}
