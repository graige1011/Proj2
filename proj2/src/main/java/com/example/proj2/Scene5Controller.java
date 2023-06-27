package com.example.proj2;
import com.example.proj2.chatMessage.TextToTextChat;
import com.example.proj2.chatMessage.TextToBooleanChat;

import com.example.proj2.chatMessage.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private TextToBooleanChat booleanChat; // Instance of BooleanChat

    private Map<String, QueryResolutionStrategy<?, ?>> chats = new HashMap<>();




    public VBox createScene5UI() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("root");
        root.setFillWidth(true);

        // Create "AIsistify" label
        Label titleLabel = new Label("AIsistify");
        titleLabel.getStyleClass().addAll("header", "title-label");

        // Create "Create Chat" button and dropdown menu
        MenuButton createChatButton = new MenuButton("Create Chat");
        createChatButton.getStyleClass().addAll("create_chat_button");
        createChatButton.setMinSize(80, 30);

//        MenuItem textMenuItem = new MenuItem("Text");
//        textMenuItem.setOnAction(event -> addChat("Text"));
//        MenuItem imageMenuItem = new MenuItem("Image");
//        imageMenuItem.setOnAction(event -> addChat("Image"));
//        MenuItem booleanMenuItem = new MenuItem("Boolean");
//        booleanMenuItem.setOnAction(event -> addChat("Boolean"));

//        createChatButton.getItems().addAll(textMenuItem, imageMenuItem, booleanMenuItem);

        HBox headerBox = new HBox();
        headerBox.setAlignment(Pos.TOP_CENTER);
        headerBox.getStyleClass().add("header");
        headerBox.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.getStyleClass().add("title-box");

        HBox createChatBox = new HBox(createChatButton);
        createChatBox.setAlignment(Pos.TOP_LEFT);
        createChatBox.getStyleClass().add("create-chat-box");

        HBox.setHgrow(titleBox, Priority.ALWAYS); // Allow the title box to expand horizontally

        headerBox.getChildren().addAll(titleBox, createChatBox);

        HBox chatContainer = new HBox();
        chatContainer.setAlignment(Pos.CENTER);
        chatContainer.setSpacing(10);
        chatContainer.setFillHeight(true);
        VBox.setVgrow(chatContainer, Priority.ALWAYS);
        HBox.setHgrow(chatContainer, Priority.ALWAYS);

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
                createChatHistoryButton("Text", "text"),
                createChatHistoryButton("Image", "image"),
                createChatHistoryButton("Boolean", "boolean")
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
//        enterButton.setOnAction(this::enter);

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

        String initialChat = "text";
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

//    private void showCreateChatMenu(ActionEvent event) {
//        // Create a menu for choosing the chat type
//        Menu menu = new Menu("Create Chat");
//        menu.getStyleClass().add("popup_menu");
//
//        // Add chat types to the menu
//        MenuItem textMenuItem = new MenuItem("Text");
//        textMenuItem.setOnAction(e -> addChat("Text"));
//        MenuItem imageMenuItem = new MenuItem("Image");
//        imageMenuItem.setOnAction(e -> addChat("Image"));
//        MenuItem booleanMenuItem = new MenuItem("Boolean");
//        booleanMenuItem.setOnAction(e -> addChat("Boolean"));
//
//        menu.getItems().addAll(textMenuItem, imageMenuItem, booleanMenuItem);
//
//        // Create a menu button for the "Create Chat" functionality
//        MenuButton createChatButton = new MenuButton();
//        createChatButton.getStyleClass().add("create_chat_button");
//        createChatButton.getItems().add(menu);
//
//        // Show the menu below the "Create Chat" button
//        menu.show();
//    }






//    public void enter(ActionEvent event) {
//        String message = textBox.getText().trim();
//        if (!message.isEmpty() && selectedChat != null) {
//            QueryResolutionStrategy<String, String> chat = (QueryResolutionStrategy<String, String>) chats.get(selectedChat);
//            if (chat != null) {
//                QueryResolutionForm<String> form = new QueryResolutionForm<>(message);
//                QueryResolutionResult<String> resolutionResult = chat.resolve(form);
//                if (resolutionResult != null) {
//                    chat.sendMessage(message);
//                    chat.displayMessages();
//                }
//            } else {
//                System.out.println("Selected chat is null. Please set it before entering a message.");
//            }
//            textBox.clear();
//        }
//    }
    private Button createChatHistoryButton(String text, String chatType) {
        Button button = new Button(text);
        button.getStyleClass().add("chat_history_button");
        button.setOnAction(e -> openChat(chatType)); // Pass the chat type to the openChat method
        return button;
    }

    private String getChatName(String chatType) {
        int chatCount = 1;
        String chatName = chatType;
        for (Node node : chatHistoryMenu.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                if (button.getText().startsWith(chatType)) {
                    chatCount++;
                }
            }
        }
        if (chatCount > 1) {
            chatName = chatType + " " + chatCount;
        }
        return chatName;
    }
    public void openChat(String chatType) {
        selectedChat = chatType;
        setChatHistoryLabel(chatType);

        chatBox.getChildren().clear();
        if (chats.containsKey(chatType)) {
            QueryResolutionStrategy<?, ?> chat = chats.get(chatType);
//            chat.displayMessages();
        } else {
            System.out.println("Unsupported chat type: " + chatType);
        }
    }
    private void displayChatMessages(List<String> messages, List<String> responses) {
        if (messages.size() != responses.size()) {
            // Handle the case where the number of messages and responses are not equal
            System.out.println("Mismatch between the number of messages and responses");
            return;
        }

        if (messages.isEmpty()) {
            chatBox.getChildren().add(placeholderLabel);
        } else {
            for (int i = 0; i < messages.size(); i++) {
                displayMessage(messages.get(i)); // Display the message
                displayMessage(responses.get(i)); // Display the response
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
