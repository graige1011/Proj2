package com.example.proj2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Scene5Controller {

    @FXML
    private TextField textBox;

    @FXML
    private Button enterButton;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    private int messageCounter = 1;

    @FXML
    private void enter(ActionEvent event) throws IOException {
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
}
