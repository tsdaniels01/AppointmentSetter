package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

/** This class is used to construct an object of times to be populated and selected in combo boxes
 *
 */
public class Times {

    private String time; // Creates a variable time

    /** This method is a constructor for the times object
     *
     * @param time String parameter
     */
    public Times(String time) {
        this.time = time; // Assigns the parameter to the variable above with the same name

    }
    // Creates an observable list of Times objects
    public static ObservableList<Times> allTimes = FXCollections.observableArrayList();

    /** This method is used to return the allTimes observable list
     *
     * @return allTimes observable list
     */
    public static ObservableList<Times> getTimes() {return allTimes;}

    /** This method is used to add a Times object to the allTimes observable list
     *
     * @param times the Times object to be added
     */
    public static void addTimes (Times times)   {allTimes.add(times);}

    /** This method is used to add a group of Times objects to the allTimes observable list
     *
     */
    public static void entry(){

        if (allTimes.size() == 0){
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("01:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("02:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("03:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("04:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("05:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("06:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("07:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("08:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("09:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("10:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("11:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("12:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("13:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("14:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("15:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("16:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("17:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("18:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("19:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("20:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("21:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("22:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("23:00:00"));
            // Creates a Times object to be added to the list
            Times.addTimes(new Times("24:00:00"));
        }
    }

    /** This method is used to override the toString method to populate a combo box
     *
     * @return time toString
     */
    @Override
    public String toString()   {
        return time;
    }

    /** This method is a getter for the time variable
     *
     * @return time variable
     */
    public String getTime() {
        return time;
    }

    /** This method is a setter for the time variable
     *
     * @param time the time to be set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /** This method is used to return a time object of the string passed in
     *
     * @param t the string to be converted to a Times object
     * @return the Times object
     */
    public static Times time (String t) {
        // Retrieves all the allTimes observable list
        for (Times times: getTimes())
            // Finds the object that matched the string
            if (times.getTime().equals(t)){
                return times; // Returns the Times object of the string
            }
        return null; // Returns null if not located
    }

    /** This method is used to extract the time from a string and convert it to a Times object
     *
     * @param t the string to be extracted from
     * @return the Times object that is extracted
     */
    public static Times extractTime (String t) {

        LocalDateTime dateTime = DateTimeConversions.toLDT(t);
        // Creates a variable to hold the String version of the dateTime LocalDateTime
        String l = String.valueOf(dateTime.toLocalTime());
        // Creates a variable that appends the seconds to the end of the lt String for filtering purposes
        String lt = l + ":00";
        // Returns the allTimes observable list
        for (Times times: getTimes())
            if (times.getTime().equals(lt)){ // Finds the Times object of the lt string
                Times extractTime = times; // Creates a variable to hold the extracted Times object
                return extractTime; // returns the object
            }
        return null; // returns null if no object is found
    }

}
