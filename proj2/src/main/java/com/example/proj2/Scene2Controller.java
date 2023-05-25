package com.example.proj2;

import com.example.proj2.HelloController;
import com.example.proj2.User;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scene2Controller {
    private TextField textField;
    private TextField textField3;
    private TextField textField4;
    private PasswordField passwordField;
    private List<User> users;

    public Scene2Controller() {
        users = new ArrayList<>();
    }

    public Parent createScene2UI() {
        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));

        Label welcomeText = new Label("Welkom bij AIsistify");
        welcomeText.setStyle("-fx-font-size: 24px; -fx-text-fill: darkblue;");

        textField = new TextField();
        textField.setPromptText("Voer je naam in");
        textField.setStyle("-fx-background-color: white;");

        textField3 = new TextField();
        textField3.setPromptText("Voer je email in");
        textField3.setStyle("-fx-background-color: white;");

        textField4 = new TextField();
        textField4.setPromptText("Voer je gebruikersnaam in");
        textField4.setStyle("-fx-background-color: white;");

        passwordField = new PasswordField();
        passwordField.setPromptText("Voer wachtwoord in");
        passwordField.setStyle("-fx-background-color: white;");

        Button registerButton = new Button("Register");
        registerButton.setOnAction(this::handleRegistration);
        registerButton.getStyleClass().add("hover-button");
        registerButton.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        root.getChildren().addAll(welcomeText, textField, textField3, textField4, passwordField, registerButton);

        return root;
    }

    public void handleRegistration(ActionEvent event) {
        String username = textField4.getText();
        String password = passwordField.getText();

        if (validateRegistration(username, password)) {
            // Create a new User object and add it to the users list
            User newUser = new User(username, password);
            users.add(newUser);

            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful!");
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

    public void switchToLoginPage(ActionEvent event) throws IOException {
         HelloController helloController1 = new HelloController();
         helloController1.switchToHelloScene(event);
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
