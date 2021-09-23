package model;

import dbCalls.DBCustomers;
/**
 * The Buyers class is inherited from the Customers class and implements the type and propertyType to differentiate
 * between the Sellers class.
 * */
public class Buyers extends Customers{

    private String propertyType; // Creates a variable to hold the propertyType

    /**
     * This method is a constructor for the Buyers customer. This method inherits the abstract customer constructor
     * including the additional propertyType parameter.
     *
     * @param propertyType the appended parameter to the super class
     */
    public Buyers(int customerID, String customerName, String address, String postalCode, String phone,
                  String createDate, String createdBy, String lastUpdate, String lastUpdateBy, int divisionID,
                  String division, int countryID, String country, String type, String propertyType) {
        super(customerID, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate,
                lastUpdateBy, divisionID, division, countryID, country, type);
        this.propertyType = propertyType; // Assigns the parameter to the variable above
    }

    /**
     * This is a getter for the propertyType
     * @return the propertyType
     */
    public String getPropertyType() {
        return propertyType;
    }

    /**
     * This is a setter for the propertyType
     * @param propertyType to set
     */
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
}
