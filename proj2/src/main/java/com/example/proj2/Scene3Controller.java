package com.example.proj2;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Scene3Controller {
    private TextField textField;
    private PasswordField passwordField;
    private PasswordField passwordField2;

    public VBox createScene3UI() {
        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));

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
        resetPasswordButton.setOnAction(this::idkfiller);
        resetPasswordButton.getStyleClass().add("hover-button");
        resetPasswordButton.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        root.getChildren().addAll(welcomeText, textField, passwordField, passwordField2, resetPasswordButton);

        return root;
    }

    public void idkfiller(ActionEvent event) {
        // Your code logic for the idkfiller method
    }
}
