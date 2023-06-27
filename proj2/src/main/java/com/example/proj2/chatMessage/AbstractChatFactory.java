package com.example.proj2.chatMessage;

public interface AbstractChatFactory<T, R> {
    QueryResolutionStrategy<T, R> createStrategy();


}
