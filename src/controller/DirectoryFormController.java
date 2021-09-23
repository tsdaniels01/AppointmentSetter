package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/** This class acts as a controller for the DirectoryForm.fxml document and controls all functions. Its purpose is
 * to direct the user to one of the three forms or allows them to exit the program.
 *
 */
public class DirectoryFormController {

    Stage stage; // Variable to set the stage
    Parent scene; // Variable to set the scene

    /** This method is used to load the Appointment form is the button is clicked.
     * @param event is a button
     * @throws IOException possible exception that may occur.
     */
    @FXML
    void onActionAppointments(ActionEvent event) throws IOException {
        // Loads and sets the scene for the Appointments form
        stage = (Stage) ((javafx.scene.control.Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    /** This method is used to load the Customers form is the button is clicked.
     * @param event is a button
     * @throws IOException possible exception that may occur.
     */
    @FXML
    void onActionSellers(ActionEvent event) throws IOException {

            stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/SellerForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    /** This method is used to load the Customers form is the button is clicked.
     * @param event is a button
     * @throws IOException possible exception that may occur.
     */
    @FXML
    void onActionBuyers(ActionEvent event) throws IOException {

        stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/BuyerForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /** This method is used to exit the program if the button is clicked.
     * @param event is a button
     */
    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0); // Exits the program

    }
    /** This method is used to load the Report form is the button is clicked.
     * @param event is a button
     * @throws IOException possible exception that may occur.
     */
    @FXML
    void onActionReports(ActionEvent event) throws IOException {
        // Loads and sets the scene for the Report form
        stage = (Stage) ((javafx.scene.control.Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
