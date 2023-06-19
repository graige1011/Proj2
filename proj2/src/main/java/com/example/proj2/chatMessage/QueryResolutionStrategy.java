package com.example.proj2.chatMessage;

import java.util.List;

public interface QueryResolutionStrategy<T, R> {
    QueryResolutionResult<R> resolve(QueryResolutionForm<?> form);

    void sendMessage(String message);

    void displayMessages();

    List<String> getMessages();

    String getChatName();

}
