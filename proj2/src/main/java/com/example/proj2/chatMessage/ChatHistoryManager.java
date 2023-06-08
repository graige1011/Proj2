package com.example.proj2.chatMessage;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ChatHistoryManager implements ChatHistory {
    private VBox chatHistoryContainer;
    private final int maxMessages;
    private List<chatMessage> messages;
    private ChatMessageFactory messageFactory;

    public ChatHistoryManager(int maxMessages) {
        this.maxMessages = maxMessages;
        messages = new ArrayList<>();
        chatHistoryContainer = new VBox();
        messageFactory = new ChatMessageFactory();

    }


    public void addMessage(ChatMessageFactory.MessageType messageType, String messageContent) {
        chatMessage message = messageFactory.createMessage(messageType, messageContent);
        messages.add(message);
        if (messages.size() > maxMessages) {
            messages.remove(0);
        }
        updateChatHistoryUI();
    }

    private void updateChatHistoryUI() {
        chatHistoryContainer.getChildren().clear();
        for (String message : messages) {
            Label label = new Label(message); // Use the message content directly
            chatHistoryContainer.getChildren().add(label);
        }
    }

    public VBox getChatHistoryUI() {
        return chatHistoryContainer;
    }
}