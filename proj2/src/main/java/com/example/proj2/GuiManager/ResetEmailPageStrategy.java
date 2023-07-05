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

public class ResetEmailPageStrategy implements SceneCreationStrategy{

    private ArrayList<User> userList;
    private SceneSwitcher sceneSwitcher;
    public ResetEmailPageStrategy(SceneSwitcher sceneSwitcher, ArrayList<User> userList) {
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

        Label welcomeText = new Label("Reset Your Email");
        welcomeText.setStyle("-fx-font-size: 24px; -fx-text-fill: darkblue;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setStyle("-fx-background-color: white;");

        TextField newEmailField = new TextField();
        newEmailField.setPromptText("Enter your new email");
        newEmailField.setStyle("-fx-background-color: white;");

        Button resetEmailButton = new Button("Reset Email");
        resetEmailButton.setOnAction(event -> {
            String username = usernameField.getText();
            String newEmail = newEmailField.getText();

            // Perform the email reset logic here
            boolean isEmailResetSuccessful = resetEmail(username, newEmail);

            if (isEmailResetSuccessful) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Email Reset Successful");
                alert.setHeaderText(null);
                alert.setContentText("Your email has been successfully reset!");
                alert.showAndWait();

                // Switch back to the login page
                sceneSwitcher.switchToLoginPage(event);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Email Reset Failed");
                alert.setHeaderText(null);
                alert.setContentText("Failed to reset your email. Please try again.");
                alert.showAndWait();
            }
        });
        resetEmailButton.getStyleClass().add("hover-button");

        Button backButton = new Button("Go Back");
        backButton.setOnAction(event -> sceneSwitcher.switchToLoginPage(event));
        backButton.getStyleClass().add("hover-button");

        root.getChildren().addAll(welcomeText, usernameField, newEmailField, backButton, resetEmailButton);

        return new Scene(root, 600, 800); // Set the desired size of the scene
    }

    private boolean resetEmail(String username, String newEmail) {
        // Implement your email reset logic here
        // This is just a placeholder method
        // Replace it with the actual logic to reset the email for the given username
        // Return true if the email reset is successful, false otherwise
        // You can store the username-email mapping in the userList or a separate database

        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                user.setEmail(newEmail);
                return true;
            }
        }

        return false; // Email reset failed
    }
}
