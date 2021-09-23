package intefaces;

import java.time.LocalDateTime;

/** This class is used as a functional interface and allows for one abstract method which is used in this application
 * to format a String to a specific date or time pattern.
 */
public interface DateFormatting {

    LocalDateTime getLDT (String s);

}
