package com.example.proj2.GuiManager;

import com.example.proj2.login.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ResetPasswordPageStrategy implements SceneCreationStrategy {
    private SceneSwitcher sceneSwitcher;
    private ArrayList<User> userList;


    public ResetPasswordPageStrategy(SceneSwitcher sceneSwitcher,ArrayList<User> userList) {
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

        Label welcomeText = new Label("Reset je wachtwoord");
        welcomeText.setStyle("-fx-font-size: 24px; -fx-text-fill: darkblue;");

        TextField textField = new TextField();
        textField.setPromptText("Voer je gebruikersnaam in");
        textField.setStyle("-fx-background-color: white;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Voer je nieuwe wachtwoord in");
        passwordField.setStyle("-fx-background-color: white;");

        PasswordField passwordField2 = new PasswordField();
        passwordField2.setPromptText("Bevestig je nieuwe wachtwoord");
        passwordField2.setStyle("-fx-background-color: white;");

        Button resetPasswordButton = new Button("Wachtwoord herstellen");
        resetPasswordButton.setOnAction(event -> {
            // sceneFunctions.resetPassword(textField.getText(), passwordField.getText(), passwordField2.getText());
            String username = textField.getText();
            String newPassword = passwordField.getText();
            String confirmPassword = passwordField2.getText();

            if (newPassword.equals(confirmPassword)) {
                // Perform the password reset logic here
                boolean isPasswordResetSuccessful = resetPassword(username, newPassword);

                if (isPasswordResetSuccessful) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Password Reset Successful");
                    alert.setHeaderText(null);
                    alert.setContentText("Your password has been successfully reset!");
                    alert.showAndWait();

                    // Switch back to the login page
                    sceneSwitcher.switchToLoginPage(event);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Password Reset Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to reset your password. Please try again.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Reset Failed");
                alert.setHeaderText(null);
                alert.setContentText("The passwords entered do not match. Please make sure to enter the same password in both fields.");
                alert.showAndWait();
            }
        });
        resetPasswordButton.getStyleClass().add("hover-button");


        Button backButton = new Button("Terug gaan");
        backButton.setOnAction(event -> sceneSwitcher.switchToLoginPage(event));
        backButton.getStyleClass().add("hover-button");

        root.getChildren().addAll(welcomeText, textField, passwordField, passwordField2, backButton, resetPasswordButton);

        return new Scene(root, 600, 800); // Set the desired size of the scene
    }

    private boolean resetPassword(String username, String newPassword) {
        // Implement your password reset logic here
        // This is just a placeholder method
        // Replace it with the actual logic to reset the password for the given username
        // Return true if the password reset is successful, false otherwise
        // You can store the username-password mapping in the userList or a separate database

        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword);
                return true;
            }
        }

        return false; // Password reset failed
    }
}