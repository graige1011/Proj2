package com.example.proj2.GuiManager;

import com.example.proj2.login.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class NewAccountPageStrategy implements SceneCreationStrategy {
    private ArrayList<User> userList;
    private SceneSwitcher sceneSwitcher;

    public NewAccountPageStrategy(SceneSwitcher sceneSwitcher, ArrayList<User> userList) {
        this.sceneSwitcher = sceneSwitcher;
        this.userList = userList;
    }

    @Override
    public Scene createScene() {
        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white;");

        Label welcomeText = new Label("Welkom bij AIsistify");
        welcomeText.setStyle("-fx-font-size: 24px; -fx-text-fill: darkblue;");

        TextField textField = new TextField();
        textField.setPromptText("Voer je naam in");
        textField.setStyle("-fx-background-color: white;");

        TextField textField3 = new TextField();
        textField3.setPromptText("Voer je email in");
        textField3.setStyle("-fx-background-color: white;");

        TextField textField4 = new TextField();
        textField4.setPromptText("Voer je gebruikersnaam in");
        textField4.setStyle("-fx-background-color: white;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Voer wachtwoord in");
        passwordField.setStyle("-fx-background-color: white;");

        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> {
            // sceneFunctions.handleRegistration(textField.getText(), textField3.getText(), textField4.getText(), passwordField.getText());


            String name = textField4.getText();
            String password = passwordField.getText();
            String email = textField3.getText();

            // Validate registration
            boolean registrationValid = User.validateRegistration(name, password);
            if (!registrationValid) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Registration Failed");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Registration failed! Please try again.");
                errorAlert.showAndWait();
                return; // Stop further execution
            }

            User newUser = new User(name, password, email);
            userList.add(newUser);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("Registration completed successfully!");
            alert.showAndWait();

            // Print current user list in the terminal
            System.out.println("Current User List:");
            for (User user : userList) {
                System.out.println("Username: " + user.getUsername() + ", Password: " + user.getPassword() + ", Email: " + user.getEmail());
            }


            // Registration successful
            System.out.println("Registration successful!");
            // Perform actions after successful registration

            // Reset the registration form
            textField.clear();
            textField3.clear();
            textField4.clear();
            passwordField.clear();
            sceneSwitcher.switchToLoginPage(event);

            // dit moet iets anders zijn
        });
        registerButton.getStyleClass().add("hover-button");

        root.getChildren().addAll(welcomeText, textField, textField3, textField4, passwordField, registerButton);

        return new Scene(root, 600, 800); // Set the desired size of the scene
    }
}
