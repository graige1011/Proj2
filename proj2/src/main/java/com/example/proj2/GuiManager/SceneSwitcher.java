package com.example.proj2.GuiManager;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneSwitcher {
    private SceneCreation sceneCreation;

    public SceneSwitcher(SceneCreation sceneCreation) {
        this.sceneCreation = sceneCreation;
    }

    public void switchToScene1(ActionEvent event) {
        Scene scene1 = sceneCreation.createScene1();
        scene1.getStylesheets().add("file:///C:/Users/keanu/Desktop/github/PROJ2/proj2/Proj2/proj2/src/main/java/com/example/proj2/GuiManager/styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
    }
    public void switchToScene2(ActionEvent event) {
        Scene scene2 = sceneCreation.createScene2();
        scene2.getStylesheets().add("file:///C:/Users/keanu/Desktop/github/PROJ2/proj2/Proj2/proj2/src/main/java/com/example/proj2/GuiManager/styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
    }
    public void switchToScene3(ActionEvent event) {
        Scene scene3 = sceneCreation.createScene3();
        scene3.getStylesheets().add("file:///C:/Users/keanu/Desktop/github/PROJ2/proj2/Proj2/proj2/src/main/java/com/example/proj2/GuiManager/styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
    }
    public void switchToScene4(ActionEvent event) {
        Scene scene4 = sceneCreation.createScene3();
        scene4.getStylesheets().add("file:///C:/Users/keanu/Desktop/github/PROJ2/proj2/Proj2/proj2/src/main/java/com/example/proj2/GuiManager/styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene4);
    }

    public void switchToScene5(ActionEvent event) {
        Scene scene5 = sceneCreation.createScene4(); // Fix the method name to createScene5()

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        VBox newRoot = new VBox();
        newRoot.getChildren().add(scene5.getRoot());

        window.setScene(new Scene(newRoot, 600, 800)); // Set newRoot as the scene's root

        window.setTitle("AIsistify");
        window.show();
    }

    public void switchToSettings(ActionEvent event) {
        Scene settingsScene = sceneCreation.createSettings();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        VBox newRoot = new VBox();
        newRoot.getChildren().add(settingsScene.getRoot());

        window.setScene(new Scene(newRoot, 600, 800)); // Set newRoot as the scene's root

        window.setTitle("AIsistify");
        window.show();
    }
}
