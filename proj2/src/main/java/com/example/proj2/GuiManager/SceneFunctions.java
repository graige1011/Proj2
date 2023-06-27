package com.example.proj2.GuiManager;

import com.example.proj2.chatMessage.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SceneFunctions {
    private TextField chatField;
    private Button enterButton;
    private VBox chatHistoryMenu;

    private Label placeholderLabel;
    private Label chatHistoryLabel;
    private VBox chatBox;
    private String selectedChat; // New variable to store the selected chat name
    private TextToTextChat textChat; // Instance of TextToTextChat

    private TextToBooleanChat booleanChat; // Instance of BooleanChat

    private Map<String, QueryResolutionStrategy<?, ?>> chats = new HashMap<>();

    public SceneFunctions() {
        chatHistoryLabel = new Label();
        chatBox = new VBox();
        this.chatField = new TextField();
    }


    public void changeToLightMode( ) {}
    public void changeToDarkMode( ) {}
    public void changeLanguage( ) {}
    public void logout(){}


}


