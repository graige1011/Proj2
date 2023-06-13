package com.example.proj2.chatMessage;

public class TextMessage implements ChatMessage {
    private String content;

    public TextMessage(String content) {
        this.content = content;
    }

    @Override
    public void display() {
        System.out.println("Text Message: " + content);
    }

    @Override
    public String getContent() {
        return content;
    }
}