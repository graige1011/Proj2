package com.example.proj2.GuiManager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class SceneCreation {

    private SceneFunctions sceneFunctions;
    private SceneSwitcher sceneSwitcher;

    public SceneCreation() {

        sceneFunctions = new SceneFunctions();
        sceneSwitcher = new SceneSwitcher(this);
    }

    public Scene createScene1() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setStyle("-fx-background-color: white;");
        root.setPadding(new Insets(40));

        ImageView imageView = new ImageView(new Image("https://media.licdn.com/dms/image/C4D0BAQHX1qomlcFEUA/company-logo_200_200/0/1548250566657?e=2147483647&amp;v=beta&amp;t=uXtCqDfNta4julyp-JYgX3X2n2pDgF8tesPnDZuGtk8"));
        root.getChildren().add(imageView);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20); // Adjusted vgap value
        gridPane.getColumnConstraints().add(new ColumnConstraints());

        gridPane.getRowConstraints().add(new RowConstraints());
        gridPane.getRowConstraints().add(new RowConstraints());

        Label titleLabel = new Label("AIsistify");
        titleLabel.setStyle("-fx-font-size: 36px; -fx-text-fill: darkblue; -fx-font-weight: bold;");
        gridPane.add(titleLabel, 0, 0);

        Label usernameLabel = new Label("Gebruikersnaam:");
        usernameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: lightblue;");
        gridPane.add(usernameLabel, 0, 1);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Voer gebruikersnaam in");
        usernameField.setStyle("-fx-background-color: white;");
        usernameField.setPrefWidth(200);
        usernameField.setPrefHeight(30);
        gridPane.add(usernameField, 1, 1);

        Label passwordLabel = new Label("Wachtwoord:");
        passwordLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: lightblue;");
        gridPane.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Voer wachtwoord in");
        passwordField.setStyle("-fx-background-color: white;");
        passwordField.setPrefWidth(200);
        passwordField.setPrefHeight(30);
        gridPane.add(passwordField, 1, 2);

        Button loginButton = new Button("Log in");
        loginButton.setOnAction(event -> {
            // sceneFunctions.handleLogin(usernameField.getText(), passwordField.getText());
            sceneSwitcher.switchToScene2(event);
        });
        loginButton.getStyleClass().add("hover-button");
        loginButton.setStyle("-fx-min-width: 150px;");
        gridPane.add(loginButton, 0, 3, 2, 1);

        Button createAccountButton = new Button("Nieuw Account Aanmaken");
        createAccountButton.setOnAction(event -> {
            // sceneFunctions.handleAccountCreation();
            sceneSwitcher.switchToScene2(event);
        });
        createAccountButton.getStyleClass().add("hover-button");
        createAccountButton.setStyle("-fx-min-width: 150px;");
        gridPane.add(createAccountButton, 0, 4, 2, 1);

        Button forgotPasswordButton = new Button("Wachtwoord Vergeten");
        forgotPasswordButton.setOnAction(event -> {
            // sceneFunctions.handlePasswordRecovery();
            sceneSwitcher.switchToScene3(event);
        });
        forgotPasswordButton.getStyleClass().add("hover-button");
        forgotPasswordButton.setStyle("-fx-min-width: 150px;");
        gridPane.add(forgotPasswordButton, 0, 5, 2, 1);

        root.getChildren().add(gridPane);

        // Allow the VBox and GridPane to grow and fill the available space
        VBox.setVgrow(gridPane, Priority.ALWAYS);

        return new Scene(root, 800, 600); // Set the desired size of the scene
    }
    public Scene createScene2() {
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
            sceneSwitcher.switchToScene3(event);
        });
        registerButton.getStyleClass().add("hover-button");

        root.getChildren().addAll(welcomeText, textField, textField3, textField4, passwordField, registerButton);

        return new Scene(root, 600, 800); // Set the desired size of the scene
    }
    public Scene createScene3() {
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
            sceneSwitcher.switchToScene1(event);
        });
        resetPasswordButton.getStyleClass().add("hover-button");

        Button backButton = new Button("Terug gaan");
        backButton.setOnAction(event -> sceneSwitcher.switchToScene1(event));
        backButton.getStyleClass().add("hover-button");

        root.getChildren().addAll(welcomeText, textField, passwordField, passwordField2, backButton, resetPasswordButton);

        return new Scene(root, 600, 800); // Set the desired size of the scene
    }
    public Scene createScene4() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setStyle("-fx-background-color: white;");

        Label successLabel = new Label("Succesvol een nieuw account aangemaakt. Wil je inloggen?");
        successLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: darkblue;");

        Button backButton = new Button("Terug naar Inloggen");
        backButton.setOnAction(event -> sceneSwitcher.switchToScene1(event));
        backButton.getStyleClass().add("hover-button");

        root.getChildren().addAll(successLabel, backButton);

        return new Scene(root, 600, 800); // Set the desired size of the scene
    }

    public Scene createSettings() {
        AnchorPane root = new AnchorPane();
        root.setPrefSize(600, 400);

        Button lightModeButton = new Button("Light Mode");
        lightModeButton.getStyleClass().add("settings-button");
        lightModeButton.setLayoutX(50);
        lightModeButton.setLayoutY(50);
        lightModeButton.setOnAction(event -> sceneFunctions.changeToLightMode());

        Button darkModeButton = new Button("Dark Mode");
        darkModeButton.getStyleClass().add("settings-button");
        darkModeButton.setLayoutX(150);
        darkModeButton.setLayoutY(50);
        darkModeButton.setOnAction(event -> sceneFunctions.changeToDarkMode());

        Button languageButton = new Button("Change Language");
        languageButton.getStyleClass().add("settings-button");
        languageButton.setLayoutX(250);
        languageButton.setLayoutY(50);
        languageButton.setOnAction(event -> sceneFunctions.changeLanguage());

        Button returnButton = new Button("Return to Chat");
        returnButton.getStyleClass().add("settings-button");
        returnButton.setLayoutX(350);
        returnButton.setLayoutY(50);
        returnButton.setOnAction(event -> sceneSwitcher.switchToScene5(event));

        Button logoutButton = new Button("Logout");
        logoutButton.getStyleClass().add("settings-button");
        logoutButton.setLayoutX(450);
        logoutButton.setLayoutY(50);
        logoutButton.setOnAction(event -> sceneFunctions.logout());

        root.getChildren().addAll(lightModeButton, darkModeButton, languageButton, returnButton, logoutButton);

        return new Scene(root);
    }




}
