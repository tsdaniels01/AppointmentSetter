package controller;

import dbCalls.DBCustomers;
import intefaces.Alertmessages;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import model.*;
import utilities.PropertyTypes;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


/** This class is a controller used to control and manage the CustomerForm functions. Its purpose is to allow the user
 * to manage customer records. The user can create, update or delete customer records as long as the customer does
 * not have a scheduled appointment.
 *
 */
public class BuyerFormController implements Initializable {

    /**
     * This method is used to pass in a message and provide an error alert with the inputted message.
     * *****************************************************************************************
     * LAMBDA EXPRESSION
     * *****************************************************************************************
     * This Lambda expressions implements the Alertmessages interface and is used 18 times in this class, which
     * improves this application by requiring less code needed for each method it is used in.
     */
    public static Alertmessages eAlert = (s) -> {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Creates a new alert
        alert.setTitle("Error Dialog"); // Sets the title of the alert
        alert.setContentText(s); // Accepts the passed in String as the message
        alert.showAndWait();
        return alert; // returns the alert
    };


    Stage stage; // Holds the stage
    Parent scene; // Holds the scene


    @FXML
    private TextField customerIdTxt; // TextField variable for the customerIdTxt

    @FXML
    private TextField customerNameTxt; // TextField variable for the customerNameTxt

    @FXML
    private TextField addressTxt; // TextField variable for the addressTxt

    @FXML
    private TextField postalCodeTxt; // TextField variable for the postalCodeTxt

    @FXML
    private TextField phoneTxt; // TextField variable for the phoneTxt

    @FXML
    private TextField searchTxt; // TextField variable for the searchTxt

    @FXML
    private ComboBox<Countries> countryCombo; // ComboBox variable to hold the countryCombo box

    @FXML
    private ComboBox<FirstLevelDivisions> firstLevelCombo; // ComboBox variable to hold the firstLevelCombo box

    @FXML
    private ComboBox<PropertyTypes> propertyTypeCombo; // ComboBox variable to hold the propertyTypeCombo box


    @FXML
    private TableView<Customers> customersTableView; // TableView variable to hold the customersTableView

    @FXML
    private TableColumn<Customers, Integer> customerIdCol; // TableColumn to hold the customerIDCol

    @FXML
    private TableColumn<Customers, String> customerNameCol; // TableColumn to hold the customerNameCol

    @FXML
    private TableColumn<Customers, String> addressCol; // TableColumn to hold the addressCol

    @FXML
    private TableColumn<Customers, String> postalCodeCol; // TableColumn to hold the postalCodeCol

    @FXML
    private TableColumn<Customers, String> phoneCol; // TableColumn to hold the phoneCol

    @FXML
    private TableColumn<Customers, String> countryCol; // TableColumn to hold the countryCol

    @FXML
    private TableColumn<Customers, String> firstLevelCol; // TableColumn to hold the firstLevelCol

    @FXML
    private TableColumn<Customers, String> propertyTypeCol; // TableColumn to hold the propertyTypeCol

    /**
     * This method is used to add a new customer to the allCustomers observable list
     *
     * @param event is a button
     */
    @FXML
    void onActionAdd(ActionEvent event) {

        try { // Possible exceptions that may occur
            if (!(customerIdTxt.getText().isEmpty())) {
                eAlert.getAlert("This customer already exists! Please click the Clear button and enter a " +
                        "new Customer."); // Alert with message
            } else if (customerNameTxt.getText().isEmpty()) { // Checks to see if the text box is empty
                eAlert.getAlert("Please enter a Customer Name!");// Alert with message

            } else if (addressTxt.getText().isEmpty()) { // Checks to see if the text box is empty
                eAlert.getAlert("Please enter an Address!");// Alert with message

            } else if (countryCombo.getSelectionModel().isEmpty()) { // Checks to see if the text box is empty
                eAlert.getAlert("Please enter a Country!");// Alert with message

            } else if (firstLevelCombo.getSelectionModel().isEmpty()) { // Checks to see if the text box is empty
                eAlert.getAlert("Please enter a Division!");// Alert with message

            } else if (postalCodeTxt.getText().isEmpty()) { // Checks to see if the text box is empty
                eAlert.getAlert("Please enter a Postal Code!");// Alert with message

            } else if (phoneTxt.getText().isEmpty()) { // Checks to see if the text box is empty
                eAlert.getAlert("Please enter a Phone Number!");// Alert with message

            } else {
                int id = 0; // Initializes variable id to zero
                String name = customerNameTxt.getText(); // Creates a variable to hold the customerName
                String address = addressTxt.getText(); // Creates a variable to hold the address
                String postalCode = postalCodeTxt.getText(); // Creates a variable to hold the postal code
                String phone = phoneTxt.getText(); // Creates a variable to hold the phone number
                String createDate = null; // Creates a null variable to fill createDate
                String createdBy = Users.fetchCurrentUser(); // Creates a variable to hold the createdBy
                String lastUpdate = null; // Creates a null variable to fill lastUpdate
                String lastUpdateBy = Users.fetchCurrentUser(); // Creates a variable named lastUpdateBy
                // Creates a variable to hold the divisionID by matching with divisionID in the Division object
                int divisionID = firstLevelCombo.getSelectionModel().getSelectedItem().getDivisionID();
                // Creates a variable to hold the division by matching with divisionName in the Division object
                String division = firstLevelCombo.getSelectionModel().getSelectedItem().getDivision();
                // Creates a variable to hold the countryID by matching it with the countryID of the Country Object
                int countryID = countryCombo.getSelectionModel().getSelectedItem().getCountryID();
                // Creates a variable to hold the country by matching it with the country of the country object
                String country = countryCombo.getSelectionModel().getSelectedItem().getCountryName();
                // Creates a new Customer object and adds it to the allCustomers observable list
                String type = "buyer";
                String propertyType = propertyTypeCombo.getSelectionModel().getSelectedItem().getPropertyType();
                Customers.createCustomers(new Buyers(id, name, address, postalCode, phone, null, createdBy,
                        null, lastUpdateBy, divisionID, division, countryID, country, type, propertyType));
                // Informs the user that the created customer has been saved
                JOptionPane.showMessageDialog(null, "The Customer has been saved!");
                customersTableView.getItems().clear(); // Clears the customersTableView after the event
                Customers.clearCustomers();
                Customers.getBuyers(); // Resets the customersTableView with the new list
                onActionClear(event); // Performs the onActionClear method to clear all selections and text boxes
            }
        }
        // Catches a possible exception and provides an Error message
        catch (Exception e) {
            eAlert.getAlert("You must enter a valid value in each field to create a Customer!"); // Alert with message

        }
    }

    /**
     * This method is used to send the user to the appointments form
     *
     * @param event is a button
     * @throws IOException a possible exception that may occur
     */
    @FXML
    void onActionAppointments(ActionEvent event) throws IOException {
        // Sets and loads the scene for the Appointments form
        stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method is used to send the user to the seller form
     *
     * @param event is a button
     * @throws IOException a possible exception that may occur
     */
    @FXML
    void onActionSellers(ActionEvent event) throws IOException {
        // Sets and loads the scene for the Appointments form
        stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/SellerForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method is used to clear any tableviews, textFields or comboBoxes
     *
     * @param event is a button
     */
    @FXML
    void onActionClear(ActionEvent event) {

        customerIdTxt.clear(); // Clears customersIdTxt
        customerNameTxt.clear(); // Clears customerNameTxt
        addressTxt.clear(); // Clears addressTxt
        postalCodeTxt.clear(); // Clears postalCodeTxt
        countryCombo.getSelectionModel().clearSelection(); // Clears countryCombo
        firstLevelCombo.getSelectionModel().clearSelection(); // Clears firstLevelCombo
        phoneTxt.clear(); // Clears phone
        customersTableView.getSelectionModel().clearSelection(); // Clears customersTableView
        propertyTypeCombo.getSelectionModel().clearSelection(); // Clears propertyTypeCombo
        searchTxt.clear(); // Clears search
        Customers.clearCustomers(); // Clears customerList for reset
        customersTableView.setItems(Customers.getBuyers()); // repopulates tableView

    }

    /**
     * This method is used to populate the firstLevelCombo with the firstLevelDivisions associated with the Country
     * selected.
     *
     * @param event is a combo box item selected
     */
    @FXML
    void onActionCountry(ActionEvent event) {
        // Checks to see if a country has been selected
        if (!(countryCombo.getSelectionModel().isEmpty())) {
            // Creates a variable to hold the countryID of the country selected
            int index = countryCombo.getSelectionModel().getSelectedItem().getCountryID();
            // Sets the items in the combo box based on countryID selected
            firstLevelCombo.setItems(FirstLevelDivisions.fdList(index));
        } else
            countryCombo.getSelectionModel().isEmpty(); // No action if countryCombo is empty
    }

    /**
     * This method is used to delete a customer from the allCustomers observable list
     *
     * @param event is a button
     */
    @FXML
    void onActionDelete(ActionEvent event) {


        if (customersTableView.getSelectionModel().isEmpty()) { // Checks to see if a selection has been made
            eAlert.getAlert("Please select a customer from the table to be deleted!"); // Alert with message

        } else {
            if (Appointments.checkCustomerAppointments(customersTableView.getSelectionModel().getSelectedItem()
                    .getCustomerID()) == true) {
                // Alert with message
                eAlert.getAlert("A Customer that has scheduled Appointments may not be deleted! " +
                        "Please delete any scheduled Appointments on the Appointments Form first.");
                // Clears the selection
                customersTableView.getSelectionModel().clearSelection();
            } else {
                // Confirms the deletion before proceeding
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will permanently delete the customer, " +
                        "Do you want to continue?");
                Optional<ButtonType> result = alert.showAndWait(); // To continue with the deletion
                if (result.isPresent() && result.get() == ButtonType.CANCEL) // Returns to form if deletion is cancelled
                    onActionClear(event); // Clears information if cancelled
                else if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Deletes a customer from the allCustomers observable list if proceeding
                    Customers.deleteCustomers(customersTableView.getSelectionModel().getSelectedItem());
                    JOptionPane.showMessageDialog(null, "The Customer has been deleted!");
                    onActionClear(event); // Clears the tableView
                    Customers.clearCustomers();
                    Customers.getBuyers(); // Resets the customersTableView with the new list
                }
            }
        }
    }

    /**
     * This method is used to exit the program
     *
     * @param event is a button
     */
    @FXML
    void onActionExit(ActionEvent event) {

        System.exit(0); // Exits the program
    }

    /**
     * This method is used to send the user to the reports form
     *
     * @param event is a button
     * @throws IOException a possible exception that may occur
     */
    @FXML
    void onActionReports(ActionEvent event) throws IOException {
        // Sets and loads the scene for the Reports form
        stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method takes a selected customer from the table and inserts it back into the textFields and combo boxes
     * so that it may be updated
     *
     * @param event is a button
     */
    @FXML
    void onActionUpdate(ActionEvent event) {

        try {
            int id = Integer.parseInt(customerIdTxt.getText()); // Creates variable to hold the customerID
            String name = customerNameTxt.getText(); // Creates a variable to hold customerName
            String address = addressTxt.getText(); // Creates a variable to hold the address
            String postalCode = postalCodeTxt.getText(); // Creates a variable to hold postalCode
            String phone = phoneTxt.getText(); // Creates a variable to hold the phone number
            String createDate = ""; // Creates a place holder variable for createDate
            String createdBy = Users.fetchCurrentUser(); // Creates a variable to hold the createdBy
            // Creates a place holder variable for lastUpdate which is retrieved before DB
            String lastUpdate = "";
            //insertion
            String lastUpdateBy = Users.fetchCurrentUser(); // Creates a variable to hold the lastUpdatedBy
            // Creates a variable to hold the divisionID by matching with divisionID in the Division object
            int divisionID = firstLevelCombo.getSelectionModel().getSelectedItem().getDivisionID();
            // Creates a variable to hold the division by matching with divisionName in the Division object
            String division = firstLevelCombo.getSelectionModel().getSelectedItem().getDivision();
            // Creates a variable to hold the countryID by matching it with the countryID of the Country Object
            int countryID = countryCombo.getSelectionModel().getSelectedItem().getCountryID();
            // Creates a variable to hold the country by matching it with the country of the country object
            String country = countryCombo.getSelectionModel().getSelectedItem().getCountryName();
            // Creates a new Customer object and adds it to the allCustomers observable list
            // Updates the customer
            String type = "buyer";
            String propertyType = propertyTypeCombo.getSelectionModel().getSelectedItem().getPropertyType();
            // Checks to see if the controls already have any information that needs to be saved before proceeding
            if (customerNameTxt.getText().isEmpty()) { // Checks to see if the text box is empty
                eAlert.getAlert("Please enter a Customer Name!");// Alert with message

            } else if (addressTxt.getText().isEmpty()) { // Checks to see if the text box is empty
                eAlert.getAlert("Please enter an Address!");// Alert with message

            } else if (countryCombo.getSelectionModel().isEmpty()) { // Checks to see if the text box is empty
                eAlert.getAlert("Please enter a Country!");// Alert with message

            } else if (firstLevelCombo.getSelectionModel().isEmpty()) { // Checks to see if the text box is empty
                eAlert.getAlert("Please enter a Division!");// Alert with message

            } else if (postalCodeTxt.getText().isEmpty()) { // Checks to see if the text box is empty
                eAlert.getAlert("Please enter a Postal Code!");// Alert with message

            } else if (phoneTxt.getText().isEmpty()) { // Checks to see if the text box is empty
                eAlert.getAlert("Please enter a Phone Number!");// Alert with message
            } else if (propertyTypeCombo.getSelectionModel().isEmpty()) {
                eAlert.getAlert("Please select a Property Type!");// Alert with message

            } else {

                Customers.updateCustomers(new Buyers(id, name, address, postalCode, phone, createDate, createdBy,
                        lastUpdate, lastUpdateBy, divisionID, division, countryID, country, type, propertyType));
                // Informs the user that the customer has been updated
                JOptionPane.showMessageDialog(null, "The updated Customer has been saved!");
                customersTableView.getItems().clear(); // Clears the customersTableView
                Customers.clearCustomers();
                Customers.getBuyers(); // Resets the customersTableView with the new list
                onActionClear(event); // Clears all
            }
        }

        // Catches a possible exception and provides an Error message
        catch (Exception e) {
            // Alert with message
            eAlert.getAlert("To update, first select a customer from the table by double clicking!");

        }
    }

    /**
     * This method is used to search for a customer by name of id and populates the customersTableView with the
     * results.
     * @param event search button
     */
    @FXML
    public void onActionSearch(ActionEvent event){

        String name = searchTxt.getText();
        if (name.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter a Customer ID or Name!");
            alert.showAndWait();
            customersTableView.setItems(Customers.getBuyers());
        }
        else {
            try {
                if (Pattern.matches("[a-zA-Z]+", name)) { // Determines if Text is a String
                    customersTableView.setItems(Customers.getBuyerByName(name)); // Searches by customer name
                    if (Customers.getSearchRecords().isEmpty()) {  // Error dialog if customer not found
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setContentText("No Buyer was found with that ID or Name!");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            searchTxt.clear(); // Clears text for next search
                            customersTableView.setItems(Customers.getBuyers());
                        }
                    }
                } else {
                    int id = Integer.parseInt(searchTxt.getText());
                    customersTableView.setItems(Customers.getBuyerByID(id));
                    if (Customers.getSearchRecords().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setContentText("No Buyer was found with that ID or Name!");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            searchTxt.clear(); // Clears text for next search
                            customersTableView.setItems(Customers.getBuyers());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * This method is used to get a selected customer from the tableview when a selection is double clicked. It then
     * passes the selected customer to the textViews to be edited and updated
     */
    public void getSelectedCustomer(){
        customersTableView.setRowFactory(tv -> {
            TableRow<Customers> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())) {
                    Customers customers = row.getItem();
                    // Sends the customerID to the customerID textField
                    customerIdTxt.setText(String.valueOf(customers.getCustomerID()));
                    // Sends the customerName to the customerNameTxt
                    customerNameTxt.setText(customers.getCustomerName());
                    // Sends the address to the addressTxt
                    addressTxt.setText(customers.getAddress());
                    // Sends the postalCode to the postalCodeTxt
                    postalCodeTxt.setText(customers.getPostalCode());
                    // Sends the phone to the phoneTxt
                    phoneTxt.setText(customers.getPhone());
                    // Sends the countryName to the countryCombo
                    countryCombo.setValue(Countries.idToName(customers.getCountryID()));
                    // Sends the division to the firstLevelCombo
                    firstLevelCombo.setValue(FirstLevelDivisions.idToName(customers.getDivisionID()));
                    // Sends the propertyType to the propertyTypeCombo
                    propertyTypeCombo.setValue(PropertyTypes.getPropertyObject(customers.getPropertyType()));
                }

            });
            return row;
        });
    }

    /**
     * This method is used to initialize any data when the form is loading and utilizes the @Override command.
     *
     * @param url to provide any pointer to the WWW if needed.
     * @param rb  in the event of a local specific resource need
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Retrieves all customers to set in the customersTable
        customersTableView.setItems(Customers.getBuyers());
        // Assigns customerID to the customerIDCol
        customerIdCol.setCellValueFactory((new PropertyValueFactory<>("customerID")));
        // Assigns customerName to the customerNameCol
        customerNameCol.setCellValueFactory((new PropertyValueFactory<>("customerName")));
        // Assigns address to the addressCol
        addressCol.setCellValueFactory((new PropertyValueFactory<>("address")));
        // Assigns postalCode to the postalCodeCol
        postalCodeCol.setCellValueFactory((new PropertyValueFactory<>("postalCode")));
        // Assigns phone to the phoneCol
        phoneCol.setCellValueFactory((new PropertyValueFactory<>("phone")));
        // Assigns division to the firstLevelCol
        firstLevelCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        // Assign country to the countryCol
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        // Assign propertyType to the propertyTypeCol
        propertyTypeCol.setCellValueFactory(new PropertyValueFactory<>("propertyType"));
        // Populates the countryCombo box with countries
        countryCombo.setItems(Countries.getCountries());
        // Populates the propertyTypeCombo box with propertyTypes
        propertyTypeCombo.setItems(PropertyTypes.getPropertyTypes());
        // Passes a customer to textViews for update
        getSelectedCustomer();
       }
    }





