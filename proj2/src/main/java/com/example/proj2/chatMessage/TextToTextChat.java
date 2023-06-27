package com.example.proj2.chatMessage;

import com.example.proj2.GuiManager.SceneFunctions;
import com.example.proj2.Scene5Controller;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextToTextChat implements QueryResolutionStrategy<String, String> {
    private ChatHistoryManager chatHistoryManager;

    @Override
    public void setChatHistoryManager(ChatHistoryManager chatHistoryManager) {
        this.chatHistoryManager = chatHistoryManager;
    }

    @Override
    public QueryResolutionResult<String> resolve(QueryResolutionForm<String> queryForm) {
        String query = queryForm.getQueryData();
        chatHistoryManager.addChatMessage(query);

        // Perform your query resolution logic here and get the text result
        String textResult;

        // Example of hardcoded responses based on prompts
        if (query.equalsIgnoreCase("hello")) {
            textResult = "Hi there!";
        } else if (query.equalsIgnoreCase("how are you?")) {
            textResult = "I'm doing well, thank you!";
        } else if (query.equalsIgnoreCase("goodbye")) {
            textResult = "Goodbye! Have a great day!";
        } else {
            textResult = "I'm sorry, I didn't understand your query.";
        }

        return new QueryResolutionResult<>(textResult);
    }
}