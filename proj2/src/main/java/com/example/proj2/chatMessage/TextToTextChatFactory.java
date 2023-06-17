package com.example.proj2.chatMessage;

class TextToTextChatFactory implements AbstractChatFactory {
    @Override
    public QueryResolutionStrategy<String, String> createChat() {
        return new TextToTextChat();
    }
}
