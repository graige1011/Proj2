package com.example.proj2;

import com.example.proj2.User;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Scene4Controller {
    private List<User> users;

    public VBox createScene4UI() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        Label successLabel = new Label("Succesvol een nieuw account aangemaakt. Wil je inloggen?");
        successLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: darkblue;");

        Button backButton = new Button("Terug naar Inloggen");
        backButton.setOnAction(this::switchToScene1);
        backButton.getStyleClass().add("hover-button");
        backButton.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        root.getChildren().addAll(successLabel, backButton);

        return root;
    }

    public void switchToScene1(ActionEvent event) {
        HelloController helloController = new HelloController();
        Scene helloScene = helloController.createHelloScene();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(helloScene);
        stage.setTitle("AIsistify");
        stage.show();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
