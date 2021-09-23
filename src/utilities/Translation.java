package utilities;

import java.util.Locale;
import java.util.ResourceBundle;

/** This class is used to create an object for translating english to french
 *
 */
public class Translation {

    /** This method takes a string and converts it to french based from a resource bundle
     *
     * @param key The String that is to be translated
     * @return the String after being converted to french
     */
    public static String translation(String key) {

        // Resource bundle for french translations
        ResourceBundle rb = ResourceBundle.getBundle("utilities/Nat", Locale.getDefault());
        String newKey = rb.getString(key); // Creates a translated variable
        return newKey; // Returns translated variable
    }

}

