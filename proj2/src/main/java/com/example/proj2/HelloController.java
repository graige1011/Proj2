package com.example.proj2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button loginButton;

    @FXML
    private Button forgotPasswordButton;

    @FXML
    private Button createAccountButton;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;



    private List<User> users;
    @FXML
    private Button toggleDarkModeButton;
    private boolean darkMode = false;

    public HelloController() {
        users = new ArrayList<>();
        // voeg dummy users toe
        users.add(new User("humberto", "1"));
        users.add(new User("keanu", "2"));
        users.add(new User("graige", "3"));
    }
    @FXML
    public void toggleDarkMode(ActionEvent event) {
        darkMode = !darkMode; // Toggle dark mode value

        // Apply dark mode styles to the current scene
        Node sourceNode = (Node) event.getSource();
        Scene scene = sourceNode.getScene();

        if (darkMode) {
            scene.getStylesheets().add(getClass().getResource("dark.css").toExternalForm());
        } else {
            scene.getStylesheets().remove(getClass().getResource("dark.css").toExternalForm());
        }
    }



    @FXML
    public void handleLogin(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (validateLogin(username, password)) {
            switchToScene5(event);
        } else {
            // Display an error message or perform appropriate action for failed login
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password");
            alert.showAndWait();
        }
    }
    // Method to validate login credentials
    private boolean validateLogin(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    @FXML
    protected void switchToScene5(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene5.fxml"));
        Parent scene2Root = fxmlLoader.load();
        Scene5Controller scene5Controller = fxmlLoader.getController();
        scene5Controller.setUsers((ArrayList<User>) users); // Pass the users ArrayList to the Scene5Controller
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(scene2Root, 800, 600));
        stage.setTitle("AIsistify");
        stage.show();
    }

    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene2.fxml"));
        Parent scene2Root = fxmlLoader.load();
        Scene2Controller scene2Controller = fxmlLoader.getController(); // Get the controller of the scene2.fxml
        scene2Controller.setUsers(users); // Pass the users ArrayList to the Scene2Controller
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(scene2Root, 800, 600));
        stage.setTitle("AIsistify");
        stage.show();
    }


    @FXML
    public void switchToScene3(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene3.fxml"));
        Parent scene2Root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(scene2Root, 800, 600));
        stage.setTitle("AIsistify");
        stage.show();
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

}

