package com.example.proj2.GuiManager;

import com.example.proj2.chatMessage.ChatHistoryManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SceneFunctions {
    private VBox chatMenuContainer;
    private ListView<String> chatMenu;
    private Button createChatButton;
    private Map<String, ChatHistoryManager> chatHistoryMap;
    private Map<String, SceneCreation.ChatType> chatMap;

    private SceneCreation sceneCreation;

    public SceneFunctions() {
        chatMenuContainer = new VBox();
        chatMenu = new ListView<>();
        createChatButton = new Button("Create Chat");
        chatHistoryMap = new HashMap<>();
        chatMap = new HashMap<>();
    }


    public void changeToLightMode() {
    }

    public void changeToDarkMode() {
    }

    public void changeLanguage() {
    }

    public void logout() {
    }

}



