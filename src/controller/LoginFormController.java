package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;
import model.Users;
import utilities.DateTimeConversions;
import utilities.Translation;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/** This class acts as a controller for the LoginForm. It controls all parts of the LoginForm.fxml document. The purpose
 * of this form is to allow the user to input their credentials and verifies it. It also alerts the user of any
 * upcoming appointments within 15 minutes of logging in.
 *
 */
public class LoginFormController implements Initializable {


    Stage stage;// Creates a variable to hold the stage
    Parent scene; // Creates a variable to hold the scene

    @FXML
    private Label userLoginLbl; // Label control variable needed for french translation

    @FXML
    private Label usernameLbl; // Label control variable needed for french translation

    @FXML
    private Label passwordLbl; // Label control variable needed for french translation

    @FXML
    private Label userLocationLbl; // Label control variable needed for french translation

    @FXML
    private Label zoneIdLbl; // Label control variable needed for french translation

    @FXML
    private Button loginBtn; // Button control label needed for french translation

    @FXML
    private Button cancelBtn; // Button control label needed for french translation

    @FXML
    private TextField userNameTxt; // Text field variable for username input


    @FXML
    private PasswordField passwordFld; // Password field variable for disguising user password input

    /** This method is an action event that verifies user credentials and if accepted loads the directory form.
     * If it is not accepted than a error message will occur.
     * @param event is a button
     */
    @FXML
    void onActionLogin(ActionEvent event) {

        try { // Used in the event of a possible exception
        String user = userNameTxt.getText(); // Creates a variable to store the username
        String password = passwordFld.getText(); // Creates a variable to store the password
        Users.userTracker(user, password);
        // Determines if the username text box is empty, if so provides an error message.
        if (userNameTxt.getText().isEmpty()) {
            // If the default language is french, it translates the message
            if (Locale.getDefault().getLanguage().equals("fr")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Boîte de dialogue d'erreur"); // Error Title
                alert.setContentText("Merci d'entrer un nom d'utilisateur."); // Error message
                alert.showAndWait();
                //if not, english version
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog"); // Error Title
                alert.setContentText("Please enter a Username"); // Error message
                alert.showAndWait();
            }

        }
        // Determines if the password text box is empty, if so provides an error message
        else if (passwordFld.getText().isEmpty()) {
            // If the default language is french, it translates the message
            if (Locale.getDefault().getLanguage().equals("fr")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Boîte de dialogue d'erreur"); // Error Title
                alert.setContentText("Veuillez entrer un mot de passe."); // Error message
                alert.showAndWait();
            }else {//if not, english version
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog"); // Error Title
                alert.setContentText("Please enter a Password"); // Error message
                alert.showAndWait();
            }
        }
        // Validates the username and password that is entered
        else if (Users.confirmUser(user, password) == false) {
            // If the default language is french, it translates the message
            if (Locale.getDefault().getLanguage().equals("fr")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Boîte de dialogue d'erreur"); // Error Title
                // Error message
                alert.setContentText("Le nom d'utilisateur ou le mot de passe que vous avez entré n'est pas valide. " +
                        "Veuillez réessayer!");
                alert.showAndWait();
            }else {//if not, english version
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog"); // Error Title
                // Error message
                alert.setContentText("The Username or Password that you entered is invalid. Please try again!");
                alert.showAndWait();
            }
            userNameTxt.clear(); // Clears the username text box if it is invalid
            passwordFld.clear(); // Clears the password text field if it is invalid

        } else if(Users.confirmUser(user, password)){
            // If the user is verified it adds the user to the currentUser observable list to be called on later
            Users.addCurrentUser(new Users(user));
            int id = Users.fetchCurrentUserID();
            Appointments a = Appointments.checkUserAppointments(id);
            if (a == null) {
                // If the default language is french, it translates the message
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alerte de rendez-vous"); // Error Title
                    alert.setContentText("Vous n'avez pas de rendez-vous à venir!"); // Error message
                    alert.showAndWait();
                }else {//if not, english version
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Appointment Alert"); // Error Title
                    alert.setContentText("You have no upcoming Appointments!"); // Error message
                    alert.showAndWait();
                }

            } else {
                LocalDateTime ldt = DateTimeConversions.toLDT(a.getStartDateTime());
                // If the default language is french, it translates the message
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alerte de rendez-vous"); // Error Title
                    // Error message
                    alert.setContentText("Vous avez un rendez-vous à venir! Rendez-vous " + a.getAppointmentID() +
                            " à" + ldt.toLocalTime() + " au " + ldt.toLocalDate() + " , démarre en moins de 15 " +
                            "minutes.");
                    alert.showAndWait();
                }else {//if not, english version
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Appointment Alert"); // Error Title
                    // Error message
                    alert.setContentText("You have an upcoming Appointment! Appointment " + a.getAppointmentID() +
                            " at " + ldt.toLocalTime() + " on " + ldt.toLocalDate() + " , starts in less than 15 " +
                            "minutes.");
                    alert.showAndWait();
                }
            }
            // Sets the stage and scene to load the DirectoryForm
            stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/DirectoryForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }   // Catches the exception and provides an error alert
        catch (Exception e){
            // If the default language is french, it translates the message
            if (Locale.getDefault().getLanguage().equals("fr")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Boîte de dialogue d'erreur"); // Error Title
                // Error message
                alert.setContentText("Les informations d'identification doivent être vérifiées! Veuillez saisir " +
                        "un nom d'utilisateur et un mot de passe valides.");
                alert.showAndWait();
            }else {//if not, english version
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog"); // Error Title
                // Error message
                alert.setContentText("Credentials must be verified! Please enter a valid Username and Password.");
                alert.showAndWait();
            }
        }
    }
    /** This method is used to exit the program if cancelled.
     *
     * @param event is a button
     */
    @FXML
    void onActionCancel(ActionEvent event) {

        System.exit(0); // Exits the system

    }

    /** This method is used to initialize any data when the form is loading and utilizes the @Override command.
     * @param  url to provide any pointer to the WWW if needed.
     * @param rb in the event of a local specific resource need
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){

        // Assigns the users ZoneId to the locationTxt field
        zoneIdLbl.setText (" " + String.valueOf(ZoneId.systemDefault()));

        // Checks to see if the user's language is set to french. If so, the labels and buttons are translated to french
        if (Locale.getDefault().getLanguage().equals("fr")) {
            // Resource bundle created for translation
            ResourceBundle bundle = ResourceBundle.getBundle("utilities/Nat", Locale.getDefault());
            // Translates label to french
            userLoginLbl.setText("Utilisateur en ligne");
            // Translates label to french
            usernameLbl.setText(Translation.translation(String.valueOf(usernameLbl.getText())));
            // Translates label to french
            passwordLbl.setText(Translation.translation(String.valueOf(passwordLbl.getText())));
            // Translates label to french
            userLocationLbl.setText(Translation.translation(String.valueOf(userLocationLbl.getText())));
            // Translates button label to french
            loginBtn.setText(Translation.translation(String.valueOf(loginBtn.getText())));
            // Translates button label to french
            cancelBtn.setText(Translation.translation(String.valueOf(cancelBtn.getText())));
        }
    }
}
