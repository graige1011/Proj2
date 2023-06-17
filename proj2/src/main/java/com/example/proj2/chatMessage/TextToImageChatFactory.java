package com.example.proj2.chatMessage;

import javafx.scene.image.Image;

public class TextToImageChatFactory implements AbstractChatFactory {
    @Override
    public QueryResolutionStrategy<String, Image> createChat() {
        return new TextToImageChat();
    }

}
