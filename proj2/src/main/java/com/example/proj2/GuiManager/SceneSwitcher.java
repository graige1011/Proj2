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
        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white;");
        // Add the desired content to the VBox

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        VBox newRoot = new VBox();
        newRoot.getChildren().add(root);

        stage.setScene(new Scene(newRoot, 600, 800));
        stage.setTitle("AIsistify");
        stage.show();
    }

    public void switchToScene2(ActionEvent event) {
        Scene scene2 = sceneCreation.createScene2(); // Create Scene 2 using the createScene2() method

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        VBox root = new VBox(); // Create a new VBox
        root.getChildren().add(scene2.getRoot()); // Add the root of Scene 2 to the new VBox

        window.setScene(new Scene(root)); // Set the new VBox as the root of the stage

        window.show();
    }



    public void switchToScene3(ActionEvent event) {
        Scene scene3 = sceneCreation.createScene3();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        VBox root = new VBox();
        root.getChildren().add(scene3.getRoot());

        window.setScene(new Scene(root, 600, 800));
        window.setTitle("AIsistify");
        window.show();
    }

    public void switchToScene4(ActionEvent event) {
        Scene scene4 = sceneCreation.createScene4();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        VBox root = new VBox();
        root.getChildren().add(scene4.getRoot());

        window.setScene(new Scene(root, 600, 800));
        window.setTitle("AIsistify");
        window.show();
    }

    public void switchToScene5(ActionEvent event) {
        Scene scene5 = sceneCreation.createScene4(); // this should be createScene5

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        VBox root = new VBox();
        root.getChildren().add(scene5.getRoot());

        window.setScene(new Scene(root, 600, 800));
        window.setTitle("AIsistify");
        window.show();
    }

    public void switchToSettings(ActionEvent event) {
        Scene settingsScene = sceneCreation.createSettings();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        VBox root = new VBox();
        root.getChildren().add(settingsScene.getRoot());

        window.setScene(new Scene(root, 600, 800));
        window.setTitle("AIsistify");
        window.show();
    }

}
