package com.example.proj2.chatMessage;

public class TextMessage implements chatMessage {
    private String content;

    public TextMessage(String content) {
        this.content = content;
    }

    @Override
    public void display() {
        System.out.println("Text Message: " + content);
    }
}