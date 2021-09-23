package model;

import dbCalls.DBContacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class is used as a value object for Contacts and provides POJO reference types.
 * The appointments are added to an observable arraylist from the database.
 * */
public class Contacts {

    private int contactID; // Creates a variable named contactID
    private String contactName; // Creates a variable named contactName
    private String email; // Creates a variable named email

    /**This is a constructor for the Contacts object and assigns the parameters to the variables above
     *
     * @param contactID int parameter
     * @param contactName String parameter
     * @param email String parameter
     */
    public Contacts(int contactID, String contactName, String email) {
        this.contactID = contactID; // Assigns parameter to the variable above with the same name
        this.contactName = contactName; // Assigns parameter to the variable above with the same name
        this.email = email; // Assigns parameter to the variable above with the same name
    }

    // Creates an observable arraylist of contacts retrieved from the contacts table in the database
    public static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    /** This method is used to retrieve the allContacts observable arraylist
     *
     * @return allContacts list
     */
    public static ObservableList<Contacts> getAllContacts() { return allContacts;}


    /** This method is used to add a new contact to the allContacts observable arraylist
     *
     * @param contacts the contact to be added
     */
    public static void addContacts(Contacts contacts)  {allContacts.add(contacts);}

    /** This method is used to override the toString command in order to populate a combo box
     *
     * @return contactName list
     */
    @Override
    public String toString(){
        return contactName;
    }

    /** This is a getter for the contactID variable*/
    public int getContactID() {
        return contactID;
    }
    /** This is a setter for the contactID variable*/
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
    /** This is a getter for the contactName variable*/
    public String getContactName() {
        return contactName;
    }
    /** This is a setter for the contactName variable*/
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /** This is a getter for the email variable*/
    public String getEmail() {
        return email;
    }
    /** This is a setter for the email variable*/
    public void setEmail(String email) {
        this.email = email;
    }

    /** This method is used to take a contact's name and locate their contactID number
     *
     * @param name the name of the contact
     * @return the contactID of the contact
     */
    public static int nameToId (String name){

        for (Contacts contacts: getAllContacts()) // Gets all contacts
            if (contacts.getContactName().equals(name)){ // Locates the contact by name
                int nameToId = contacts.getContactID(); // Gets the contact's ID number
                return nameToId; // The contactID
            }
        return 0; // returns if nothing is found
    }

    /** This method is used to locate a Contacts object based on contactID
     *
     * @param contactID the contact's ID number
     * @return the Contacts object
     */
    public static Contacts locateContact (int contactID){

        for (Contacts contacts: getAllContacts()) // Gets all contacts
            if (contacts.getContactID() == contactID) { // Locates the contact object by contactID
                Contacts locateContact = contacts; // Creates a variable object to hold the contact
                return locateContact; // Returns the contact object
            }
        return null; // Returns null if not found
    }

}
