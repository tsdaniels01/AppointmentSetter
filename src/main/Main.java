package main;


import dbCalls.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import utilities.*;

import java.util.Locale;

/**
 * @Author: Timothy Daniels
 * @StudentID: 001164972
 * @Class: C195
 * @Description: This application was created to manage customers and schedule appointments utilizing a MySQL database
 * @Javadoc: Software II/Javadoc
 */


/** This is the main class. It initiates the start command for Javafx and loads the LoginForm as well as establishes
 *  the initial connection to the database. Any methods that need to be initialized from the start of the
 *  application will be called here.
 * */
public class Main extends Application {

    @Override // Overrides the start command
    public void start(Stage primaryStage) throws Exception {
        // Creates a variable to hold and load the LoginForm
        Parent root = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        // Determines if the default user preferred language is french, if so it translates the title
        if (Locale.getDefault().getLanguage().equals("fr"))
            primaryStage.setTitle("Agents Immobiliers Nationaux Inc.");
        else // If not french, loads the english title
            primaryStage.setTitle("National Realtors Inc.");
            primaryStage.setScene(new Scene(root, 400, 300));
            primaryStage.show();
    }

    // Main method used to establish database connection and execute initial methods
    public static void main(String[] args){

        DBConnection.getConnection(); // Establishes the database command
        // Executes the call to the database to retrieve the appointments table
        DBAppointments.getAllAppointments();
        // Executes the call to the database to retrieve the contacts table
        DBContacts.getAllContacts();
        // Executes the call to the database to retrieve the countries table
        DBCountries.getAllCountries();
        // Executes the call to the database to retrieve the customers table
        DBCustomers.getDBCustomers();
        // Executes the call to the database to retrieve customerAppointments
        DBCustomerAppointments.getAllCustomerAppointments();
        // Executes the call to the database to retrieve the first_Level_Divisions table
        DBFirstLevelDivisions.getAllFirstLevelDivisions();
        // Executes the call to the database to retrieve the users table
        DBUsers.getUsers();
        CalendarMonths.entry(); // Executes the calendarMonths entry
        CalendarWeeks.entry(); // Executes the calendarWeeks entry
        Times.entry(); // Executes the times entry
        PropertyTypes.entry(); // Executes the property type entry
        launch(args);
        DBConnection.closeConnection(); // Closes the database connection
        }

    }



