package com.example.proj2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {

    @FXML
    public void changeToLightMode(ActionEvent event) throws IOException {
    }

    @FXML
    public void changeToDarkMode(ActionEvent event) throws IOException {
    }

    @FXML
    public void changeLanguage(ActionEvent event) throws IOException {
    }

    @FXML
    public void switchToScene5(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene5.fxml")); //hier moet de settings page komen
        Parent scene2Root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(scene2Root, 800, 600));
        stage.setTitle("AIsistify");
        stage.show();
    }
    @FXML
    public void logout(ActionEvent event) throws IOException{
        // Perform any necessary logout actions, such as clearing user session data

        // Navigate to the "Hello View" or login screen
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml")); //hier moet de settings page komen
        Parent scene2Root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(scene2Root, 800, 600));
        stage.setTitle("AIsistify");
        stage.show();
    }
}
