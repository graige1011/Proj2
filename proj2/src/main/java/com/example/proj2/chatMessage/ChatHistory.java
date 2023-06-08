package com.example.proj2.chatMessage;

import javafx.scene.layout.VBox;

public interface ChatHistory {
    void addMessage(ChatMessageFactory.MessageType messageType, String messageContent);
    VBox getChatHistoryUI();
}