package com.example.proj2.chatMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextToTextChatTest {
    private TextToTextChat chat;
    private ChatHistoryManager chatHistoryManager;

    @BeforeEach
    public void setup() {
        chat = new TextToTextChat();
        chatHistoryManager = new ChatHistoryManager("chat1");
        chat.setChatHistoryManager(chatHistoryManager);
    }

    @Test
    public void testTextResolve() {
        // Testcase 1: Query is "hello"
        String expectedTextResult = "Hi there!";
        QueryResolutionForm<String> queryForm = new QueryResolutionForm<>("hello");
        QueryResolutionResult<String> expectedResult = new QueryResolutionResult<>(expectedTextResult);

        // Act
        QueryResolutionResult<String> actualResult = chat.resolve(queryForm);

        // Assert
        assertEquals(expectedResult.getResultData(), actualResult.getResultData());

        // Testcase 2: Query is "how are you?"
        expectedTextResult = "I'm doing well, thank you!";
        queryForm = new QueryResolutionForm<>("how are you?");
        expectedResult = new QueryResolutionResult<>(expectedTextResult);

        // Act
        actualResult = chat.resolve(queryForm);

        // Assert
        assertEquals(expectedResult.getResultData(), actualResult.getResultData());

        // Testcase 3: Query is "goodbye"
        expectedTextResult = "Goodbye! Have a great day!";
        queryForm = new QueryResolutionForm<>("goodbye");
        expectedResult = new QueryResolutionResult<>(expectedTextResult);

        // Act
        actualResult = chat.resolve(queryForm);

        // Assert
        assertEquals(expectedResult.getResultData(), actualResult.getResultData());

        // Testcase 4: Query is unknown
        expectedTextResult = "I'm sorry, I didn't understand your query.";
        queryForm = new QueryResolutionForm<>("unknown");
        expectedResult = new QueryResolutionResult<>(expectedTextResult);

        // Act
        actualResult = chat.resolve(queryForm);

        // Assert
        assertEquals(expectedResult.getResultData(), actualResult.getResultData());
    }
}