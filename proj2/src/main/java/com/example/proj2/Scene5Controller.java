package com.example.proj2;

import com.example.proj2.chatMessage.ChatHistoryManager;
import com.example.proj2.chatMessage.ChatMessageFactory;
import com.example.proj2.chatMessage.ChatMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Scene5Controller {


    private TextField textBox;
    private Button enterButton;
    private VBox chatHistoryMenu;

    private Label placeholderLabel;
    private Label chatHistoryLabel;
    private VBox chatBox;
    private ChatMessageFactory messageFactory;
    private ChatHistoryManager historyManager;
    private String selectedChat; // New variable to store the selected chat name




    public VBox createScene5UI() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("root");
        root.setFillWidth(true); // Hiermee wordt de VBox ingesteld om de beschikbare ruimte in te nemen

        HBox headerBox = new HBox();
        headerBox.setAlignment(Pos.CENTER);
        headerBox.getStyleClass().add("header");
        headerBox.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        Label titleLabel = new Label("AIsistify");
        titleLabel.getStyleClass().add("header");
        titleLabel.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        headerBox.getChildren().add(titleLabel);

        HBox chatContainer = new HBox();
        chatContainer.setAlignment(Pos.CENTER);
        chatContainer.setSpacing(10);
        chatContainer.setFillHeight(true);
        VBox.setVgrow(chatContainer, Priority.ALWAYS);
        HBox.setHgrow(chatContainer,Priority.ALWAYS);

        HBox chatHistoryLabelBox = new HBox();
        chatHistoryLabelBox.setAlignment(Pos.CENTER);
        chatHistoryLabelBox.getStyleClass().add("wrapper");
        chatHistoryLabel = new Label("Chat");
        chatHistoryLabel.setStyle("-fx-font-weight: bold;");
        chatHistoryLabel.getStyleClass().add("wrapper");
        chatHistoryLabelBox.getChildren().add(chatHistoryLabel);

        chatHistoryMenu = new VBox();
        chatHistoryMenu.getStyleClass().add("chat_history_menu");
        chatHistoryMenu.setPrefWidth(300);
        chatHistoryMenu.setAlignment(Pos.TOP_LEFT);
        chatHistoryMenu.setSpacing(10);
        VBox.setVgrow(chatHistoryMenu, Priority.ALWAYS);
        HBox.setHgrow(chatHistoryMenu, Priority.ALWAYS);
        chatHistoryMenu.setMaxWidth(Double.MAX_VALUE);
        chatHistoryMenu.getChildren().add(chatHistoryLabelBox);
        chatHistoryMenu.getChildren().addAll(
                createChatHistoryButton("Chat 1"),
                createChatHistoryButton("Chat 2"),
                createChatHistoryButton("Chat 3")
        );


        chatBox = new VBox();
        chatBox.getStyleClass().add("chat_box");
        chatBox.setAlignment(Pos.CENTER);
        chatBox.setSpacing(10);
        VBox.setVgrow(chatBox, Priority.ALWAYS);
        HBox.setHgrow(chatBox, Priority.ALWAYS);
        placeholderLabel = new Label("No messages yet");
        chatBox.getChildren().addAll(chatHistoryLabelBox, placeholderLabel);

        SplitPane splitPane = new SplitPane();
        splitPane.getStyleClass().add("split-pane");
        splitPane.setPrefWidth(600); // Set the preferred width of the split pane
        splitPane.setDividerPositions(0.2); // Set the initial divider position
        splitPane.getItems().addAll(chatHistoryMenu, chatBox);


        VBox.setVgrow(splitPane, Priority.ALWAYS);
        HBox.setHgrow(splitPane, Priority.ALWAYS);

        HBox inputBox = new HBox();
        inputBox.setAlignment(Pos.BOTTOM_CENTER);

        textBox = new TextField();
        textBox.setPrefWidth(300); // Set the preferred width of the text field
        textBox.setPrefHeight(40); // Set the preferred height of the text field
        textBox.setPromptText("Enter text");
        textBox.getStyleClass().add("input");
        textBox.setStyle("-fx-background-color: white;");

        enterButton = new Button("Enter");
        enterButton.getStyleClass().add("hover-button");
        enterButton.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        enterButton.setOnAction(this::enter);

        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction(this::openSettings);
        settingsButton.getStyleClass().add("settings_button");

        StackPane chatBoxContainer = new StackPane();
        chatBoxContainer.setAlignment(Pos.TOP_CENTER);
        chatBoxContainer.getChildren().add(chatBox);

        HBox.setHgrow(textBox, Priority.ALWAYS);
        HBox.setHgrow(enterButton, Priority.NEVER);
        HBox.setHgrow(settingsButton, Priority.NEVER);

        VBox.setVgrow(textBox, Priority.ALWAYS);
        VBox.setVgrow(enterButton, Priority.NEVER);
        VBox.setVgrow(settingsButton, Priority.NEVER);

        inputBox.getChildren().addAll(textBox, enterButton, settingsButton);
        chatContainer.getChildren().addAll(splitPane, chatBoxContainer);
        root.getChildren().addAll(headerBox, chatContainer, inputBox);

        String initialChat = "Chat 1";
        openChat(initialChat);


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
            if (messageFactory != null && historyManager != null) {
                // Create chat message using the factory
                ChatMessage chat1 = messageFactory.createMessage(ChatMessageFactory.MessageType.TEXT, message);
                // Get the selected chat name from the chat history menu
                String selectedChat = getSelectedChat();

                // Add chat message to the history manager
                historyManager.addMessage(chat1, selectedChat);

                displayMessage(chat1);
                textBox.clear();
            } else {
                System.out.println("ChatMessageFactory or ChatHistoryManager is null. Please set them before entering a message.");
            }
        }
    }
    private Button createChatHistoryButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("chat_history_button");
        button.setOnAction(e -> openChat(text)); // Add action to open the selected chat
        return button;
    }
    private void openChat(String chatName) {
        // Update the chat history label
        setChatHistoryLabel(chatName);

        // Set the selected chat
        selectedChat = chatName;

        // Clear the existing messages in the chat box
        chatBox.getChildren().clear();

        // Retrieve the chat messages for the selected chat from the history manager
        List<ChatMessage> chatMessages = historyManager.getChatMessages(chatName);

        // Display the chat messages in the chat box
        if (chatMessages.isEmpty()) {

            chatBox.getChildren().add(placeholderLabel);
        } else {
            for (ChatMessage message : chatMessages) {
                displayMessage(message);
            }
        }
    }
    private void displayMessage(ChatMessage message) {
        Label messageLabel = new Label(message.getContent());
        chatBox.getChildren().add(messageLabel);
        if (chatBox.getChildren().contains(placeholderLabel)) {
            chatBox.getChildren().remove(placeholderLabel);
        }
    }

    public void setChatHistoryLabel(String label) {
        chatHistoryLabel.setText(label);
    }


    public void setHistoryManager(ChatHistoryManager historyManager) {
        this.historyManager = historyManager;
    }
    public void setMessageFactory(ChatMessageFactory messageFactory) {
        this.messageFactory = messageFactory;
    }
    private String getSelectedChat() {
        return selectedChat;
    }



}
