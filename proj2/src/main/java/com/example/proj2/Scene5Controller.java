package com.example.proj2;

import com.example.proj2.User;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Scene5Controller {
    private TextField textBox;
    private Button enterButton;
    private Label label1;
    private Label label2;
    private Label label3;
    private int messageCounter = 1;
    private ArrayList<User> users;

    public VBox createScene5UI() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        VBox chatHistory = new VBox();
        chatHistory.setAlignment(Pos.TOP_RIGHT);

        Label chatHistoryLabel = new Label("Chat History");
        chatHistoryLabel.setStyle("-fx-font-weight: bold;");

        label1 = new Label("Message 1");
        label2 = new Label("Message 2");
        label3 = new Label("Message 3");

        chatHistory.getChildren().addAll(chatHistoryLabel, label1, label2, label3);

        HBox settingsBox = new HBox();
        settingsBox.setAlignment(Pos.BOTTOM_LEFT);

        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction(this::openSettings);

        settingsBox.getChildren().add(settingsButton);

        HBox inputBox = new HBox();
        inputBox.setAlignment(Pos.BOTTOM_CENTER);

        textBox = new TextField();
        textBox.setPromptText("Enter text");
        textBox.setStyle("-fx-background-color: white;");

        enterButton = new Button("Enter");
        enterButton.getStyleClass().add("hover-button");
        enterButton.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        enterButton.setOnAction(this::enter);

        // Logout button
        // Uncomment the following lines once the logoutButton is added to the FXML
//        Button logoutButton = new Button("Log out");
//        logoutButton.setOnAction(this::handleLogout);

        inputBox.getChildren().addAll(textBox, enterButton);

        root.getChildren().addAll(chatHistory, settingsBox, inputBox);

        return root;
    }

    public void openSettings(ActionEvent event) {
        SettingsController settingsController = new SettingsController();
        AnchorPane settingsRoot = settingsController.createSettingsUI();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(settingsRoot, 800, 600));
        stage.setTitle("AIsistify");
        stage.show();
    }

    public void enter(ActionEvent event) {
        String message = textBox.getText().trim();
        if (!message.isEmpty()) {
            displayMessage(message);
            textBox.clear();
        }
    }

    private void displayMessage(String message) {
        switch (messageCounter) {
            case 1:
                label1.setText(message);
                break;
            case 2:
                label2.setText(message);
                break;
            case 3:
                label3.setText(message);
                break;
            default:
                shiftMessages();
                label3.setText(message);
                break;
        }
        messageCounter++;
    }

    private void shiftMessages() {
        label1.setText(label2.getText());
        label2.setText(label3.getText());
        label3.setText("");
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void handleLogout(ActionEvent event) {
        //switch to hellocontroller
    }
}
