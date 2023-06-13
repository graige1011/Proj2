package com.example.proj2.chatMessage;

public class ChatMessageFactory {
    private ChatMessageFactory messageFactory;
    public enum MessageType {
        TEXT,
        IMAGE,
    }

    public ChatMessage createMessage(MessageType messageType, String messageContent) {
        switch (messageType) {
            case TEXT:
                return new TextMessage(messageContent);
            case IMAGE:
                return new ImageMessage(messageContent);
            default:
                throw new IllegalArgumentException("Invalid message type: " + messageType);
        }
    }

}

// how to call the chat message in my other classes..
//ChatMessageFactory messageFactory = new ChatMessageFactory();
//
//    // Create a text message
//    ChatMessage textMessage = messageFactory.createMessage(MessageType.TEXT, "Hello, how are you?");
//textMessage.display();
//
//// Create an image message
//        ChatMessage imageMessage = messageFactory.createMessage(MessageType.IMAGE, "https://example.com/image.jpg");
//        imageMessage.display();