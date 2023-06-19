package com.example.proj2.chatMessage;

import com.example.proj2.Scene5Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextToTextChat implements QueryResolutionStrategy<String, String> {
    private List<String> messages = new ArrayList<>();
    private Map<String, String> messageResponses = new HashMap<>();
    private Scene5Controller scene5Controller;

    private String chatName;

    public TextToTextChat() {
        this.messages = new ArrayList<>();
    }

    public void setScene5Controller(Scene5Controller scene5Controller) {
        this.scene5Controller = scene5Controller;
    }

    @Override
    public QueryResolutionResult<String> resolve(QueryResolutionForm<?> queryForm) {
        String queryData = (String) queryForm.getQueryData();
        String textResult = getResponse(queryData);

        return new QueryResolutionResult<>(textResult);
    }

    @Override
    public void sendMessage(String message) {
        String response = getResponse(message);
        messages.add(message);
        messageResponses.put(message, response);
    }

    @Override
    public void displayMessages() {
        for (String message : messages) {
            String response = messageResponses.get(message);
            scene5Controller.displayMessage(message);
            scene5Controller.displayMessage(response);
        }
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }

    public List<String> getResponses() {
        List<String> responses = new ArrayList<>();
        for (String message : messages) {
            String response = messageResponses.get(message);
            responses.add(response);
        }
        return responses;
    }

    private String getResponse(String input) {
        // Define specific responses based on the input text
        if (input.equalsIgnoreCase("hello")) {
            return "Hi there!";
        } else if (input.equalsIgnoreCase("how are you?")) {
            return "I'm doing well, thank you!";
        } else {
            return "I'm sorry, I don't understand.";
        }
    }

    @Override
    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
}