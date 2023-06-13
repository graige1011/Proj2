package com.example.proj2.chatMessage;

import javafx.scene.layout.VBox;

public interface ChatHistory {
    void addMessage(ChatMessage message);
    VBox getChatHistoryUI();
}