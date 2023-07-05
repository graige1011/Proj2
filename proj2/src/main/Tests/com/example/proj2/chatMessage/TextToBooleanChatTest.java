package com.example.proj2.chatMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextToBooleanChatTest {
    private TextToBooleanChat chat;
    private ChatHistoryManager chatHistoryManager;

    @BeforeEach
    public void setup() {
        chat = new TextToBooleanChat();
        chatHistoryManager = new ChatHistoryManager("chat1");
        chat.setChatHistoryManager(chatHistoryManager);
    }

    @Test
    public void testBooleanResolve() {
        // Testcase 1: Query is "is it sunny today?"
        boolean expectedBooleanResult = true;
        QueryResolutionForm<String> queryForm = new QueryResolutionForm<>("is it sunny today?");
        QueryResolutionResult<Boolean> expectedResult = new QueryResolutionResult<>(expectedBooleanResult);

        // Act
        QueryResolutionResult<Boolean> actualResult = chat.resolve(queryForm);

        // Assert
        assertEquals(expectedResult.getResultData(), actualResult.getResultData());

        // Testcase 2: Query is "do you like pizza?"
        expectedBooleanResult = true;
        queryForm = new QueryResolutionForm<>("do you like pizza?");
        expectedResult = new QueryResolutionResult<>(expectedBooleanResult);

        // Act
        actualResult = chat.resolve(queryForm);

        // Assert
        assertEquals(expectedResult.getResultData(), actualResult.getResultData());

        // Testcase 3: Query is "is the sky blue?"
        expectedBooleanResult = true;
        queryForm = new QueryResolutionForm<>("is the sky blue?");
        expectedResult = new QueryResolutionResult<>(expectedBooleanResult);

        // Act
        actualResult = chat.resolve(queryForm);

        // Assert
        assertEquals(expectedResult.getResultData(), actualResult.getResultData());

        // Testcase 4: Query is unknown
        expectedBooleanResult = false;
        queryForm = new QueryResolutionForm<>("unknown");
        expectedResult = new QueryResolutionResult<>(expectedBooleanResult);

        // Act
        actualResult = chat.resolve(queryForm);

        // Assert
        assertEquals(expectedResult.getResultData(), actualResult.getResultData());
    }
}



