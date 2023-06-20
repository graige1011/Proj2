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

    private Map<String, QueryResolutionStrategy<?, ?>> chats = new HashMap<>();


    public void changeToLightMode( ) {}
    public void changeToDarkMode( ) {}
    public void changeLanguage( ) {}
    public void logout(){}

    public void addChat(String chatType) {
        String chatName = getChatName(chatType);
        switch (chatType.toLowerCase()) {
            case "text":
                TextToTextChat textChat = new TextToTextChat();
                textChat.setScene5Controller(this);
                chats.put(chatName, textChat);
                break;
            case "image":
                TextToImageChat imageChat = new TextToImageChat();
                imageChat.setScene5Controller(this);
                chats.put(chatName, imageChat);
                break;
            case "boolean":
                TextToBooleanChat booleanChat = new TextToBooleanChat();
                booleanChat.setScene5Controller(this);
                chats.put(chatName, booleanChat);
                break;
            default:
                System.out.println("Unsupported chat type: " + chatType);
                break;
        }
        chatHistoryMenu.getChildren().add(createChatHistoryButton(chatName, chatType.toLowerCase()));
    }




    public void enter(ActionEvent event) {
        String message = textBox.getText().trim();
        if (!message.isEmpty() && selectedChat != null) {
            QueryResolutionStrategy<String, String> chat = (QueryResolutionStrategy<String, String>) chats.get(selectedChat);
            if (chat != null) {
                QueryResolutionForm<String> form = new QueryResolutionForm<>(message);
                QueryResolutionResult<String> resolutionResult = chat.resolve(form);
                if (resolutionResult != null) {
                    chat.sendMessage(message);
                    chat.displayMessages();
                }
            } else {
                System.out.println("Selected chat is null. Please set it before entering a message.");
            }
            textBox.clear();
        }
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

    public Button createChatHistoryButton(String text, String chatType) {
        Button button = new Button(text);
        button.getStyleClass().add("chat_history_button");
        button.setOnAction(e -> openChat(chatType)); // Pass the chat type to the openChat method
        return button;
    }
    public void openChat(String chatType) {
        selectedChat = chatType;
        setChatHistoryLabel(chatType);

        chatBox.getChildren().clear();
        if (chats.containsKey(chatType)) {
            QueryResolutionStrategy<?, ?> chat = chats.get(chatType);
            chat.displayMessages();
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

    public void setTextToImageChat(TextToImageChat imageChat) {
        this.imageChat = imageChat;
    }

}
