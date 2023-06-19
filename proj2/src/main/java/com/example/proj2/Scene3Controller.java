package com.example.proj2;

import com.example.proj2.HelloController;
import com.example.proj2.login.User;
import com.example.proj2.login.UserManager;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Scene3Controller {
    private UserManager userManager;

    private TextField textField;
    private PasswordField passwordField;
    private PasswordField passwordField2;
    private HelloController helloController;

    public Scene3Controller() {
        userManager = new UserManager();
    }


        public VBox createScene3UI() {
            VBox root = new VBox();
            root.setSpacing(20);
            root.setPadding(new Insets(20));
            helloController = new HelloController();
            Label welcomeText = new Label("Reset je wachtwoord");
            welcomeText.setStyle("-fx-font-size: 24px; -fx-text-fill: darkblue;");

            textField = new TextField();
            textField.setPromptText("Voer je gebruikersnaam in");
            textField.setStyle("-fx-background-color: white;");

            passwordField = new PasswordField();
            passwordField.setPromptText("Voer je nieuwe wachtwoord in");
            passwordField.setStyle("-fx-background-color: white;");

            passwordField2 = new PasswordField();
            passwordField2.setPromptText("Bevestig je nieuwe wachtwoord");
            passwordField2.setStyle("-fx-background-color: white;");

            Button resetPasswordButton = new Button("Wachtwoord herstellen");
            resetPasswordButton.setOnAction(event -> resetPasswordFunction(event));
            resetPasswordButton.getStyleClass().add("hover-button");
            resetPasswordButton.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

            Button backButton = new Button("Terug gaan");
            backButton.setOnAction(this::switchToHelloScene);
            backButton.getStyleClass().add("hover-button");
            backButton.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

            root.getChildren().addAll(welcomeText, textField, passwordField, passwordField2, backButton, resetPasswordButton);

            return root;
        }

    public void switchToHelloScene(ActionEvent event) {
        helloController = new HelloController();
        Scene helloScene = helloController.createHelloScene();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(helloScene);
        stage.setTitle("AIsistify");
        stage.show();
    }

    public void resetPasswordFunction(ActionEvent event) {
        String gebruikersnaam = textField.getText();
        String nieuwWachtwoord = passwordField.getText();
        String bevestigWachtwoord = passwordField2.getText();

        // Check if the new password and confirmation match
        if (!nieuwWachtwoord.equals(bevestigWachtwoord)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fout");
            alert.setHeaderText(null);
            alert.setContentText("Nieuwe wachtwoord en bevestiging komen niet overeen.");
            alert.showAndWait();
            return;
        }

        // Find the user based on the provided username
        User gebruiker = userManager.zoekGebruikerOpGebruikersnaam(gebruikersnaam);

        // Check if the user exists
        if (gebruiker == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fout");
            alert.setHeaderText(null);
            alert.setContentText("Gebruiker niet gevonden.");
            alert.showAndWait();
            return;
        }

        // Reset the user's password
        gebruiker.setPassword(nieuwWachtwoord);

        // Update the user's password in the users list
        for (User user : helloController.getUsers()) {
            if (user.getUsername().equals(gebruiker.getUsername())) {
                user.setPassword(nieuwWachtwoord);
                break;
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succes");
        alert.setHeaderText(null);
        alert.setContentText("Wachtwoord succesvol gereset.");
        alert.showAndWait();

        // Switch back to the login screen
        switchToHelloScene(event);
    }


    public void handleResetPassword(ActionEvent event) {
        resetPasswordFunction(event);
    }
}
