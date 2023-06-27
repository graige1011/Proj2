package com.example.proj2.chatMessage;

public class TextToBooleanChatFactory implements AbstractChatFactory<String, Boolean> {
    @Override
    public QueryResolutionStrategy<String, Boolean> createStrategy() {
        return new TextToBooleanChat();
    }

}
