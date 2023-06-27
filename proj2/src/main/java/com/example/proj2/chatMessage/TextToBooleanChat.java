package com.example.proj2.chatMessage;

import com.example.proj2.Scene5Controller;

import java.util.ArrayList;
import java.util.List;

public class TextToBooleanChat implements QueryResolutionStrategy<String, Boolean> {
    private ChatHistoryManager chatHistoryManager;

    @Override
    public void setChatHistoryManager(ChatHistoryManager chatHistoryManager) {
        this.chatHistoryManager = chatHistoryManager;
    }

    @Override
    public QueryResolutionResult<Boolean> resolve(QueryResolutionForm<String> queryForm) {
        String query = queryForm.getQueryData();
        chatHistoryManager.addChatMessage(query);

        // Perform your query resolution logic here and get the boolean result
        boolean booleanResult;

        // Example of hardcoded responses based on prompts
        if (query.equalsIgnoreCase("is it sunny today?")) {
            booleanResult = true;
        } else if (query.equalsIgnoreCase("do you like pizza?")) {
            booleanResult = true;
        } else if (query.equalsIgnoreCase("is the sky blue?")) {
            booleanResult = true;
        } else {
            booleanResult = false;
        }

        return new QueryResolutionResult<>(booleanResult);
    }
}


