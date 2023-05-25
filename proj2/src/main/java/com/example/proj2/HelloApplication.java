package com.example.proj2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        HelloController helloController = new HelloController();
        Scene scene = helloController.createHelloScene();

        stage.getIcons().add(new Image("https://media.licdn.com/dms/image/C4D0BAQHX1qomlcFEUA/company-logo_200_200/0/1548250566657?e=2147483647&amp;v=beta&amp;t=uXtCqDfNta4julyp-JYgX3X2n2pDgF8tesPnDZuGtk8"));
        stage.setTitle("AIsistify");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
