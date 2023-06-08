package com.example.proj2.chatMessage;

public class ImageMessage implements chatMessage {
    private String imageUrl;

    public ImageMessage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void display() {
        System.out.println("Image Message: " + imageUrl);
    }
}