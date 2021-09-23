package controller;


import intefaces.Alertmessages;
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
import utilities.CalendarMonths;
import utilities.CalendarWeeks;
import utilities.DateTimeConversions;
import utilities.Times;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class is a controller used to control and manage the AppointmentsForm. Its purpose is to allow the user to
 * manage appointments. The user can create, update or delete appointments as well as view all appointments, by week
 * or month.
 *
 */
public class AppointmentFormController implements Initializable {

    /**
     * This method is used to pass in a message and provide an error alert with the inputted message.
     * *****************************************************************************************
     * LAMBDA EXPRESSION
     * *****************************************************************************************
     * This Lambda expressions implements the Alertmessages interface and is used 39 times in this class, which
     * improves this application by requiring less code needed for each method it is used in.
     */
    public static Alertmessages eAlert = (s) -> {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Creates a new alert
        alert.setTitle("Error Dialog"); // Alert title
        alert.setContentText(s); // Accepts the passed in String as the message
        alert.showAndWait();
        return alert; // Returns the alert
    };

    Stage stage; // Creates a variable to hold the stage
    Parent scene; // Creates a variable to hold the scene

    @FXML
    private Tab allTab; // Assigns Tab to a variable

    @FXML
    private TableView<Appointments> allTableView; // Tableview variable to hold the allTableView

    @FXML
    private TableColumn<Appointments, Integer> appointIdCol; // TableColumn variable to hold appointIdCol

    @FXML
    private TableColumn<Appointments, String> titleCol; // TableColumn variable to hold titleCol

    @FXML
    private TableColumn<Appointments, String> descriptionCol; // TableColumn variable to hold descriptionCol

    @FXML
    private TableColumn<Appointments, String> locationCol; // TableColumn variable to hold locationCol

    @FXML
    private TableColumn<Appointments, Integer> contactCol; // TableColumn variable to hold contactCol

    @FXML
    private TableColumn<Appointments, String> typeCol; // TableColumn variable to hold typeCol

    @FXML
    private TableColumn<Appointments, String> startDateTimeCol; // TableColumn variable to hold StartDateTimeCol

    @FXML
    private TableColumn<Appointments, String> endDateTimeCol; // TableColumn variable to hold endDateTimeCol

    @FXML
    private TableColumn<Appointments, Integer> customerIdCol; // TableColumn variable to hold customerIdCol

    @FXML
    private Tab weeklyTab; // Tab variable to hold the weeklyTab

    @FXML
    private ComboBox<CalendarWeeks> weekCombo; // ComboBox variable to hold the weekCombo

    @FXML
    private TableView<Appointments> weeklyTableView; // Tableview variable to hold the weeklyTableView

    @FXML
    private TableColumn<Appointments, String> weekStartDateTimeCol; // TableColumn variable to hold weekStartDateTimeCol

    @FXML
    private TableColumn<Appointments, String> weekEndDateTimeCol; // TableColumn variable to hold weekEndDateTimeCol

    @FXML
    private TableColumn<Appointments, Integer> weekAppointIdCol; // TableColumn variable to hold weekAppointIdCol

    @FXML
    private TableColumn<Appointments, String> weekTitleCol; // TableColumn variable to hold weekTitleCol

    @FXML
    private TableColumn<Appointments, String> weekDescriptionCol; // TableColumn variable to hold weekDescriptionCol

    @FXML
    private TableColumn<Appointments, String> weekLocationCol; // TableColumn variable to hold weekLocationCol

    @FXML
    private TableColumn<Appointments, String> weekContactCol; // TableColumn variable to hold weekContactCol

    @FXML
    private TableColumn<Appointments, String> weekTypeCol; // TableColumn variable to hold weekTypeCol

    @FXML
    private TableColumn<Appointments, Integer> weekCustomerIdCol; // TableColumn variable to hold weekCustomerIdCol

    @FXML
    private Tab monthlyTab; // Tab variable to hold monthlyTab

    @FXML
    private ComboBox<CalendarMonths> monthCombo; // ComboBox variable to hold the monthCombo

    @FXML
    private TableView<Appointments> monthlyTableView; // Tableview variable to hold the monthlyTableView

    @FXML
    private TableColumn<Appointments, String> monthStartDateTimeCol;//TableColumn variable to hold monthStartDateTimeCol

    @FXML
    private TableColumn<Appointments, String> monthEndDateTimeCol; // TableColumn variable to hold monthEndDateTimeCol

    @FXML
    private TableColumn<Appointments, String> monthAppointIdCol; // TableColumn variable to hold monthAppointIdCol

    @FXML
    private TableColumn<Appointments, String> monthTitleCol; // TableColumn variable to hold monthTitleCol

    @FXML
    private TableColumn<Appointments, String> monthDescriptionCol; // TableColumn variable to hold monthDescriptionCol

    @FXML
    private TableColumn<Appointments, String> monthLocationCol; // TableColumn variable to hold monthLocationCol

    @FXML
    private TableColumn<Appointments, String> monthContactCol; // TableColumn variable to hold monthContactCol

    @FXML
    private TableColumn<Appointments, String> monthTypeCol; // TableColumn variable to hold monthTypeCol

    @FXML
    private TableColumn<Appointments, String> monthCustomerCol; // TableColumn variable to hold monthCustomerCol

    @FXML
    private TextField appointIdTxt; // TextField variable to hold appointIdTxt

    @FXML
    private TextField titleTxt; // TextField variable to hold titleTxt

    @FXML
    private TextField descriptionTxt; // TextField variable to hold descriptionTxt

    @FXML
    private TextField locationTxt; // TextField variable to hold locationTxt

    @FXML
    private ComboBox<Contacts> contactCombo; // ComboBox variable to hold contactCombo

    @FXML
    private TextField typeTxt; // TextField variable to hold typeTxt

    @FXML
    private DatePicker startDatePick; // DatePicker variable to hold startDatePick

    @FXML
    private DatePicker endDatePick; // DatePicker variable to hold endDatePick

    @FXML
    private TextField userIdTxt; // TextField variable to hold userIdTxt

    @FXML
    private ComboBox<Times> startTimeCombo; // ComboBox variable to hold startTimeCombo

    @FXML
    private ComboBox<Times> endTimeCombo; // ComboBox variable to hold endTimeCombo

    @FXML
    private ComboBox<Customers> customerCombo; // ComboBox variable to hold customerCombo

    /**
     * This method is used to collect data from the controls and creates a new Appointment
     *
     * @param event is a button
     */
    @FXML
    void onActionAdd(ActionEvent event) {

        try { // Used in the event of a possible exception
            int appointmentID = 0; // Creates a temporary variable to store in the constructor
            String title = titleTxt.getText(); // Creates a variable to hold the titleTxt
            String description = descriptionTxt.getText(); // Creates a variable to hold descriptionTxt
            String location = locationTxt.getText(); // Creates a variable to hold locationTxt
            String type = typeTxt.getText(); // Creates a variable to hold typeTxt
            LocalDate date = startDatePick.getValue(); // Creates a variable to hold startDatePick
            // Creates a variable to hold startTime
            String startTime = startTimeCombo.getSelectionModel().getSelectedItem().getTime();
            String startDateTime = date + " " + startTime; // Creates a variable to combine date and startTime
            LocalDate eDate = endDatePick.getValue(); // Creates a variable to hold eDate
            // Creates a variable to hold the endTime
            String endTime = endTimeCombo.getSelectionModel().getSelectedItem().getTime();
            String endDateTime = eDate + " " + endTime; // Creates a variable to combine eDate and endTime
            System.out.println(endDateTime);
            String createDate = ""; // Creates an empty String to pass through the constructor
            String createdBy = ""; // Creates an empty String to pass through the constructor
            String lastUpdate = ""; // Creates an empty String to pass through the constructor
            String lastUpdateBy = ""; // Creates an empty String to pass through the constructor
            // Creates a variable to hold the contactID
            int contactID = Contacts.nameToId(contactCombo.getSelectionModel().getSelectedItem().getContactName());
            // Creates a variable to hold the customerID
            int customerID = customerCombo.getSelectionModel().getSelectedItem().getCustomerID();
            int userID = Integer.parseInt(userIdTxt.getText()); // Creates a variable to hold the userID
            // Creates a variable to hold the contact
            String contact = contactCombo.getSelectionModel().getSelectedItem().getContactName();
            // Checks to see if the textField is empty, if so provides an alert message
            if (!(appointIdTxt.getText().isEmpty())) {
                // Alert and message
                eAlert.getAlert("This Appointment already exists! Please click the Clear button and enter" +
                        " a new Appointment or click the Save button to update the pre-existing Appointment.");
            }// Checks to see if the textField is empty, if so provides an alert message
            else if (titleTxt.getText().isEmpty()) {
                eAlert.getAlert("Please enter a Title!");// Alert and message

            } // Checks to see if the textField is empty, if so provides an alert message
            else if (descriptionTxt.getText().isEmpty()) {
                eAlert.getAlert("Please enter a Description!");// Alert and message

            } // Checks to see if the textField is empty, if so provides an alert message
            else if (locationTxt.getText().isEmpty()) {
                eAlert.getAlert("Please enter a Location!");// Alert and message

            } // Checks to see if a selection has not been made, if so provides an alert message
            else if (contactCombo.getValue() == null) {
                eAlert.getAlert("Please select a Contact!");// Alert and message

            } // Checks to see if the textField is empty, if so provides an alert message
            else if (typeTxt.getText().isEmpty()) {
                eAlert.getAlert("Please enter a Type!");// Alert and message

            } // Checks to see if a selection has not been made, if so provides an alert message
            else if (startDatePick.getValue() == null) {
                eAlert.getAlert("Please select a Start Date!");// Alert and message

            } // Checks to see if a selection has not been made, if so provides an alert message
            else if (startTimeCombo.getValue() == null) {
                eAlert.getAlert("Please select a Start Time!");// Alert and message

            } // Checks to see if a selection has not been made, if so provides an alert message
            else if (endDatePick.getValue() == null) {
                eAlert.getAlert("Please select an End Date!");// Alert and message

            } // Checks to see if a selection has not been made, if so provides an alert message
            else if (endTimeCombo.getValue() == null) {
                eAlert.getAlert("Please select an End Time!");// Alert and message

            } // Checks to see if the textField is empty, if so provides an alert message
            else if (customerCombo.getValue() == null) {
                eAlert.getAlert("Please enter a Customer ID!");// Alert and message

            }// Checks to see if the textField is empty, if so provides an alert message
            else if (userIdTxt.getText().isEmpty()) {
                eAlert.getAlert("Please enter a User ID!");// Alert and message

            }
            // Checks to see if the dates are current, if not gives an alert
            else if (DateTimeConversions.confirmDateTime(startDateTime, endDateTime) == false) {
                eAlert.getAlert("Please select current Dates and Times!");// Alert and message
                startDatePick.setValue(null); // Clears datePicker for new selection
                startTimeCombo.getSelectionModel().clearSelection(); // Clears comboBox for new selection
                endDatePick.setValue(null); // Clears datePicker for new selection
                endTimeCombo.getSelectionModel().clearSelection(); // Clears comboBox for new selection
                // Checks to see if the times are between Eastern Standard Time office hours
            } else if (DateTimeConversions.openHours(startDateTime) == false || // Checks startTime
                    DateTimeConversions.openHours(endDateTime) == false) { // Checks endTime
                // Alert and message
                eAlert.getAlert("This Appointment does not fall between the office hours of 8:00 a.m. to " +
                        "10:00 p.m. Eastern Standard Time. Please schedule another time for the meeting!");
                startTimeCombo.getSelectionModel().clearSelection(); // Clears selection
                endTimeCombo.getSelectionModel().clearSelection(); // Clears selection
                // Checks to make sure the customer doesn't have any overlapping Appointments
            } else if (Appointments.confirmStart(customerID, appointmentID, startDateTime) == false ||
                    Appointments.confirmEnd(customerID, appointmentID, endDateTime) == false) {
                // Alert and message
                eAlert.getAlert("There is an overlapping Appointment for this Customer. Please select another " +
                        "Time!");
                startDatePick.setValue(null); // Clears datePicker for new selection
                startTimeCombo.getSelectionModel().clearSelection(); // Clears comboBox for new selection
                endDatePick.setValue(null); // Clears datePicker for new selection
                endTimeCombo.getSelectionModel().clearSelection(); // Clears comboBox for new selection
                // Checks to see if the customer ID is valid
            } else if (Customers.isCustomerValid(customerID) == false) {
                eAlert.getAlert("Please enter a valid Customer ID!");// Alert and message
                customerCombo.getSelectionModel().clearSelection(); // Clears the textField
                // Checks to see if the user ID is valid
            } else if (Users.isUserValid(userID) == false) {
                eAlert.getAlert("Please enter a valid User ID!");// Alert and message
                userIdTxt.clear(); // Clears the textField
            } else {
                // If everything else is confirmed it creates a new Appointment
                Appointments.createAppointments(new Appointments(appointmentID, title, description, location, type,
                        startDateTime, endDateTime, createDate, createdBy, lastUpdate, lastUpdateBy, customerID, userID,
                        contactID, contact));
                // Informs the user that an Appointment has been created
                JOptionPane.showMessageDialog(null, "The Appointment has been created!");
                if (!(allTableView.getItems() == null))
                    allTableView.getItems().clear(); // Clears the tableView
                if (!(weeklyTableView.getItems() == null))
                    weeklyTableView.getItems().clear(); // Clears the tableView
                if (!(monthlyTableView.getItems() == null))
                    monthlyTableView.getItems().clear(); // Clears the tableView
                Appointments.clearAppointments();
                Appointments.getAllAppointments(); // Resets the tableViews with updated Appointments list
                if (weeklyTab.isSelected()) {
                    onActionEnterWeek(event); // Resets the table if visible
                } else if (monthlyTab.isSelected()) {
                    onActionEnterMonth(event); // Resets the table if visible
                }
                onActionClear(event);
            }
        } catch (Exception e) { // Used to catch any possible exceptions
            eAlert.getAlert("You must enter a valid value in each field to create an Appointment!");// Alert and message

        }

    }

    /**
     * This method is used to clear any selections or controls
     *
     * @param event is a button
     */
    @FXML
    void onActionClear(ActionEvent event) {

        appointIdTxt.clear(); // Clears the appointIdTxt
        titleTxt.clear(); // Clears the titleTxt
        descriptionTxt.clear(); // Clears the descriptionTxt
        locationTxt.clear(); // Clears the locationTxt
        contactCombo.getSelectionModel().clearSelection(); // Clears the contactCombo
        typeTxt.clear(); // Clears the typeTxt
        startDatePick.setValue(null); // Clears the startDatePick
        startTimeCombo.getSelectionModel().clearSelection(); // Clears the startTimeCombo
        endDatePick.setValue(null); // Clears the endDatePick
        endTimeCombo.getSelectionModel().clearSelection(); // Clears the endTimeCombo
        customerCombo.getSelectionModel().clearSelection(); // Clears the customerIdTxt
        userIdTxt.setText(String.valueOf(Users.fetchCurrentUserID())); // Resets the CurrentUserID
        allTableView.getSelectionModel().clearSelection(); // Clears allTableView selector
        weeklyTableView.getSelectionModel().clearSelection(); // Clears the weeklyTableView selector
        monthlyTableView.getSelectionModel().clearSelection(); // Clears the monthlyTableView selector

    }

    /**
     * This method is used to transfer and load the BuyersForm
     *
     * @param event is a button
     * @throws IOException possible exception thrown
     */
    @FXML
    void onActionBuyers(ActionEvent event) throws IOException {

        // Checks to see if their is appointment information to be saved before proceeding
        if (!(appointIdTxt.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // Error message if the text box is empty
            alert.setTitle("Save Record"); // Error title
            // Error message
            alert.setContentText("Would you like to save the previous record before proceeding?");
            // Option to proceed without saving or save the contents
            Optional<ButtonType> result = alert.showAndWait();
            // Saves the Appointment information
            if (result.isPresent() && result.get() == ButtonType.OK) {
                onActionUpdate(event); // Saves
            }
        }
        // Loads and opens the CustomerForm
        stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/BuyerForm.fxml"));
        stage.setScene(new Scene(scene)); // Sets the scene
        stage.show(); // Sets the stage

    }

    /**
     * This method is used to transfer and load the SellersForm
     *
     * @param event is a button
     * @throws IOException possible exception thrown
     */
    @FXML
    void onActionSellers(ActionEvent event) throws IOException {

        // Checks to see if their is appointment information to be saved before proceeding
        if (!(appointIdTxt.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // Error message if the text box is empty
            alert.setTitle("Save Record"); // Error title
            // Error message
            alert.setContentText("Would you like to save the previous record before proceeding?");
            // Option to proceed without saving or save the contents
            Optional<ButtonType> result = alert.showAndWait();
            // Saves the Appointment information
            if (result.isPresent() && result.get() == ButtonType.OK) {
                onActionUpdate(event); // Saves
            }
        }
        // Loads and opens the CustomerForm
        stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/SellerForm.fxml"));
        stage.setScene(new Scene(scene)); // Sets the scene
        stage.show(); // Sets the stage

    }

    /**
     * This method is used to delete a selected Appointment and provides confirmation before proceeding
     *
     * @param event is a button
     */
    @FXML
    void onActionDelete(ActionEvent event) {

        if (allTableView.getSelectionModel().isEmpty() && weeklyTableView.getSelectionModel().isEmpty()
                && monthlyTableView.getSelectionModel().isEmpty()) {
            // If the button is clicked with no selection made, an error message will occur
            eAlert.getAlert("Please select an Appointment from one of the tables to be deleted.");
        }
        // Determines which table the selection is being made from
        else if (allTab.isSelected() && !(allTableView.getSelectionModel().isEmpty())) {
            // Confirms the deletion before proceeding
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will permanently delete the Appointment, " +
                    "Do you want to continue?");
            Optional<ButtonType> result = alert.showAndWait(); // To continue with the deletion
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // If okay, deletes the selected appointment
                Appointments.deleteAppointments(allTableView.getSelectionModel().getSelectedItem());
                JOptionPane.showMessageDialog(null, "Appointment " +
                        allTableView.getSelectionModel().getSelectedItem().getAppointmentID() + " has been deleted!" +
                        " This appointment was a " + allTableView.getSelectionModel().getSelectedItem().getType()
                        + " Appointment.");

            }
        }
        // Determines which table the selection is being made from
        else if (weeklyTab.isSelected() && !(weeklyTableView.getSelectionModel().isEmpty())) {
            // Confirms the deletion before proceeding
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will permanently delete the Appointment, " +
                    "Do you want to continue?");
            Optional<ButtonType> result = alert.showAndWait(); // To continue with the deletion
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // If okay, deletes the selected appointment
                Appointments.deleteAppointments(weeklyTableView.getSelectionModel().getSelectedItem());
                JOptionPane.showMessageDialog(null, "Appointment " +
                        weeklyTableView.getSelectionModel().getSelectedItem().getAppointmentID() +
                        " has been deleted!" +
                        " This appointment was a " + weeklyTableView.getSelectionModel().getSelectedItem().getType()
                        + " Appointment.");
            }
        }
        // Determines which table the selection is being made from
        else if (monthlyTab.isSelected() && !(monthlyTableView.getSelectionModel().isEmpty())) {
            // Confirms the deletion before proceeding
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will permanently delete the Appointment, " +
                    "Do you want to continue?");
            Optional<ButtonType> result = alert.showAndWait(); // To continue with the deletion
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // If okay, deletes the selected appointment
                Appointments.deleteAppointments(monthlyTableView.getSelectionModel().getSelectedItem());
                JOptionPane.showMessageDialog(null, "Appointment " +
                        monthlyTableView.getSelectionModel().getSelectedItem().getAppointmentID() +
                        " has been deleted!" +
                        " This appointment was a " + monthlyTableView.getSelectionModel().getSelectedItem().getType()
                        + " Appointment.");
            }
        }
        if (!(allTableView.getItems() == null))
            allTableView.getItems().clear(); // Clears the tableView
        if (!(weeklyTableView.getItems() == null))
            weeklyTableView.getItems().clear(); // Clears the tableView
        if (!(monthlyTableView.getItems() == null))
            monthlyTableView.getItems().clear(); // Clears the tableView
        Appointments.clearAppointments();
        Appointments.getAllAppointments(); // Resets the tableViews with updated Appointments list
        if (weeklyTab.isSelected()) {
            onActionEnterWeek(event); // Resets the table if visible
        } else if (monthlyTab.isSelected()) {
            onActionEnterMonth(event); // Resets the table if visible
        }
        onActionClear(event);
    }

    /**
     * This method is used to select a month from the monthCombo box
     *
     * @param event is a button
     */
    @FXML
    void onActionEnterMonth(ActionEvent event) {

        // Error message if the button is clicked with no selection
        if (monthCombo.getValue() == null) {
            eAlert.getAlert("Please select a Month!");
        } else {
            // Creates a variable to hold the monthNumber of the selection
            int m = monthCombo.getSelectionModel().getSelectedItem().getMonthNumber();
            if (m == 0) { // Checks to see if the currentMonth is selection
                int cm = Appointments.getCurrentMonth(); // Creates a variable to hold the currentMonth
                if (Appointments.getMonthlyAppointments(cm) == null) {
                    JOptionPane.showMessageDialog(null, "There are no Appointments scheduled " +
                            "for this month!");
                } else
                    // Sets the tableView with the currentMonth appointments
                    monthlyTableView.setItems(Appointments.getMonthlyAppointments(cm));
                // Assigns the monthStartDateTimeCol with the startDateTime variable
                monthStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
                // Assigns the monthEndDateTimeCol with the endDateTime variable
                monthEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
                // Assigns the monthAppointIdCol with the appointmentIDe variable
                monthAppointIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
                // Assigns the monthTitleCol with the title variable
                monthTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
                // Assigns the monthDescriptionCol with the description variable
                monthDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
                // Assigns the monthLocationCol with the location variable
                monthLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
                // Assigns the monthContactCol with the contactName variable
                monthContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
                // Assigns the monthTypeCol with the type variable
                monthTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
                // Assigns the monthCustomerCol with the customerID variable
                monthCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            } else { // Sets the tableView with the monthNumber selected if it is not the CurrentMonth
                if (Appointments.getMonthlyAppointments(m) == null) {
                    JOptionPane.showMessageDialog(null, "There are no Appointments scheduled " +
                            "for " + monthCombo.getSelectionModel().getSelectedItem().getMonthName() + " !");
                } else
                    monthlyTableView.setItems(Appointments.getMonthlyAppointments(m));
                // Assigns the monthStartDateTimeCol with the startDateTime variable
                monthStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
                // Assigns the monthEndDateTimeCol with the endDateTime variable
                monthEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
                // Assigns the monthAppointIdCol with the appointmentIDe variable
                monthAppointIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
                // Assigns the monthTitleCol with the title variable
                monthTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
                // Assigns the monthDescriptionCol with the description variable
                monthDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
                // Assigns the monthLocationCol with the location variable
                monthLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
                // Assigns the monthContactCol with the contactName variable
                monthContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
                // Assigns the monthTypeCol with the type variable
                monthTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
                // Assigns the monthCustomerCol with the customerID variable
                monthCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            }
        }
    }

    /**
     * This method is used to select a week from the weekCombo Box
     *
     * @param event is a button
     */
    @FXML
    void onActionEnterWeek(ActionEvent event) {
        // Error message if not selection is made
        if (weekCombo.getValue() == null) {
            eAlert.getAlert("Please select a Week!");
        } else {
            // Creates a variable to hold the selected week number
            int w = weekCombo.getSelectionModel().getSelectedItem().getWeekNumber();
            int cw = Appointments.getCurrentWeek();
            if (w == cw) { // Checks to see if the current week is selected
                // Creates a variable to hold the current week

                // Sets the tableView with the current week appointments
                if (Appointments.getWeeklyAppointments(cw) == null) {
                    JOptionPane.showMessageDialog(null, "There are no Appointments scheduled " +
                            "for this week!");
                } else
                    weeklyTableView.setItems(Appointments.getWeeklyAppointments(cw));
                // Assigns weekStartDateTimeCol the  to the startDateTime variable
                weekStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
                // Assigns weekEndDateTimeCol the  to the endDateTime variable
                weekEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
                // Assigns weekAppointIdCol the  to the appointmentID variable
                weekAppointIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
                // Assigns weekTitleCol the  to the title variable
                weekTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
                // Assigns weekDescriptionCol the  to the description variable
                weekDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
                // Assigns weekLocationCol the  to the location variable
                weekLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
                // Assigns weekContactCol the  to the contactName variable
                weekContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
                // Assigns weekTypeCol the  to the type variable
                weekTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
                // Assigns weekCustomerIdCol the  to the customerID variable
                weekCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            } else {
                // Sets the tableView with the selected week
                if (Appointments.getWeeklyAppointments(w) == null) {
                    JOptionPane.showMessageDialog(null, "There are no Appointments scheduled " +
                            "for " + weekCombo.getSelectionModel().getSelectedItem() + "!");
                } else
                    weeklyTableView.setItems(Appointments.getWeeklyAppointments(w));
                // Assigns weekStartDateTimeCol the  to the startDateTime variable
                weekStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
                // Assigns weekEndDateTimeCol the  to the endDateTime variable
                weekEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
                // Assigns weekAppointIdCol the  to the appointmentID variable
                weekAppointIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
                // Assigns weekTitleCol the  to the title variable
                weekTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
                // Assigns weekDescriptionCol the  to the description variable
                weekDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
                // Assigns weekLocationCol the  to the location variable
                weekLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
                // Assigns weekContactCol the  to the contactName variable
                weekContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
                // Assigns weekTypeCol the  to the type variable
                weekTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
                // Assigns weekCustomerIdCol the  to the customerID variable
                weekCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            }
        }
    }

    /**
     * This method is used to exit the application
     *
     * @param event is a button
     */
    @FXML
    void onActionExit(ActionEvent event) {

        System.exit(0); // Exits the application
    }

    /**
     * This method is used to transfer and load the ReportForm
     *
     * @param event is a button
     * @throws IOException possible exception that may occur
     */
    @FXML
    void onActionReports(ActionEvent event) throws IOException {
        // Checks to see if any information needs to be saved before proceeding
        if (!(appointIdTxt.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Save Record"); // Confirmation title
            // Confirmation message
            alert.setContentText("Would you like to save the previous record before proceeding?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) { // Saves the previous record
                onActionUpdate(event);
            }
        }
        // Loads and sets the scene for the ReportForm
        stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportForm.fxml"));
        stage.setScene(new Scene(scene)); // Sets the scene
        stage.show(); // Sets the stage

    }


    /** This method is used to take a selected appointment from one of the tables and transfer the contents to the
     * controls so that the appointment can be updated.
     * @param event is a button
     */
    @FXML
    void onActionUpdate(ActionEvent event) {


        try { // Used in the event of a possible exception
            int appointmentID = Integer.parseInt(appointIdTxt.getText()); // Creates a variable to hold the appointID
            String title = titleTxt.getText(); // Creates a variable to hold the titleTxt
            String description = descriptionTxt.getText(); // Creates a variable to hold descriptionTxt
            String location = locationTxt.getText(); // Creates a variable to hold locationTxt
            String type = typeTxt.getText(); // Creates a variable to hold typeTxt
            LocalDate date = startDatePick.getValue(); // Creates a variable to hold startDatePick
            // Creates a variable to hold startTime
            String startTime = startTimeCombo.getSelectionModel().getSelectedItem().getTime();
            String startDateTime = date + " " + startTime; // Creates a variable to combine date and startTime
            LocalDate eDate = endDatePick.getValue(); // Creates a variable to hold eDate
            // Creates a variable to hold the endTime
            String endTime = endTimeCombo.getSelectionModel().getSelectedItem().getTime();
            String endDateTime = eDate + " " + endTime; // Creates a variable to combine eDate and endTime
            String createDate = ""; // Creates an empty String to pass through the constructor
            String createdBy = ""; // Creates an empty String to pass through the constructor
            String lastUpdate = ""; // Creates an empty String to pass through the constructor
            String lastUpdateBy = ""; // Creates an empty String to pass through the constructor
            // Creates a variable to hold the contactID
            int contactID = Contacts.nameToId(contactCombo.getSelectionModel().getSelectedItem().getContactName());
            // Creates a variable to hold the customerID
            int customerID = customerCombo.getSelectionModel().getSelectedItem().getCustomerID();
            int userID = Users.fetchCurrentUserID(); // Creates a variable to hold the userID
            // Creates a variable to hold the contact
            String contact = contactCombo.getSelectionModel().getSelectedItem().getContactName();


            // Checks to see if the textField is empty, if so provides an alert message
            if (titleTxt.getText().isEmpty()) {
                eAlert.getAlert("Please enter a Title!");// Alert and message

            } // Checks to see if the textField is empty, if so provides an alert message
            else if (descriptionTxt.getText().isEmpty()) {
                eAlert.getAlert("Please enter a Description!");// Alert and message

            } // Checks to see if the textField is empty, if so provides an alert message
            else if (locationTxt.getText().isEmpty()) {
                eAlert.getAlert("Please enter a Location!");// Alert and message

            } // Checks to see if a selection has not been made, if so provides an alert message
            else if (contactCombo.getValue() == null) {
                eAlert.getAlert("Please select a Contact!");// Alert and message

            } // Checks to see if the textField is empty, if so provides an alert message
            else if (typeTxt.getText().isEmpty()) {
                eAlert.getAlert("Please enter a Type!");// Alert and message

            } // Checks to see if a selection has not been made, if so provides an alert message
            else if (startDatePick.getValue() == null) {
                eAlert.getAlert("Please select a Start Date!");// Alert and message

            } // Checks to see if a selection has not been made, if so provides an alert message
            else if (startTimeCombo.getValue() == null) {
                eAlert.getAlert("Please select a Start Time!");// Alert and message

            } // Checks to see if a selection has not been made, if so provides an alert message
            else if (endDatePick.getValue() == null) {
                eAlert.getAlert("Please select an End Date!");// Alert and message

            } // Checks to see if a selection has not been made, if so provides an alert message
            else if (endTimeCombo.getValue() == null) {
                eAlert.getAlert("Please select an End Time!");// Alert and message

            } // Checks to see if the textField is empty, if so provides an alert message
            else if (customerCombo.getValue() == null) {
                eAlert.getAlert("Please enter a Customer ID!");// Alert and message

            }// Checks to see if the textField is empty, if so provides an alert message
            else if (userIdTxt.getText().isEmpty()) {
                eAlert.getAlert("Please enter a User ID!");// Alert and message

            }
            // Checks to see if the dates are current, if not gives an alert
            else if (DateTimeConversions.confirmDateTime(startDateTime, endDateTime) == false) {
                eAlert.getAlert("Please select current Dates and Times!");// Alert and message
                startDatePick.setValue(null); // Clears datePicker for new selection
                startTimeCombo.getSelectionModel().clearSelection(); // Clears comboBox for new selection
                endDatePick.setValue(null); // Clears datePicker for new selection
                endTimeCombo.getSelectionModel().clearSelection(); // Clears comboBox for new selection
                // Checks to see if the times are between Eastern Standard Time office hours
            } else if (DateTimeConversions.openHours(startDateTime) == false || // Checks startTime
                    DateTimeConversions.openHours(endDateTime) == false) { // Checks endTime
                // Alert and message
                eAlert.getAlert("This Appointment does not fall between the office hours of 8:00 a.m. to " +
                        "10:00 p.m. Eastern Standard Time. Please schedule another time for the meeting!");
                startTimeCombo.getSelectionModel().clearSelection(); // Clears selection
                endTimeCombo.getSelectionModel().clearSelection(); // Clears selection
                // Checks to make sure the customer doesn't have any overlapping Appointments
            } else if (Appointments.confirmStart(customerID, appointmentID, startDateTime) == false ||
                    Appointments.confirmEnd(customerID, appointmentID, endDateTime) == false) {
                // Alert and message
                eAlert.getAlert("There is an overlapping Appointment for this Customer. Please select another " +
                        "Time!");
                startDatePick.setValue(null); // Clears datePicker for new selection
                startTimeCombo.getSelectionModel().clearSelection(); // Clears comboBox for new selection
                endDatePick.setValue(null); // Clears datePicker for new selection
                endTimeCombo.getSelectionModel().clearSelection(); // Clears comboBox for new selection
                // Checks to see if the customer ID is valid
            } else if (Customers.isCustomerValid(customerID) == false) {
                eAlert.getAlert("Please enter a valid Customer ID!");// Alert and message
                customerCombo.getSelectionModel().clearSelection(); // Clears the textField
                // Checks to see if the user ID is valid
            } else if (Users.isUserValid(userID) == false) {
                eAlert.getAlert("Please enter a valid User ID!");// Alert and message
                userIdTxt.clear(); // Clears the textField
            }
            else {
                // If everything else is confirmed it updates the selected appointment
                Appointments.updateAppointments(new Appointments(appointmentID, title, description, location, type,
                        startDateTime, endDateTime, createDate, createdBy, lastUpdate, lastUpdateBy, customerID, userID,
                        contactID, contact));
                JOptionPane.showMessageDialog(null, "The updated Appointment has been saved!");
                if (!(allTableView.getItems() == null))
                    allTableView.getItems().clear(); // Clears the tableView
                if (!(weeklyTableView.getItems() == null))
                    weeklyTableView.getItems().clear(); // Clears the tableView
                if (!(monthlyTableView.getItems() == null))
                    monthlyTableView.getItems().clear(); // Clears the tableView
                Appointments.clearAppointments();
                Appointments.getAllAppointments(); // Resets the tableViews with updated Appointments list
                if(weeklyTab.isSelected()){
                    onActionEnterWeek(event); // Resets the table if visible
                }
                else if (monthlyTab.isSelected())   {
                    onActionEnterMonth(event); // Resets the table if visible
                }
                onActionClear(event); // Clears all selections, TextFields,  and tableViews
            }
        }

        catch (Exception e){ // Catches any possible exceptions
            // Alert and message
            eAlert.getAlert("To update, first select an appointment from one of the tables by double clicking!");

        }
    }

    /**
     * This method is used to get a selected appointment from the tableview when a selection is double clicked. It then
     * passes the selected appointment to the textViews to be edited and updated
     */
    public void getAllAppointment(){
        allTableView.setRowFactory(tv -> {
            TableRow<Appointments> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())) {
                    Appointments appointments = row.getItem();
                        // Creates an Appointments object to hold the selected appointment
                        appointIdTxt.setText(String.valueOf(appointments.getAppointmentID()));
                        // Sets the titleTxt from the object
                        titleTxt.setText(appointments.getTitle());
                        // Sets the descriptionTxt from the object
                        descriptionTxt.setText(appointments.getDescription());
                        // Sets the locationTxt from the object
                        locationTxt.setText(appointments.getLocation());
                        // Sets the contactCombo from the object
                        contactCombo.setValue(Contacts.locateContact(appointments.getContactID()));
                        // Sets the typeTxt from the object
                        typeTxt.setText(appointments.getType());
                        // Sets the startDatePick from the object
                        startDatePick.setValue(DateTimeConversions.extractDate(appointments.getStartDateTime()));
                        // Sets the startTimeCombo from the object
                        startTimeCombo.setValue(Times.extractTime(appointments.getStartDateTime()));
                        // Sets the endDatePick from the object
                        endDatePick.setValue(DateTimeConversions.extractDate(appointments.getEndDateTime()));
                        // Sets the endTimeCombo from the object
                        endTimeCombo.setValue(Times.extractTime(appointments.getEndDateTime()));
                        // Sets the customerCombo from the object
                        customerCombo.setValue(Customers.getCustomerObject(appointments.getCustomerID()));
                        // Sets the userIdTxt from the object
                        userIdTxt.setText(String.valueOf(appointments.getUserID()));
                        // Sets the weeklyTableView from the object
                        weeklyTableView.getSelectionModel().clearSelection();
                        // Sets the monthlyTableView from the object
                        monthlyTableView.getSelectionModel().clearSelection();
                }

            });
            return row;
        });

    }
    /**
     * This method is used to get a selected appointment from the tableview when a selection is double clicked. It then
     * passes the selected appointment to the textViews to be edited and updated
     */
    public void getWeeklyAppointment(){
        weeklyTableView.setRowFactory(tv -> {
            TableRow<Appointments> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())) {
                    Appointments appointments = row.getItem();
                        appointIdTxt.setText(String.valueOf(appointments.getAppointmentID()));
                        // Sets the titleTxt from the object
                        titleTxt.setText(appointments.getTitle());
                        // Sets the descriptionTxt from the object
                        descriptionTxt.setText(appointments.getDescription());
                        // Sets the locationTxt from the object
                        locationTxt.setText(appointments.getLocation());
                        // Sets the contactCombo from the object
                        contactCombo.setValue(Contacts.locateContact(appointments.getContactID()));
                        // Sets the typeTxt from the object
                        typeTxt.setText(appointments.getType());
                        // Sets the startDatePick from the object
                        startDatePick.setValue(DateTimeConversions.extractDate(appointments.getStartDateTime()));
                        // Sets the startTimeCombo from the object
                        startTimeCombo.setValue(Times.extractTime(appointments.getStartDateTime()));
                        // Sets the endDatePick from the object
                        endDatePick.setValue(DateTimeConversions.extractDate(appointments.getEndDateTime()));
                        // Sets the endTimeCombo from the object
                        endTimeCombo.setValue(Times.extractTime(appointments.getEndDateTime()));
                        // Sets the customerCombo from the object
                        customerCombo.setValue(Customers.getCustomerObject(appointments.getCustomerID()));
                        // Sets the userIdTxt from the object
                        userIdTxt.setText(String.valueOf(appointments.getUserID()));
                        // Sets the weeklyTableView from the object
                        allTableView.getSelectionModel().clearSelection();
                        // Sets the monthlyTableView from the object
                        monthlyTableView.getSelectionModel().clearSelection();
                        // Checks to see which table the selection is made from
                }

            });
            return row;
        });

    }
    /**
     * This method is used to get a selected appointment from the tableview when a selection is double clicked. It then
     * passes the selected appointment to the textViews to be edited and updated
     */
    public void getMonthlyAppointment() {
        monthlyTableView.setRowFactory(tv -> {
            TableRow<Appointments> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Appointments appointments = row.getItem();
                        // Sets the appointmentIdTxt from the object
                        appointIdTxt.setText(String.valueOf(appointments.getAppointmentID()));
                        // Sets the titleTxt from the object
                        titleTxt.setText(appointments.getTitle());
                        // Sets the descriptionTxt from the object
                        descriptionTxt.setText(appointments.getDescription());
                        // Sets the locationTxt from the object
                        locationTxt.setText(appointments.getLocation());
                        // Sets the contactCombo from the object
                        contactCombo.setValue(Contacts.locateContact(appointments.getContactID()));
                        // Sets the typeTxt from the object
                        typeTxt.setText(appointments.getType());
                        // Sets the startDatePick from the object
                        startDatePick.setValue(DateTimeConversions.extractDate(appointments.getStartDateTime()));
                        // Sets the startTimeCombo from the object
                        startTimeCombo.setValue(Times.extractTime(appointments.getStartDateTime()));
                        // Sets the endDatePick from the object
                        endDatePick.setValue(DateTimeConversions.extractDate(appointments.getEndDateTime()));
                        // Sets the endTimeCombo from the object
                        endTimeCombo.setValue(Times.extractTime(appointments.getEndDateTime()));
                        // Sets the customerCombo from the object
                        customerCombo.setValue(Customers.getCustomerObject(appointments.getCustomerID()));
                        // Sets the userIdTxt from the object
                        userIdTxt.setText(String.valueOf(appointments.getUserID()));
                        // Sets the weeklyTableView from the object
                        weeklyTableView.getSelectionModel().clearSelection();
                        // Sets the monthlyTableView from the object
                        allTableView.getSelectionModel().clearSelection();
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

        // Sets the allTableView with Appointment objects
        allTableView.setItems(Appointments.getAllAppointments());
        // Assigns the appointIdCol to the appointmentID variable
        appointIdCol.setCellValueFactory((new PropertyValueFactory<>("appointmentID")));
        // Assigns titleCol the to the title variable
        titleCol.setCellValueFactory((new PropertyValueFactory<>("title")));
        // Assigns descriptionCol the to the description variable
        descriptionCol.setCellValueFactory((new PropertyValueFactory<>("description")));
        // Assigns the locationCol to the location variable
        locationCol.setCellValueFactory((new PropertyValueFactory<>("location")));
        // Assigns contactCol the to the contactName variable
        contactCol.setCellValueFactory((new PropertyValueFactory<>("contactName")));
        // Assigns typeCol the to the type variable
        typeCol.setCellValueFactory((new PropertyValueFactory<>("type")));
        // Assigns the startDateTimeCol to the startDateTime variable
        startDateTimeCol.setCellValueFactory((new PropertyValueFactory<>("startDateTime")));
        // Assigns the endDateTimeCol to the endDateTime variable
        endDateTimeCol.setCellValueFactory((new PropertyValueFactory<>("endDateTime")));
        // Assigns the customerIdCol to the customerID variable
        customerIdCol.setCellValueFactory((new PropertyValueFactory<>("customerID")));
        contactCombo.setItems(Contacts.getAllContacts()); // Sets the contact Combo box with Contact objects
        startTimeCombo.setItems(Times.getTimes()); // Sets the startTimeCombo box with Times objects
        endTimeCombo.setItems(Times.getTimes()); // Sets the endTimeCombo box with Times objects
        monthCombo.setItems(CalendarMonths.getAllCalendarMonths()); // Sets the monthCombo with CalendarMonths objects
        weekCombo.setItems(CalendarWeeks.getAllCalendarWeeks()); // Sets the weekCombo box with CalendarWeeks objects
        customerCombo.setItems(Customers.getCustomers());
        userIdTxt.setText(String.valueOf(Users.fetchCurrentUserID())); // Sets the userIdTxt with the currentUser
        getAllAppointment(); // Passes an appointment from the allTableView to textViews to edit and update
        getWeeklyAppointment(); // Passes an appointment from the weeklyTableView to textViews to edit and update
        getMonthlyAppointment(); // Passes an appointment from the monthlyTableView to textViews to edit and update
    }
}
