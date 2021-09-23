package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;


/** This class is used to create a list of Calendar Week objects to be displayed in a combo box
 *
 */
public class CalendarWeeks {

    private String dates; // variable to hold the dates
    private int weekNumber; // variable to hold the weekNumber

    /** This method is a constructor for the CalendarWeeks object and assigns parameters
     *
     */
    public CalendarWeeks( String dates, int weekNumber) {
        this.dates = dates; // assigns parameter to variable above
        this.weekNumber = weekNumber; // assigns parameter to variable above
    }
    // Creates an observable list of Calendar week objects
    public static ObservableList<CalendarWeeks> allCalendarWeeks = FXCollections.observableArrayList();

    /** This method is used to retrieve the allCalendarWeeks observable list
     *
     * @return allCalendarWeeks observable list
     */
    public static ObservableList<CalendarWeeks> getAllCalendarWeeks() {return allCalendarWeeks;}

    /** This method adds a calendar week object to the allCalendarWeeks observable list of objects
     *
     * @param calendarWeeks the calendar week to be added
     */
    public static void addCalendarWeeks (CalendarWeeks calendarWeeks) {allCalendarWeeks.add(calendarWeeks);}

    /**
     * this method is a getter for the dates variable
     * @return the dates
     */
    public String getDates() {
        return dates;
    }

    /**
     * this method is a setter for the dates variable
     * @param dates the dates to be set
     */
    public void setDates(String dates) {
        this.dates = dates;
    }

    /**
     * this method is a getter for the weekNumber variable
     * @return the weekNumber
     */
    public int getWeekNumber() {
        return weekNumber;
    }

    /**
     * this method is a setter for the weekNumber variable
     * @param weekNumber the weekNumber to be set
     */
    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    /** This method is used to add a group of calendar week objects to the allCalendarWeeks observable list
     *
     */
    public static void entry(){

        if (allCalendarWeeks.size() == 0) {

            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks ("[Current week] " +
                    getDates(Appointments.getCurrentWeek()), Appointments.getCurrentWeek()));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(1), 1));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(2), 2));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(3), 3));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(4), 4));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(5),5));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(6),6));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(7),7));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(8),8));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(9),9));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(10),10));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(11),11));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(12),12));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(13),13));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(14),14));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(15),15));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(16),16));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(17),17));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(18),18));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(19),19));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(20),20));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(21),21));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(22),22));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(23),23));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(24),24));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(25),25));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(26),26));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(27),27));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(28),28));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(29),29));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(30),30));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(31),31));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(32),32));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(33),33));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(34),34));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(35),35));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(36),36));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(37),37));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(38),38));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(39),39));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(40),40));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(41),41));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(42),42));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(43),43));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(44),44));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(45),45));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(46),46));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(47),47));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(48),48));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(49),49));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(50),50));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(51),51));
            // Creates a calendar week object that is added to the allCalendarWeeks object
            CalendarWeeks.addCalendarWeeks(new CalendarWeeks(getDates(52),52));


        }
    }

    /** This method is used to override the toString method to populate a combo box
     *
     * @return week variable and week number variable toString
     */
    @Override
    public String toString(){
        return dates;
    }

    /**
     * This method is used to retrieve beginning and end dates based on the week number passed in
     * @param weekNumber the weekNumber
     * @return allDates String
     */
    public static String getDates(int weekNumber){
        LocalDate localDate = LocalDate.now(); // Variable to hold the currentDate
        int y = localDate.getYear(); // Variable to hold the current year
        int bDay = 1; // Variable to hold the beginning day, Sunday
        int eDay = 7; // Variable to hold the end day, Saturday
        Calendar calendar1 = Calendar.getInstance(); // creates a new instance of calendar
        calendar1.setWeekDate(y, weekNumber, bDay); // sets the calendar with variables
        // creates a variable to hold the localDate instance from calendar
        LocalDate localDate1 = LocalDateTime.ofInstant(calendar1.toInstant(),
                calendar1.getTimeZone().toZoneId()).toLocalDate();
        String beginDay = String.valueOf(localDate1.getDayOfMonth()); // variable to hold the beginDay
        String beginMonth = String.valueOf(localDate1.getMonth()); // variable to hold the beginMonth
        String beginYear = String.valueOf(localDate1.getYear()); // variable to hold the beginYear
        Calendar calendar2 = Calendar.getInstance(); // creates a new instance of calendar
        calendar2.setWeekDate(y, weekNumber, eDay); // sets the calendar with variables
        // creates a variable to hold the localDate instance from calendar
        LocalDate localDate2 = LocalDateTime.ofInstant(calendar2.toInstant(),
                calendar2.getTimeZone().toZoneId()).toLocalDate();
        String endDay = String.valueOf(localDate2.getDayOfMonth()); // variable to hold the endDay
        String endMonth = String.valueOf(localDate2.getMonth()); // variable to hold the endMonth
        String endYear = String.valueOf(localDate2.getYear()); // variable to hold the endYear
        // Concatenates all the strings together
        String allDates = beginMonth + " " +beginDay+ ", "+beginYear+ " - " +endMonth+ " "+endDay+ ", "+endYear;
        return allDates; // returns concatenation
    }
}
