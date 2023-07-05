package com.example.proj2.GuiManager;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class CreateSettingsStrategy implements SceneCreationStrategy {
    private SceneFunctions sceneFunctions;
    private SceneSwitcher sceneSwitcher;

    public CreateSettingsStrategy(SceneSwitcher sceneSwitcher,SceneFunctions sceneFunctions){
            this.sceneSwitcher = sceneSwitcher;
            this.sceneFunctions = sceneFunctions;

    }
    @Override
    public Scene createScene() {
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
            returnButton.setOnAction(event -> sceneSwitcher.switchTocreateChatPage(event));

            Button logoutButton = new Button("Logout");
            logoutButton.getStyleClass().add("settings-button");
            logoutButton.setLayoutX(450);
            logoutButton.setLayoutY(50);
            logoutButton.setOnAction(event -> sceneFunctions.logout());

            root.getChildren().addAll(lightModeButton, darkModeButton, languageButton, returnButton, logoutButton);

            return new Scene(root);

    }
}
