package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class is a controller class and is used to control and handle the ReportForm. Its purpose is to provide three
 * reports for the user.
 *
 */
public class ReportFormController implements Initializable{

    Stage stage; // Creates a variable to hold the stage
    Parent scene; // Creates a variable to hold the scene

    // Creates a variable to hold the customerCombo
    @FXML
    private ComboBox<Customers> customerCombo;
    // Creates a variable to hold the customerAppointTableView
    @FXML
    private TableView<CustomerAppointments> customerAppointTableView;
    // Creates a variable to hold the customerIdCol
    @FXML
    private TableColumn<Integer, CustomerAppointments> customerIdCol;
    // Creates a variable to hold the customerNameCol
    @FXML
    private TableColumn<String, CustomerAppointments> customerNameCol;
    // Creates a variable to hold the customerMonthCol
    @FXML
    private TableColumn<String, CustomerAppointments> customerMonthCol;
    // Creates a variable to hold the customerTypeCol
    @FXML
    private TableColumn<String, CustomerAppointments> customerTypeCol;
    // Creates a variable to hold the customerCountCol
    @FXML
    private TableColumn<Integer, CustomerAppointments> customerCountCol;
    // Creates a variable to hold the contactCombo
    @FXML
    private ComboBox<Contacts> contactCombo;
    // Creates a variable to hold the contactScheduleTableView
    @FXML
    private TableView<Appointments> contactScheduleTableView;
    // Creates a variable to hold the appointIdCol
    @FXML
    private TableColumn<Integer, Appointments> appointIdCol;
    // Creates a variable to hold the titleCol
    @FXML
    private TableColumn<String, Appointments> titleCol;
    // Creates a variable to hold the typeCol
    @FXML
    private TableColumn<String, Appointments> typeCol;
    // Creates a variable to hold the
    @FXML
    private TableColumn<String, Appointments> descriptionCol;
    // Creates a variable to hold the descriptionCol
    @FXML
    private TableColumn<String, Appointments> startDateTimeCol;
    // Creates a variable to hold the endDateTimeCol
    @FXML
    private TableColumn<String, Appointments> endDateTimeCol;
    // Creates a variable to hold the contactCustomerIdCol
    @FXML
    private TableColumn<Integer, Appointments> contactCustomerIdCol;
    // Creates a variable to hold the userTableView

    /**This method is a button action event. It takes the selection of the contactCombo box from the user and populates
     * the contactScheduleTableView with the results.
     * @param event is a button
     */
    @FXML
    void onActionContactEnter(ActionEvent event)  {

        int id = contactCombo.getSelectionModel().getSelectedItem().getContactID();
        if (Appointments.getContactAppointments(id) == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment Alert"); // Error Title
            alert.setContentText("This Agent has no Appointments scheduled."); // Error message
            alert.showAndWait();
        }
        else
        // Takes the contactID selection from the user and populates the tableView with Appointments
        contactScheduleTableView.setItems(Appointments.getContactAppointments(id));
        // Assigns appointIdCol to the appointmentID variable
        appointIdCol.setCellValueFactory((new PropertyValueFactory<>("appointmentID")));
        // Assigns titleCol to the title variable
        titleCol.setCellValueFactory((new PropertyValueFactory<>("title")));
        // Assigns typeCol to the type variable
        typeCol.setCellValueFactory((new PropertyValueFactory<>("type")));
        // Assigns descriptionCol to the description variable
        descriptionCol.setCellValueFactory((new PropertyValueFactory<>("description")));
        // Assigns startDateTimeCol to the startDateTime variable
        startDateTimeCol.setCellValueFactory((new PropertyValueFactory<>("startDateTime")));
        // Assigns endDateTimeCol to the endDateTime variable
        endDateTimeCol.setCellValueFactory((new PropertyValueFactory<>("endDateTime")));
        // Assigns contactCustomerIdCol to the customerID variable
        contactCustomerIdCol.setCellValueFactory((new PropertyValueFactory<>("customerID")));

    }
    /** This method is used to transfer and load the AppointmentsForm
     *
     * @param event is a button
     * @throws IOException possible exception thrown
     */
    @FXML
    void onActionAppointments(ActionEvent event) throws IOException {

        stage = (Stage) ((javafx.scene.control.Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    /** This method is used to transfer and load the BuyerForm
     *
     * @param event is a button
     * @throws IOException possible exception thrown
     */
    @FXML
    void onActionBuyers(ActionEvent event) throws IOException{

        stage = (Stage) ((javafx.scene.control.Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/BuyerForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /** This method is used to transfer and load the SellerForm
     *
     * @param event is a button
     * @throws IOException possible exception thrown
     */
    @FXML
    void onActionSellers(ActionEvent event) throws IOException{

        stage = (Stage) ((javafx.scene.control.Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/SellerForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This method is a button action event. It takes the selection of the customerCombo box from the user and populates
     * the customerTableView with the results.
     * @param event is a button
     */
    @FXML
    void onActionCustomerEnter(ActionEvent event) {

        int id = customerCombo.getSelectionModel().getSelectedItem().getCustomerID();
        // Takes the contactID selection from the user and populates the tableView with Appointments
        if (CustomerAppointments.getCustomerAppointments(id) == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment Alert"); // Error Title
            alert.setContentText("This Customer has no Appointments scheduled."); // Error message
            alert.showAndWait();
        }
        customerAppointTableView.setItems(CustomerAppointments.getCustomerAppointments(id));
        // Assigns customerIdCol to the customerID variable
        customerIdCol.setCellValueFactory((new PropertyValueFactory<>("customerID")));
        // Assigns customerNameCol to the customerName variable
        customerNameCol.setCellValueFactory((new PropertyValueFactory<>("customerName")));
        // Assigns customerMonthCol to the month variable
        customerMonthCol.setCellValueFactory((new PropertyValueFactory<>("month")));
        // Assigns customerTypeCol to the type variable
        customerTypeCol.setCellValueFactory((new PropertyValueFactory<>("type")));
        // Assigns customerCountCol to the count variable
        customerCountCol.setCellValueFactory((new PropertyValueFactory<>("count")));
    }

    /** This method is used to exit the application
     *
     * @param event is a button
     */
    @FXML
    void onActionExit(ActionEvent event) {

        System.exit(0);

    }
    /**
     * This method is used to initialize any data when the form is loading and utilizes the @Override command.
     *
     * @param url to provide any pointer to the WWW if needed.
     * @param rb  in the event of a local specific resource need
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Sets the contactCombo box with contacts
        contactCombo.setItems(Contacts.getAllContacts());
        // Sets the customerCombo box with customers
        customerCombo.setItems(Customers.getCustomers());


    }
}

