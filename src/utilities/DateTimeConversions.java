package utilities;

import intefaces.DateFormatting;

import java.time.*;
import java.time.format.DateTimeFormatter;


/** This class is used to convert dates and times in and out of the database
 * */
public class DateTimeConversions {

    /**This method is used to format a String to a specific date and time pattern, then convert it into a
     * LocalDateTime for DateTimeConversions
     * *****************************************************************************************
     * LAMBDA EXPRESSION
     * *****************************************************************************************
     * This Lambda expressions implements the DateFormatting interface and is used seven times in this class, which
     * improves this application by requiring less code needed for each method it is used in.
     */
    public static DateFormatting date = (d) -> {
        // Creates a variable to hold the pattern to format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Creates a variable to hold the LocalDateTime formatted
        LocalDateTime startTime = LocalDateTime.parse(d, formatter);
        return startTime; // returns the LocalDateTime object
    };

    /** This method is used to convert the localDateTime to the UTC time zone
     *
     * @param t is the String to be converted
     * @return a String version of the LocalDateTime converted to UTC
     */
    public static String toUTC(String t){

        LocalDateTime dateTime = date.getLDT(t);
        // Creates a variable to hold the local Zone Id
        ZoneId localZoneID = ZoneId.systemDefault();
        // Creates a variable to combine LocalDateTime and Zone Id
        ZonedDateTime localZDT = ZonedDateTime.of(dateTime, localZoneID);
        // Creates a variable to hold the UTC Zone Id
        ZoneId utcZoneID = ZoneId.of("UTC");
        // Creates a variable to hold and convert the local ZonedDateTime to UTC
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(localZDT.toInstant(), utcZoneID);
        // Creates a variable to parse the UTC ZonedDateTime to LocalDateTime
        LocalDateTime utc = utcZDT.toLocalDateTime();
        // Creates a variable to parse the LocalDateTime to LocalTime
        LocalTime lt = utc.toLocalTime();
        // Creates a variable to parse the LocalDateTime to LocalDate
        LocalDate ld = utc.toLocalDate();
        String convertUTC = ld+" "+lt; // Creates a variable to add the two String versions together
        return convertUTC; // returns the string
    }

    /** This method is used to convert a UTC LocalDateTime string to the system Default time zone
     *
     * @param utc is the String to be converted
     * @return the converted String
     */
    public static String toDefault (String utc) {

        LocalDateTime dateTime = date.getLDT(utc);
        // Creates a variable to hold the UTC Zone Id
        ZoneId utcZoneID = ZoneId.of("UTC");
        // Creates a variable to combine the LocalDateTime with the Zone Id
        ZonedDateTime utcZDT = ZonedDateTime.of(dateTime, utcZoneID);
        // Creates a variable to hold the system Default Zone Id
        ZoneId localZoneID = ZoneId.systemDefault();
        // Creates a variable to hold and convert the UTC ZonedDateTime to the system default
        ZonedDateTime localZDT = ZonedDateTime.ofInstant(utcZDT.toInstant(), localZoneID);
        // Creates a variable to parse the ZonedDateTime to LocalDateTime
        LocalDateTime local = localZDT.toLocalDateTime();
        LocalDate ld = local.toLocalDate(); // Creates a variable to parse the LocalDateTime to LocalDate
        LocalTime lt = local.toLocalTime(); // Creates a variable to parse the LocalDateTime to LocalDate
        // Creates a variable to combine the two string versions and appends the seconds on the end for filtering
        String convertDefault = ld+" "+lt+":00";
        return convertDefault; // returns the converted string
    }

    /** This method converts a time string to EST time zone
     *
     * @param time the string to be converted
     * @return the converted string
     */
    public static LocalDateTime toEST (String time){

        LocalDateTime dateTime = date.getLDT(time);
        // Creates a variable to hold the system default zone Id
        ZoneId localZoneID = ZoneId.systemDefault();
        // Creates a variable to convert and hold the ZonedDateTime
        ZonedDateTime localZDT = ZonedDateTime.of(dateTime, localZoneID);
        // Creates a variable to hold the EST zone Id
        ZoneId estZoneID = ZoneId.of("US/Eastern");
        // Creates a variable to hold the ZonedDateTime converted to EST
        ZonedDateTime estZDT = ZonedDateTime.ofInstant(localZDT.toInstant(), estZoneID);
        // Creates a variable to parse the ZonedDateTime to LocalDateTime
        LocalDateTime est = estZDT.toLocalDateTime();
        return est; // returns the converted LocalDateTime

    }

    /** This method is used to get the current LocalDateTime converted to UTC
     *
     * @return converted string
     */
    public static String getUTC() {
        // Creates a variable of the current ZonedDateTime
        ZonedDateTime local = ZonedDateTime.now();
        // Creates a variable to hold the UTC zone Id
        ZoneId utcZoneId = ZoneId.of("UTC");
        // Creates a variable to hold the ZonedDateTime converted to UTC
        ZonedDateTime utc = ZonedDateTime.ofInstant(local.toInstant(), utcZoneId);
        // Creates a variable to parse the ZonedDateTime to LocalDateTime
        LocalDateTime localDateTime = utc.toLocalDateTime();
        // Creates a string value of the LocalDateTime
        String getUTC = String.valueOf(localDateTime);
        return getUTC; // returns the converted String

    }

    /** This method is used to get the LocalDateTime of the default timeZone and convert it to UTC
     *
     * @return the LocalDateTime converted to UTC
     */
    public static LocalDateTime getLdtUTC() {
        // Creates a variable of the current ZonedDateTime
        ZonedDateTime local = ZonedDateTime.now();
        // Creates a variable to hold the UTC zone Id
        ZoneId utcZoneId = ZoneId.of("UTC");
        // Creates a variable to hold the ZonedDateTime converted to UTC
        ZonedDateTime utc = ZonedDateTime.ofInstant(local.toInstant(), utcZoneId);
        // Creates a variable to parse the ZonedDateTime to LocalDateTime
        LocalDateTime localDateTime = utc.toLocalDateTime();
        return localDateTime; // returns the converted String

    }
    /** This method check to see if a time falls in between the businesses hours of operation in EST
     *
     * @param s the time to be checked
     * @return boolean
     */
    public static boolean openHours (String s){

        LocalDateTime estLDT = toEST(s); // Creates a variable to hold the converted String to EST
        LocalDate ld = estLDT.toLocalDate(); // Creates a variable to hold the LocalDate of estLDT
        LocalTime openT = LocalTime.of(8, 0); // Creates a variable to set the beginning hours of business
        LocalTime closedT = LocalTime.of(22, 0); // Creates a variable to set the closing hours of business
        // Creates a variable to hold the LocalDateTime of the open hours
        LocalDateTime open = LocalDateTime.of(ld, openT);
        // Creates a variable to hold the LocalDateTime of the closed hours
        LocalDateTime closed = LocalDateTime.of(ld, closedT);
         if (estLDT.isBefore(open) || estLDT.isAfter(closed) ) {// Verifies the time is between the open and close hours
             return false; // False if the hours are not during business hours
         }
         return true; // True if they are
    }

    /** This method is used to extract the LocalDate from a string
     *
     * @param t the string to be extracted from
     * @return the extracted LocalDate
     */
    public static LocalDate extractDate (String t){

        LocalDateTime dateTime = date.getLDT(t);
        // Creates a variable to parse the LocalDateTime to a LocalDate
        LocalDate ld = dateTime.toLocalDate();
        return ld; // returns the extracted date

    }

    /** This method is used to convert two Strings to LocalDateTime and determine if they are current dates and times
     *
     * @param s the startTime
     * @param e the endTime
     * @return boolean value
     */
    public static boolean confirmDateTime (String s, String e){

        LocalDateTime startTime = date.getLDT(s); // Created variable to hold the converted String to LocalDateTime
        LocalDateTime endTime = date.getLDT(e); // Created variable to hold the converted String to LocalDateTime

        // Checks to see if the times are current
        if (startTime.isBefore(LocalDateTime.now()) || endTime.isBefore(LocalDateTime.now())){
            return false; // Returns false if they are not current
        }
        // Checks to see if the endTime is before the startTime
        else if (endTime.isBefore(startTime)){
            return false; // Returns false if the endTime is before
        }
        return true; // Returns true if the times are current and the startTime is before the endTime

    }

    /** This method is used to convert a String to the desired date and time pattern and utilizes the DateFormatting
     * interface.
     * @param s the String to convert
     * @return the converted LocalDateTime
     */
    public static LocalDateTime toLDT (String s){

        return date.getLDT(s); // DateFormatting interface
    }
}



