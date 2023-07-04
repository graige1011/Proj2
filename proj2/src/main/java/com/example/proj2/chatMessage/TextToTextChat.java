package com.example.proj2.chatMessage;

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
        }
        else if (query.equalsIgnoreCase("Fawaka mi mang whats going on")) {
                textResult = "Fawaka mi pangpang u ma pangpang mang !";

        }
        else if (query.equalsIgnoreCase("goodbye")) {
            textResult = "Goodbye! Have a great day!";
        } else {
            textResult = "I'm sorry, I didn't understand your query.";
        }


        return new QueryResolutionResult<>(textResult);
    }
}