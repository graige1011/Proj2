package com.example.proj2.chatMessage;

import java.util.List;

public interface QueryResolutionStrategy<T, R> {
    QueryResolutionResult<R> resolve(QueryResolutionForm<T> form);
    void setChatHistoryManager(ChatHistoryManager chatHistoryManager);

}
