package com.example.proj2.chatMessage;

import java.util.ArrayList;
import java.util.List;

public class  TextToBooleanChat implements QueryResolutionStrategy<String, Boolean> {
    private List<String> messages = new ArrayList<>();

    @Override
    public QueryResolutionResult<Boolean> resolve(QueryResolutionForm<String> queryForm) {
        String queryData = queryForm.getQueryData();
        Boolean booleanResult = createBooleanFromText(queryData);

        return new QueryResolutionResult<>(booleanResult);
    }

    @Override
    public void sendMessage(String message) {
        messages.add(message);
    }

    @Override
    public void displayMessages() {
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            String response = getResponse(message);

            System.out.println("Message: " + message);
            System.out.println("Response: " + response);
            System.out.println();
        }
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }

    private Boolean createBooleanFromText(String text) {
        // Logic to create a boolean from text
        // Hard-coded response for demonstration purposes
        Boolean booleanResult = text.toLowerCase().equals("yes");
        return booleanResult;
    }
    public List<String> getResponses() {
        // Logic to retrieve the responses for the chat
        // Return the list of responses
        return null;
    }

    private String getResponse(String message) {
        // Logic to generate a response based on the message
        // Hard-coded response for demonstration purposes
        String response = "Sure!";
        return response;
    }
}