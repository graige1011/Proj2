package com.example.proj2.chatMessage;

import com.example.proj2.Scene5Controller;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class TextToImageChat implements QueryResolutionStrategy<String, Image> {
    private List<String> messages = new ArrayList<>();
    private String chatName;
    private Scene5Controller scene5Controller;




    public TextToImageChat() {
        this.messages = new ArrayList<>();

    }
    public void setScene5Controller(Scene5Controller scene5Controller) {
        this.scene5Controller = scene5Controller;
    }


    @Override
    public QueryResolutionResult<Image> resolve(QueryResolutionForm<?> queryForm) {
        String queryData = (String) queryForm.getQueryData();
        Image imageResult = createImageFromText(queryData);

        return new QueryResolutionResult<>(imageResult);
    }

    @Override
    public void sendMessage(String message) {
        messages.add(message);
    }

    @Override
    public void displayMessages() {
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            Image image = createImageFromText(message);

            System.out.println("Message: " + message);
            System.out.println("Image: " + image);
            System.out.println();
        }
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }

    public List<String> getResponses() {
        // Logic to retrieve the responses for the chat
        // Return the list of responses
        return null;
    }

    private Image createImageFromText(String text) {
        // Logic to create an image from text
        // Hard-coded response for demonstration purposes
        Image image = new Image("path/to/image.png");
        return image;

    }
    @Override
    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
}
