package com.example.proj2.GuiManager;

import com.example.proj2.login.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ResetUsernamePageStrategy implements SceneCreationStrategy {

    private ArrayList<User> userList;
    private SceneSwitcher sceneSwitcher;

    public ResetUsernamePageStrategy(SceneSwitcher sceneSwitcher,ArrayList<User> userList) {
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

        Label welcomeText = new Label("Reset je gebruikersnaam");
        welcomeText.setStyle("-fx-font-size: 24px; -fx-text-fill: darkblue;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Voer je huidige gebruikersnaam in");
        usernameField.setStyle("-fx-background-color: white;");

        TextField newUsernameField = new TextField();
        newUsernameField.setPromptText("Voer je nieuwe gebruikersnaam in");
        newUsernameField.setStyle("-fx-background-color: white;");

        Button resetUsernameButton = new Button("Gebruikersnaam herstellen");
        resetUsernameButton.setOnAction(event -> {
            String username = usernameField.getText();
            String newUsername = newUsernameField.getText();

            boolean isUsernameResetSuccessful = resetUsername(username, newUsername);

            if (isUsernameResetSuccessful) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Username Reset Successful");
                alert.setHeaderText(null);
                alert.setContentText("Your username has been successfully reset!");
                alert.showAndWait();

                // Switch back to the login page
                sceneSwitcher.switchToLoginPage(event);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Username Reset Failed");
                alert.setHeaderText(null);
                alert.setContentText("Failed to reset your username. Please try again.");
                alert.showAndWait();
            }
        });
        resetUsernameButton.getStyleClass().add("hover-button");

        Button backButton = new Button("Terug gaan");
        backButton.setOnAction(event -> sceneSwitcher.switchToLoginPage(event));
        backButton.getStyleClass().add("hover-button");

        root.getChildren().addAll(welcomeText, usernameField, newUsernameField, resetUsernameButton, backButton);

        return new Scene(root, 600, 800); // Set the desired size of the scene

    }

    private boolean resetUsername(String oldUsername, String newUsername) {
        // Implement your username reset logic here
        // This is just a placeholder method
        // Replace it with the actual logic to reset the username for the given oldUsername
        // Return true if the username reset is successful, false otherwise
        // You can store the username-password mapping in the userList or a separate database

        for (User user : userList) {
            if (user.getUsername().equals(oldUsername)) {
                user.setUsername(newUsername);
                return true;
            }
        }

        return false; // Username reset failed
    }
}
