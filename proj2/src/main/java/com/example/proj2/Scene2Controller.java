package com.example.proj2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scene2Controller {
    @FXML
    private TextField textField;
    @FXML
    private TextField textField3;
    @FXML
    private TextField textField4;
    @FXML
    private PasswordField passwordField;
    private List<User> users;

    public Scene2Controller() {
        users = new ArrayList<>();
    }

        public void createAccount(ActionEvent event) {
        String username = textField4.getText();
        String password = passwordField.getText();

        // Create a new User object
        User newUser = new User(username, password);

        // Add the new user to the users list
        users.add(newUser);

        // Clear the input fields
        textField4.clear();
        passwordField.clear();

            try {
                switchToScene4(event); // Switch to Scene 4 after creating a new account
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    @FXML
    public void switchToScene4(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene4.fxml"));
        Parent scene2Root = fxmlLoader.load();
        Scene4Controller scene4Controller = fxmlLoader.getController(); // Get the controller of the scene2.fxml
        scene4Controller.setUsers(users); // Pass the users ArrayList to the Scene2Controller
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(scene2Root, 800, 600));
        stage.setTitle("AIsistify");
        stage.show();


    }
    @FXML
    public void handleRegistration(ActionEvent event) {
        String username = textField4.getText();
        String password = passwordField.getText();

        if (validateRegistration(username, password)) {
            // Create a new User object and add it to the users list
            User newUser = new User(username, password);
            users.add(newUser);

            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("Registration completed successfully!");
            alert.showAndWait();

            // Clear the input fields after successful registration
            textField4.clear();
            passwordField.clear();

            try {
                switchToLoginPage(event); // Switch back to the login page after successful registration
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Display an error message for invalid registration
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password");
            alert.showAndWait();
        }
    }

    @FXML
    public void switchToLoginPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent homepageRoot = fxmlLoader.load();
        HelloController helloController = fxmlLoader.getController();
        helloController.setUsers(users);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(homepageRoot, 800, 600));
        stage.setTitle("AIsistify");
        stage.show();
    }
    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene2.fxml"));
        Parent scene2Root = fxmlLoader.load();
        Scene2Controller scene2Controller = fxmlLoader.getController();
        scene2Controller.setUsers(users);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(scene2Root, 800, 600));
        stage.setTitle("AIsistify");
        stage.show();
    }
    // Method to validate registration credentials
    private boolean validateRegistration(String username, String password) {
        // Perform any validation checks as needed
        // For example, check if the username is already taken
        // You can customize this logic based on your requirements

        // Here, we simply check if the username is not empty and the password is at least 4 characters long
        return !username.isEmpty() && password.length() >= 4;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
