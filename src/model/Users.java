package model;

import dbCalls.DBUsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DateTimeConversions;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/** This class is a value object for the Users and has an observable list of users for POJO reference types
 * */
public class Users {

    private int userID; // Creates a variable named userID
    private String userName; // Creates a variable named userName
    private String password; // Creates a variable named password
    private String createDate; // Creates a variable named createDate
    private String createdBy; // Creates a variable named createdBy
    private String lastUpdate; // Creates a variable named lastUpdate
    private String lastUpdateBy; // Creates a variable named lastUpdateBy

    /**This method is a constructor for the Users object and assigns the parameters to the variable above
     *
     * @param userID int parameter
     * @param userName String parameter
     * @param password String parameter
     * @param createDate String parameter
     * @param createdBy String parameter
     * @param lastUpdate String parameter
     * @param lastUpdateBy String parameter
     */
    public Users(int userID, String userName, String password, String createDate, String createdBy, String lastUpdate,
                 String lastUpdateBy) {
        this.userID = userID; // Assigns parameter to the variable above with the same name
        this.userName = userName; // Assigns parameter to the variable above with the same name
        this.password = password; // Assigns parameter to the variable above with the same name
        this.createDate = createDate; // Assigns parameter to the variable above with the same name
        this.createdBy = createdBy; // Assigns parameter to the variable above with the same name
        this.lastUpdate = lastUpdate; // Assigns parameter to the variable above with the same name
        this.lastUpdateBy = lastUpdateBy; // Assigns parameter to the variable above with the same name
    }

    /** This method is an overloaded constructor. The purpose of this constructor is to store the current user's name
     *
     * @param name String parameter
     */
    public Users(String name){
        this.userName = name;
    }
    // Creates an observable arraylist of users
    public static ObservableList<Users> allUsers = FXCollections.observableArrayList();

    /** This method is used to retrieve the allUsers observable arraylist
     *
     * @return allUsers list
     */
    public static ObservableList<Users> getAllUsers() { return allUsers;}

    /** This method is used to add a new user to the allUsers list
     *
     * @param users the new user that is added
     */
    public static void addUsers(Users users)  {allUsers.add(users);}

    // Creates an observable list to hold the currentUser object to transfer between forms
    public static ObservableList<Users> currentUser = FXCollections.observableArrayList();

    /** This method is used to get the currentUser list
     *
     * @return the currentUser
     */
    public static ObservableList<Users> getCurrentUser() { return currentUser;}

    /** This method is used to add the currentUser to the currentUser list
     *
     * @param users the currentUser
     */
    public static void addCurrentUser(Users users)  {currentUser.add(users);}

    /** This method is used to override the toString command
     *
     * @return the userName
     */
    @Override
    public String toString() {
        return userName;
    }

    /** This method is a getter for the userID variable*/
    public int getUserID() {
        return userID;
    }
    /** This method is a setter for the userID variable*/
    public void setUserID(int userID) {
        this.userID = userID;
    }
    /** This method is a getter for the userID variable*/
    public String getUserName() {
        return userName;
    }
    /** This method is a setter for the userID variable*/
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /** This method is a getter for the userID variable*/
    public String getPassword() {
        return password;
    }
    /** This method is a setter for the userID variable*/
    public void setPassword(String password) {
        this.password = password;
    }
    /** This method is a getter for the userID variable*/
    public String getCreateDate() {
        return createDate;
    }
    /** This method is a setter for the userID variable*/
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    /** This method is a getter for the userID variable*/
    public String getCreatedBy() {
        return createdBy;
    }
    /** This method is a setter for the userID variable*/
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /** This method is a getter for the userID variable*/
    public String getLastUpdate() {
        return lastUpdate;
    }
    /** This method is a setter for the userID variable*/
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    /** This method is a getter for the userID variable*/
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }
    /** This method is a setter for the userID variable*/
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /** This method is used to retrieve the currentUser's name
     *
     * @return the currentUser name
     */
    public static String fetchCurrentUser () {

        for (Users users: getCurrentUser()) { //Gets the currentUser
            // Gets the currentUser name
            return users.getUserName();
        }
        return null; // Returns null if not found
    }

    /** This method is used to retrieve the currentUser's ID
     *
     * @return the currentUser ID
     */
    public static int fetchCurrentUserID () {

        for (Users users : getAllUsers()) // Gets all users
            if (users.getUserName().equals(fetchCurrentUser())){ //Locates the currentUser object
                return users.getUserID(); // Returns the currentUserID
        }
        return 0; // Returns zero if not located
    }

    /** This method is used to verify the user's credentials when logging in
     *
     * @param userName the userName to verify
     * @param password the password to verify
     * @return boolean value
     */
    public static boolean confirmUser (String userName, String password) {

        for (Users users : getAllUsers())  // Gets all users
            // Verifies username and password together
            if (users.getUserName().equals(userName) && users.getPassword().equals(password)) {
                return true; // true if found
            }
            return false; // false if not
        }


    /** This method is used to determine if a inputted userID is a valid user
     *
     * @param i the inputted ID
     * @return boolean value
     */
    public static boolean isUserValid (int i){

        for (Users users: getAllUsers() ) { // Gets all users
            if (users.getUserID() == i) // Looks for users
                return true; // True if found
        }
        return false; // false if not
    }

    /** This method is used to track a user's login attempts
     *
     * @param name the user's name
     * @param password the password to confirm the user
     * @throws IOException possible Input/Output exception
     */
    public static void userTracker (String name, String password) throws IOException {

        // Creates a new fileWriter variable and sets the save file
        FileWriter fw = new FileWriter("login_activity.txt", true);
        // Creates a new printWriter variable
        PrintWriter pw = new PrintWriter(fw);
        pw.println(""); // Prints space
        pw.print(name); // Prints the user's name
        pw.print(", "); // Prints space and comma
        LocalDateTime ldt = DateTimeConversions.getLdtUTC();
        pw.print(ldt.toLocalDate()); // Prints the datestamp in UTC
        pw.print(", "); // Prints space and comma
        pw.print(ldt.toLocalTime()); // Prints the timestamp in UTC
        pw.print(", "); // Prints space and comma
        if (Users.confirmUser(name, password) == true) // Prints if the login was succesful or not
        pw.print("Successful Attempt;");
        else
            pw.print("Failed Attempt;");
        pw.close(); // Closes the printWriter
    }
}

