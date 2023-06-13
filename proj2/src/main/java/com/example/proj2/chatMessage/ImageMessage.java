package com.example.proj2.chatMessage;

public class ImageMessage implements ChatMessage {
    private String imageUrl;

    public ImageMessage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void display() {
        System.out.println("Image Message: " + imageUrl);
    }

    @Override
    public String getContent() {
        return imageUrl;
    }
}