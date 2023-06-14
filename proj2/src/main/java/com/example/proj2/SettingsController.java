package com.example.proj2;

import com.example.proj2.HelloController;
import com.example.proj2.Scene5Controller;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsController {
    public AnchorPane createSettingsUI() {
        AnchorPane root = new AnchorPane();
        root.setPrefSize(600, 400);

        Button lightModeButton = new Button("Light Mode");
        lightModeButton.getStyleClass().add("settings-button");
        lightModeButton.setLayoutX(50);
        lightModeButton.setLayoutY(50);
        lightModeButton.setOnAction(this::changeToLightMode);

        Button darkModeButton = new Button("Dark Mode");
        darkModeButton.getStyleClass().add("settings-button");
        darkModeButton.setLayoutX(150);
        darkModeButton.setLayoutY(50);
        darkModeButton.setOnAction(this::changeToDarkMode);

        Button languageButton = new Button("Change Language");
        languageButton.getStyleClass().add("settings-button");
        languageButton.setLayoutX(250);
        languageButton.setLayoutY(50);
        languageButton.setOnAction(this::changeLanguage);

        Button returnButton = new Button("Return to Chat");
        returnButton.getStyleClass().add("settings-button");
        returnButton.setLayoutX(350);
        returnButton.setLayoutY(50);
        returnButton.setOnAction(this::switchToScene5);

        Button logoutButton = new Button("Logout");
        logoutButton.getStyleClass().add("settings-button");
        logoutButton.setLayoutX(450);
        logoutButton.setLayoutY(50);
        logoutButton.setOnAction(this::logout);

        root.getChildren().addAll(lightModeButton, darkModeButton, languageButton, returnButton, logoutButton);

        return root;
    }

    public void changeToLightMode(ActionEvent event) {
        // Implement light mode logic here
    }

    public void changeToDarkMode(ActionEvent event) {
        // Implement dark mode logic here
    }

    public void changeLanguage(ActionEvent event) {
        // Implement language change logic here
    }

    public void switchToScene5(ActionEvent event) {
        Scene5Controller scene5Controller = new Scene5Controller();
        VBox scene5Root = scene5Controller.createScene5UI();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(scene5Root, 800, 600));
        stage.setTitle("AIsistify");
        stage.show();
    }

    public void logout(ActionEvent event) {
        // Perform any necessary logout actions, such as clearing user session data

        // Navigate to the "Hello View" or login screen
        HelloController helloController = new HelloController();
        Scene helloScene = helloController.createHelloScene();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(helloScene);
        stage.setTitle("AIsistify");
        stage.show();
    }
}
