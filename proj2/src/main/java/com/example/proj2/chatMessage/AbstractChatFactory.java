package com.example.proj2.chatMessage;

public interface AbstractChatFactory {
    QueryResolutionStrategy<?, ?> createChat();
}
