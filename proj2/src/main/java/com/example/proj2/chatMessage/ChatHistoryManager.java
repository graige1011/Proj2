package com.example.proj2.chatMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatHistoryManager {
    private List<String> chatHistory;
    private Map<String, String> lastUserMessages;
    private String chatIdentifier;

    public ChatHistoryManager(String chatIdentifier) {
        this.chatIdentifier = chatIdentifier;
        chatHistory = new ArrayList<>();
        lastUserMessages = new HashMap<>();
    }


    public void addChatMessage(String message) {
        chatHistory.add("User: " + message);
        lastUserMessages.put(getChatIdentifier(), message);
    }
    public void addBotResponse(String response) {
        chatHistory.add("Bot: " + response);
    }

    public List<String> getChatHistory() {
        return chatHistory;
    }

    public String getLastUserMessage(String chatIdentifier) {
        List<String> chatMessages = chatHistory;
        if (chatMessages != null && !chatMessages.isEmpty()) {
            return chatMessages.get(chatMessages.size() - 1);
        }
        return null;
    }
    private String getChatIdentifier() {
        return chatIdentifier;
    }
}
