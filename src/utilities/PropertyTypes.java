package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivisions;

/**
 * This class creates and property type object and populates an observable arraylist of propertyTypes for a
 * comboBox
 */
public class PropertyTypes {

    protected String propertyType; // Creates a variable to hold the propertyType

    // Creates an observable list of propertyType objects
    public static ObservableList<PropertyTypes> allPropertyTypes = FXCollections.observableArrayList();

    /** This method is used to return the allPropertyTypes observable list
     *
     * @return allTimes observable list
     */
    public static ObservableList<PropertyTypes> getPropertyTypes() {return allPropertyTypes;}

    /** This method is used to add a Times object to the allTimes observable list
     *
     * @param propertyType the propertyType object to be added
     */
    public static void addPropertyTypes (PropertyTypes propertyType)   {allPropertyTypes.add(propertyType);}

    /**
     * This method is a constructor for the propertyTypes object
     * @param propertyType String parameter
     */
    public PropertyTypes(String propertyType) {
        this.propertyType = propertyType; // assign parameter to variable above
    }

    /**
     * This method is a getter for the propertyType
     * @return the propertyType
     */
    public String getPropertyType() {
        return propertyType;
    }

    /**
     * This method is a setter for the propertyType
     * @param propertyType to be set
     */
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    /**
     * This method overrides toString in order to populate a comboBox
     * @return
     */
    @Override
    public String toString() {
        return propertyType;
    }

    /**
     * This method inserts propertyType objects into the arraylist and is called in the main thread
     */
    public static void entry(){

        if (allPropertyTypes.size() == 0) {

            PropertyTypes.addPropertyTypes(new PropertyTypes("Office")); // adds object
            PropertyTypes.addPropertyTypes(new PropertyTypes("Retail"));  // adds object
            PropertyTypes.addPropertyTypes(new PropertyTypes("Industrial")); // adds object
            PropertyTypes.addPropertyTypes(new PropertyTypes("Multifamily")); // adds object
            PropertyTypes.addPropertyTypes(new PropertyTypes("Hotel")); // adds object
            PropertyTypes.addPropertyTypes(new PropertyTypes("Special Purpose")); // adds object
        }

    }

    /**
     * This method is used to return the propertyType object for a combo box when passing in the name
     * @param name the property type to locate
     * @return the propertyType object
     */
    public static PropertyTypes getPropertyObject(String name) {
        for (PropertyTypes propertyTypes : getPropertyTypes()) // Retrieves all property types
            if(propertyTypes.getPropertyType().equals(name)){ // locates current object
                return propertyTypes; // return object
            }
        return null; // if not return null
    }

}
