package com.example.proj2.GuiManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        SceneCreation sceneCreation = new SceneCreation();
        Scene scene = sceneCreation.createLoginPage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("AIsistify");
        primaryStage.setWidth(800); // Set the window width
        primaryStage.setHeight(600); // Set the window height
        primaryStage.show();
    }
}
