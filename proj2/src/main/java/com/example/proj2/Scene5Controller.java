package com.example.proj2;
import com.example.proj2.chatMessage.TextToTextChat;
import com.example.proj2.chatMessage.TextToBooleanChat;
import com.example.proj2.chatMessage.TextToImageChat;

import com.example.proj2.chatMessage.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;

public class Scene5Controller {


    private TextField textBox;
    private Button enterButton;
    private VBox chatHistoryMenu;

    private Label placeholderLabel;
    private Label chatHistoryLabel;
    private VBox chatBox;
//    private ChatMessageFactory messageFactory;
//    private ChatHistoryManager historyManager;
    private String selectedChat; // New variable to store the selected chat name
    private TextToTextChat textChat; // Instance of TextToTextChat
    private TextToImageChat imageChat; // Instance of ImageChat
    private TextToBooleanChat booleanChat; // Instance of BooleanChat



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
                createChatHistoryButton("text"),
                createChatHistoryButton("image"),
                createChatHistoryButton("boolean")
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
            if (selectedChat != null) {
                // Create chat message based on the selected chat type
                String messageType = selectedChat.toLowerCase();

                switch (messageType) {
                    case "text":
                        QueryResolutionResult<String> textResolutionResult = textChat.resolve(new QueryResolutionForm<>(message));
                        if (textResolutionResult != null) {
                            // Handle text resolution result
                            String response = textResolutionResult.getResultData();
                            textChat.sendMessage(message);
                            textChat.displayMessages(); // Call displayMessages() to update the chat box
                        }
                        break;
                    case "image":
                        QueryResolutionResult<Image> imageResolutionResult = imageChat.resolve(new QueryResolutionForm<>(message));
                        if (imageResolutionResult != null) {
                            // Handle image resolution result
                            Image resolvedImage = imageResolutionResult.getResultData();
                            imageChat.sendMessage(message);
                            imageChat.displayMessages();
                        }
                        break;
                    case "boolean":
                        QueryResolutionResult<Boolean> booleanResolutionResult = booleanChat.resolve(new QueryResolutionForm<>(message));
                        if (booleanResolutionResult != null) {
                            // Handle boolean resolution result
                            Boolean resolvedBoolean = booleanResolutionResult.getResultData();
                            booleanChat.sendMessage(message);
                            booleanChat.sendMessage(resolvedBoolean.toString());
                            booleanChat.displayMessages();
                        }
                        break;
                    default:
                        // Handle unknown chat types
                        System.out.println("Unsupported chat type: " + selectedChat);
                        break;
                }

                textBox.clear();
            } else {
                System.out.println("SelectedChat is null. Please set it before entering a message.");
            }
        }
    }
    private Button createChatHistoryButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("chat_history_button");
        button.setOnAction(e -> openChat(text)); // Add action to open the selected chat
        return button;
    }
    private void openChat(String chatType) {
        // Update the chat history label
        setChatHistoryLabel(chatType);

        // Set the selected chat
        selectedChat = chatType;

        // Clear the existing messages in the chat box
        chatBox.getChildren().clear();

        // Retrieve the chat messages for the selected chat based on its type
        List<String> chatMessages;
        if (chatType.equals("Text")) {
            chatMessages = textChat.getMessages();
        } else if (chatType.equals("Image")) {
            chatMessages = imageChat.getMessages();
        } else if (chatType.equals("Boolean")) {
            chatMessages = booleanChat.getMessages();
        } else {
            // Handle unknown chat names or other types of chats
            chatMessages = Collections.emptyList();
        }

        // Display the chat messages in the chat box
        if (chatMessages.isEmpty()) {
            chatBox.getChildren().add(placeholderLabel);
        } else {
            for (String message : chatMessages) {
                displayMessage(message);
            }
        }
    }
    public void displayMessage(String message) {
        Label messageLabel = new Label(message);
        chatBox.getChildren().add(messageLabel); // Add the message label to the chat box
        if (chatBox.getChildren().contains(placeholderLabel)) {
            chatBox.getChildren().remove(placeholderLabel);
        }
    }

    public void setChatHistoryLabel(String label) {
        chatHistoryLabel.setText(label);
    }

    public void setTextChat(TextToTextChat textChat) {
        this.textChat = textChat;
    }

    public void setTextToBooleanChat(TextToBooleanChat booleanChat) {
        this.booleanChat = booleanChat;
    }

    public void setTextToImageChat(TextToImageChat imageChat) {
        this.imageChat = imageChat;
    }


//    public void setHistoryManager(ChatHistoryManager historyManager) {
//        this.historyManager = historyManager;
//    }
//    public void setMessageFactory(ChatMessageFactory messageFactory) {
//        this.messageFactory = messageFactory;
//    }
//    private String getSelectedChat() {
//        return selectedChat;
//    }
//
//

}
