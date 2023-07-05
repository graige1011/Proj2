package com.example.proj2.GuiManager;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneSwitcher {
    private SceneCreation sceneCreation;

    public SceneSwitcher(SceneCreation sceneCreation) {
        this.sceneCreation = sceneCreation;
    }

    public void switchToLoginPage(ActionEvent event) {
        Scene scene1 = sceneCreation.createLoginPage();
//        scene1.getStylesheets().add("file:///C:/Users/keanu/Desktop/github/PROJ2/proj2/Proj2/proj2/src/main/java/com/example/proj2/GuiManager/styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
    }

    public void switchToNewAccountPage(ActionEvent event) {
        Scene scene2 = sceneCreation.createNewAccountPage();
//        scene2.getStylesheets().add("file:///C:/Users/keanu/Desktop/github/PROJ2/proj2/Proj2/proj2/src/main/java/com/example/proj2/GuiManager/styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
    }

    public void switchToResetPasswordPage(ActionEvent event) {
        Scene scene3 = sceneCreation.createResetPasswordPage();
//        scene3.getStylesheets().add("file:///C:/Users/keanu/Desktop/github/PROJ2/proj2/Proj2/proj2/src/main/java/com/example/proj2/GuiManager/styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
    }

    public void switchToResetUsernamePage(ActionEvent event) {
        Scene resetUsernameScene = sceneCreation.createResetUsernamePage();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(resetUsernameScene);
    }

    public void switchTocreateChatPage(ActionEvent event) {
        Scene scene5 = sceneCreation.createChatPage(); // Fix the method name to createScene5()

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        VBox newRoot = new VBox();
        newRoot.getChildren().add(scene5.getRoot());

        window.setScene(new Scene(newRoot, 600, 800)); // Set newRoot as the scene's root

        window.setTitle("AIsistify");
        window.show();
    }
}