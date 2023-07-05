package com.example.proj2.GuiManager;

import com.example.proj2.login.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class LoginPageStrategy implements SceneCreationStrategy {
    private SceneFunctions sceneFunctions;
    private SceneSwitcher sceneSwitcher;
    //deze zijn nodig voor createScene5
    private Label usernameLabel;
    private Label passwordLabel;
    private Button loginButton;
    private Button createAccountButton;
    private Button forgotPasswordButton;
    private Button forgotUsernameButton;
    private Button forgotEmailButton;
    private PasswordField passwordField;
    private TextField usernameField;
    private ArrayList<User> userList;


    public LoginPageStrategy(SceneSwitcher sceneSwitcher,ArrayList<User> userList) {
        this.sceneSwitcher = sceneSwitcher;
        this.userList = userList;
    }
    @Override
    public Scene createScene() {
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

        usernameLabel = new Label("Gebruikersnaam:");
        usernameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: lightblue;");
        gridPane.add(usernameLabel, 0, 1);

        usernameField = new TextField();
        usernameField.setPromptText("Voer gebruikersnaam in");
        usernameField.setStyle("-fx-background-color: white;");
        usernameField.setPrefWidth(200);
        usernameField.setPrefHeight(30);
        gridPane.add(usernameField, 1, 1);

        passwordLabel = new Label("Wachtwoord:");
        passwordLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: lightblue;");
        gridPane.add(passwordLabel, 0, 2);

        passwordField = new PasswordField();
        passwordField.setPromptText("Voer wachtwoord in");
        passwordField.setStyle("-fx-background-color: white;");
        passwordField.setPrefWidth(200);
        passwordField.setPrefHeight(30);
        gridPane.add(passwordField, 1, 2);



        ChoiceBox<String> languageChoiceBox = new ChoiceBox<>();
        languageChoiceBox.getItems().addAll("English", "Dutch", "Spanish");
        languageChoiceBox.setValue("English");
        languageChoiceBox.setStyle("-fx-background-color: white;");
        gridPane.add(languageChoiceBox, 1, 10);



        loginButton = new Button("Log in");
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Call a method or class to handle the login logic
            boolean isAuthenticated = false;
            for (User user : userList) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    isAuthenticated = true;
                    break;
                }
            }

            if (isAuthenticated) {
                sceneSwitcher.switchTocreateChatPage(event);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password. Please try again.");
                alert.showAndWait();
            }
        });

        loginButton.getStyleClass().add("hover-button");
        loginButton.setStyle("-fx-min-width: 150px;");
        gridPane.add(loginButton, 0, 3, 2, 1);

        createAccountButton = new Button("Nieuw Account Aanmaken");
        createAccountButton.setOnAction(event -> {
            // sceneFunctions.handleAccountCreation();
            sceneSwitcher.switchToNewAccountPage(event);
        });
        createAccountButton.getStyleClass().add("hover-button");
        createAccountButton.setStyle("-fx-min-width: 150px;");
        gridPane.add(createAccountButton, 0, 4, 2, 1);

        forgotPasswordButton = new Button("Wachtwoord Vergeten");
        forgotPasswordButton.setOnAction(event -> {
            // sceneFunctions.handlePasswordRecovery();
            sceneSwitcher.switchToResetPasswordPage(event);
        });
        forgotPasswordButton.getStyleClass().add("hover-button");
        forgotPasswordButton.setStyle("-fx-min-width: 150px;");
        gridPane.add(forgotPasswordButton, 0, 5, 2, 1);

        forgotUsernameButton = new Button("Gebruikersnaam Vergeten");
        forgotUsernameButton.setOnAction(event -> {
            sceneSwitcher.switchToResetUsernamePage(event);
        });
        forgotUsernameButton.getStyleClass().add("hover-button");
        forgotUsernameButton.setStyle("-fx-min-width: 150px;");
        gridPane.add(forgotUsernameButton, 0, 6, 2, 1);

        forgotEmailButton = new Button("Forgot Email");
        forgotEmailButton.setOnAction(event -> {
            sceneSwitcher.switchToResetEmailPage(event);
        });
        forgotEmailButton.getStyleClass().add("hover-button");
        forgotEmailButton.setStyle("-fx-min-width: 150px;");
        gridPane.add(forgotEmailButton, 0, 7, 2, 1);


        root.getChildren().add(gridPane);


        languageChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            SceneFunctions.applyLanguage(usernameLabel, passwordLabel, loginButton, createAccountButton,
                    forgotPasswordButton, forgotUsernameButton, forgotEmailButton, newValue);
        });


        // Allow the VBox and GridPane to grow and fill the available space
        VBox.setVgrow(gridPane, Priority.ALWAYS);

        return new Scene(root, 800, 600); // Set the desired size of the scene
    }


}