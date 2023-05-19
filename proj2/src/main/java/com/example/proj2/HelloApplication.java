package com.example.proj2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Image icon = new Image("C:\\Users\\Humberto de Castro\\OneDrive\\Documents\\GitHub\\PROJ\\Proj2\\proj2\\src\\42.jpg");
        stage.getIcons().add(icon);
        stage.setTitle("AIsistify");
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm()); // voor hover effect
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}