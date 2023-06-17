package com.example.proj2.chatMessage;

public class QueryResolutionResult<T> {
    private T resultData;

    public QueryResolutionResult(T resultData) {
        this.resultData = resultData;
    }

    public T getResultData() {
        return resultData;
    }
}
