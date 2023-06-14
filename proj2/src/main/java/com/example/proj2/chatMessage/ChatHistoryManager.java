package com.example.proj2.chatMessage;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatHistoryManager implements ChatHistory {
    private VBox chatHistoryMenu;
    private Map<String, List<ChatMessage>> chatMessagesMap;
    private final int maxMessages;
    private List<ChatMessage> messages;
    private ChatMessageFactory messageFactory;

    private ChatHistoryManager historyManager;



    public ChatHistoryManager(int maxMessages) {
        this.maxMessages = maxMessages;
        messages = new ArrayList<>();
        chatHistoryMenu = new VBox();
        messageFactory = new ChatMessageFactory();
        chatMessagesMap = new HashMap<>();

    }


    public void addMessage(ChatMessage message, String chatName) {
        List<ChatMessage> chatMessages = chatMessagesMap.getOrDefault(chatName, new ArrayList<>());
        chatMessages.add(message);

        // Check if the number of messages exceeds the maximum allowed

        if (chatMessages.size() > maxMessages) {
            // Remove the oldest message
            chatMessages.remove(0);
        }
        chatMessagesMap.put(chatName, chatMessages);

    }



    private void updateChatHistoryUI(String selectedChat) {
        chatHistoryMenu.getChildren().clear();
        List<ChatMessage> chatMessages = chatMessagesMap.getOrDefault(selectedChat, new ArrayList<>());
        for (ChatMessage message : chatMessages) {
            Label label = new Label(message.getContent()); // Use the message content directly
            chatHistoryMenu.getChildren().add(label);
        }
    }

    public VBox getChatHistoryUI() {
        return chatHistoryMenu;
    }

    public List<ChatMessage> getChatMessages(String chatName) {
        return chatMessagesMap.getOrDefault(chatName, new ArrayList<>());
    }
    public Map<String, List<ChatMessage>> getChatMessagesMap() {
        return chatMessagesMap;
    }

}
