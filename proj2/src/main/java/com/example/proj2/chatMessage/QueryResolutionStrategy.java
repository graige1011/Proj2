package com.example.proj2.chatMessage;

import java.util.List;

public interface QueryResolutionStrategy<T, R> {
    QueryResolutionResult<R> resolve(QueryResolutionForm<T> queryForm);

    void sendMessage(String message);

    void displayMessages();

    public List<String> getMessages();

}
