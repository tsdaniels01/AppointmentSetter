package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/** This class is used to create an observable list of months to be displayed in a combo box
 *
 */
public class CalendarMonths {

    private String monthName; // Creates a variable of month names
    private int monthNumber; // Creates a variable of month numbers

    /** This method is a constructor for the calender months object and assigns the parameters to the variable above
     *
     * @param monthName is a String parameter
     * @param monthNumber is a integer parameter
     */
    public CalendarMonths(String monthName, int monthNumber) {
        this.monthName = monthName; // Assigns to the variable above with the same name
        this.monthNumber = monthNumber; // Assigns to the variable above with the same name
    }
    // Creates and holds an observable list of calendar months
    public static ObservableList<CalendarMonths> allCalendarMonths = FXCollections.observableArrayList();

    /**
     * This method is used to return the allCalendarMonths observable list
     * @return allCalendarMonths list
     */
    public static ObservableList<CalendarMonths> getAllCalendarMonths() {return allCalendarMonths;}

    /** This method is used to add a calendar month to the allCalendarMonths list
     *
     * @param calendarMonths the calendar month to be added
     */
    public static void addCalendarMonths (CalendarMonths calendarMonths) {allCalendarMonths.add(calendarMonths);}

    /** This method is used to add the calendar month objects to the allCalendarMonths observable list
     *
     */
    public static void entry(){

        if (allCalendarMonths.size() == 0) {
            // Calendar month object added to the allCalendarMonths observable list
            CalendarMonths.addCalendarMonths(new CalendarMonths("[Current Month]", 0));
            // Calendar month object added to the allCalendarMonths observable list
            CalendarMonths.addCalendarMonths(new CalendarMonths("January", 1));
            // Calendar month object added to the allCalendarMonths observable list
            CalendarMonths.addCalendarMonths(new CalendarMonths("February", 2));
            // Calendar month object added to the allCalendarMonths observable list
            CalendarMonths.addCalendarMonths(new CalendarMonths("March", 3));
            // Calendar month object added to the allCalendarMonths observable list
            CalendarMonths.addCalendarMonths(new CalendarMonths("April", 4));
            // Calendar month object added to the allCalendarMonths observable list
            CalendarMonths.addCalendarMonths(new CalendarMonths("May", 5));
            // Calendar month object added to the allCalendarMonths observable list
            CalendarMonths.addCalendarMonths(new CalendarMonths("June", 6));
            // Calendar month object added to the allCalendarMonths observable list
            CalendarMonths.addCalendarMonths(new CalendarMonths("July", 7));
            // Calendar month object added to the allCalendarMonths observable list
            CalendarMonths.addCalendarMonths(new CalendarMonths("August", 8));
            // Calendar month object added to the allCalendarMonths observable list
            CalendarMonths.addCalendarMonths(new CalendarMonths("September", 9 ));
            // Calendar month object added to the allCalendarMonths observable list
            CalendarMonths.addCalendarMonths(new CalendarMonths("October", 10));
            // Calendar month object added to the allCalendarMonths observable list
            CalendarMonths.addCalendarMonths(new CalendarMonths("November", 11));
            // Calendar month object added to the allCalendarMonths observable list
            CalendarMonths.addCalendarMonths(new CalendarMonths("December", 12));
        }
    }

    /** This method is used to override the toString method to populate a combo box
     *
     * @return monthName in String form
     */
    @Override
    public String toString(){
        return monthName;
    }

    /** This method is a getter for the monthName variable
     *
     * @return the monthName
     */
    public String getMonthName() {
        return monthName;
    }

    /** This method is a setter for the monthName variable
     *
     * @param monthName the monthName to be set
     */
    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    /** This method is a getter for the monthNumber variable
     *
     * @return the monthNumber
     */
    public int getMonthNumber() {
        return monthNumber;
    }

    /** This method is a setter for the monthNumber variable
     *
     * @param monthNumber the monthNumber to be set
     */
    public void setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
    }

}
