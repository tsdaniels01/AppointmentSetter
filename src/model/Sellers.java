package model;
/**
 * The Sellers class is inherited from the Customers class and implements the type and listingNumber to differentiate
 * between the Buyers class.
 * */
public class Sellers extends Customers {

    private String listingID;
    /**
     * This method is a constructor for the Sellers customer. This method inherits the abstract customers constructor
     * including the additional listingNumber parameter.
     * @param listingID appended parameter to the super class
     */
    public Sellers(int customerID, String customerName, String address, String postalCode, String phone,
                   String createDate, String createdBy, String lastUpdate, String lastUpdateBy, int divisionID,
                   String division, int countryID, String country, String type, String listingID) {
        super(customerID, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate,
                lastUpdateBy, divisionID, division, countryID, country, type);
        this.listingID = listingID;
    }

    /**
     * This method is a getter for the listingNumber
     * @return the listingNumber
     */
    public String getListingID() {
        return listingID;
    }

    /**
     * This method is a setter for the listingNumber
     * @param listingNumber to set
     */
    public void setListingID(String listingNumber) {
        this.listingID = listingNumber;
    }
}
