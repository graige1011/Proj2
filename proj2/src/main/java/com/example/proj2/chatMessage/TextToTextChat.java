package com.example.proj2.chatMessage;

import com.example.proj2.Scene5Controller;

import java.util.ArrayList;
import java.util.List;

public class TextToTextChat implements QueryResolutionStrategy<String, String> {
    private List<String> messages = new ArrayList<>();
    private Scene5Controller scene5Controller; // Reference to the Scene5Controller instance

    public void setScene5Controller(Scene5Controller scene5Controller) {
        this.scene5Controller = scene5Controller;
    }
    @Override
    public QueryResolutionResult<String> resolve(QueryResolutionForm<String> queryForm) {
        String queryData = queryForm.getQueryData();
        String textResult = getResponse(queryData);

        return new QueryResolutionResult<>(textResult);
    }

    @Override
    public void sendMessage(String message) {
        messages.add(message);
    }

    @Override
    public void displayMessages() {
        for (String message : messages) {
            String response = getResponse(message);

            // Update UI elements in Scene5Controller to show the messages and responses
            scene5Controller.displayMessage(message);
            scene5Controller.displayMessage(response);
        }
    }

    @Override
    public List<String> getMessages() {
        return messages;
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
}