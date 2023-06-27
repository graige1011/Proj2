package com.example.proj2.chatMessage;

public class TextToTextChatFactory implements AbstractChatFactory {
    @Override
    public QueryResolutionStrategy<String, String> createStrategy() {
        return new TextToTextChat();
    }
}
