package com.example.proj2.chatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatHistoryManager {
    private List<String> chatHistory;

    public ChatHistoryManager() {
        chatHistory = new ArrayList<>();
    }

    public void addChatMessage(String message) {
        chatHistory.add(message);
    }

    public List<String> getChatHistory() {
        return chatHistory;
    }
    public void saveChatHistory() {
        // Implement the logic to save the chat history to a file or any other storage mechanism
        // You can use the chat name or any unique identifier to associate the chat history with the chat
    }
}
