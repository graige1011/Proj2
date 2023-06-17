package com.example.proj2.chatMessage;

class TextToBooleanChatFactory implements AbstractChatFactory {
    @Override
    public QueryResolutionStrategy<String, Boolean> createChat() {
        return new TextToBooleanChat();
    }
}
