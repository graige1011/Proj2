package com.example.proj2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Scene4Controller {

    private List<User> users;

    @FXML
    public void switchToScene1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent scene2Root = fxmlLoader.load();
        HelloController helloController = fxmlLoader.getController();
        helloController.setUsers(users); // Geef de bijgewerkte lijst met gebruikers door aan HelloController
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(scene2Root, 800, 600));
        stage.setTitle("AIsistify");
        stage.show();
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
