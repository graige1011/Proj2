package com.example.proj2.GuiManager;

import com.example.proj2.chatMessage.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.*;

public class CreateChatPageStrategy implements SceneCreationStrategy{
    private Map<String, ChatType> chatMap = new HashMap<>();

    private SceneSwitcher sceneSwitcher;
    enum ChatType {
        BOOLEAN,
        TEXT
    }
    public CreateChatPageStrategy(SceneSwitcher sceneSwitcher ) {
        this.sceneSwitcher = sceneSwitcher;
    }

    @Override
    public Scene createScene() {
        BorderPane root = new BorderPane();

        // Create a VBox to hold the chat menu items
        VBox chatMenuContainer = new VBox();

        // Create the chat menu as a ListView
        ListView<String> chatMenu = new ListView<>();
        chatMenu.setPrefWidth(200);

        // Create a placeholder item in the chat menu
        chatMenu.getItems().add("Select a chat");

        // Create the create chat button
        Button createChatButton = new Button("Create Chat");
        Button backButton = new Button("Logout");
        backButton.setOnAction(event -> sceneSwitcher.switchToLoginPage(event));

        // Create a map to store the chat history managers for each chat
        Map<String, ChatHistoryManager> chatHistoryMap = new HashMap<>();

        createChatButton.setOnAction(event -> {

            // Prompt the user to enter a name for the new chat
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Create Chat");
            dialog.setHeaderText("Enter the name for the new chat:");
            Optional<String> result = dialog.showAndWait();

            // Create a new chat with the entered name and type
            result.ifPresent(chatName -> {
                // Prompt the user to select the chat type
                ChoiceDialog<ChatType> typeDialog = new ChoiceDialog<>(ChatType.BOOLEAN, ChatType.TEXT);
                typeDialog.setTitle("Select Chat Type");
                typeDialog.setHeaderText("Select the type for the new chat:");
                typeDialog.setContentText("Chat Type:");
                Optional<ChatType> typeResult = typeDialog.showAndWait();

                // Add the new chat to the chat menu with its associated type
                typeResult.ifPresent(chatType -> {
                    chatMenu.getItems().add(chatName);
                    chatMap.put(chatName, chatType);
                    chatHistoryMap.put(chatName, new ChatHistoryManager(chatName)); // Pass the chat name as the chat identifier
                });
            });
        });

        // Add the create chat button to the chat menu container
        chatMenuContainer.getChildren().addAll(chatMenu, createChatButton,backButton);

        // Set the chat menu container on the left side of the root layout
        root.setLeft(chatMenuContainer);

        // Create the chat history display box
        TextArea chatHistoryDisplay = new TextArea();
        chatHistoryDisplay.setEditable(false);
        chatHistoryDisplay.setWrapText(true);

        // Create the message input box
        TextField messageInput = new TextField();
        messageInput.setPromptText("Type your message here");

        // Create the send button
        Button sendButton = new Button("Send");
        sendButton.setDefaultButton(true);

        // Create the chat area containing the chat history display, message input, and send button
        VBox chatArea = new VBox(10);
        chatArea.setPadding(new Insets(10));
        chatArea.getChildren().addAll(chatHistoryDisplay, messageInput, sendButton);

        sendButton.setOnAction(event -> {
            System.out.println("Send button clicked");
            String message = messageInput.getText();
            // Process and send the message to the selected chat
            QueryResolutionForm<String> queryForm = new QueryResolutionForm<>(message);

            // Get the selected chat from the chat menu
            String selectedChat = chatMenu.getSelectionModel().getSelectedItem();

            // Get the chat type associated with the selected chat
            ChatType selectedChatType = chatMap.get(selectedChat);

            // Get the ChatHistoryManager for the selected chat
            ChatHistoryManager chatHistoryManager = chatHistoryMap.get(selectedChat);

            // Set the selected chat factory based on the chat type
            AbstractChatFactory<String, ?> selectedChatFactory;
            if (selectedChatType == ChatType.BOOLEAN) {
                selectedChatFactory = new TextToBooleanChatFactory();
            } else if (selectedChatType == ChatType.TEXT) {
                selectedChatFactory = new TextToTextChatFactory();
            } else {
                // Handle any additional chat types here
                selectedChatFactory = null; // Provide the appropriate factory for the additional chat types
            }

            if (selectedChatFactory != null) {
                QueryResolutionStrategy<String, ?> strategy = selectedChatFactory.createStrategy();
                strategy.setChatHistoryManager(chatHistoryManager);
                QueryResolutionResult<?> result = strategy.resolve(queryForm);

//                // Retrieve the last user message from the chat history manager
//                String lastUserMessage = chatHistoryManager.getLastUserMessage(selectedChat);

                // Append the user's message and last user message to the chat history display
                chatHistoryDisplay.appendText("User: " + message + "\n");

                // Append the chat response to the chat history display
                chatHistoryDisplay.appendText("Bot: " + result.getResultData().toString() + "\n");

                // Add the user's message and bot's response to the chat history for the selected chat
                //chatHistoryManager.addChatMessage(message);
                chatHistoryManager.addBotResponse(result.getResultData().toString());
            }
            // Clear the message input box
            messageInput.clear();
        });

        chatMenu.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the chat history display when a new chat is selected
            chatHistoryDisplay.clear();

            if (newValue != null && !newValue.equals("Select a chat")) {
                ChatHistoryManager chatHistoryManager = chatHistoryMap.get(newValue);
                List<String> chatMessages = chatHistoryManager.getChatHistory();

                // Append the chat history to the chat history display
                for (String chatMessage : chatMessages) {
                    chatHistoryDisplay.appendText(chatMessage + "\n");
                }
            }

        });

        // Set the chat area as the center of the root layout
        root.setCenter(chatArea);

        return new Scene(root, 800, 600); // Set the desired size of the scene
    }

}
