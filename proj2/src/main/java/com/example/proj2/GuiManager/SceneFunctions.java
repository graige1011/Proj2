package com.example.proj2.GuiManager;

import com.example.proj2.chatMessage.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SceneFunctions {
    private TextField chatField;
    private Button enterButton;
    private VBox chatHistoryMenu;

    private Label placeholderLabel;
    private Label chatHistoryLabel;
    private VBox chatBox;
    private String selectedChat; // New variable to store the selected chat name
    private TextToTextChat textChat; // Instance of TextToTextChat

    private TextToBooleanChat booleanChat; // Instance of BooleanChat

    private Map<String, QueryResolutionStrategy<?, ?>> chats = new HashMap<>();

    public SceneFunctions() {
        chatHistoryLabel = new Label();
        chatBox = new VBox();
        this.chatField = new TextField();
    }


    public void changeToLightMode( ) {}
    public void changeToDarkMode( ) {}
    public void changeLanguage( ) {}
    public void logout(){}

    public static void displayChatMessage(TextArea chatHistoryDisplay, String message) {
        // Toon het chatbericht in het chatgeschiedenisdisplay
        // Voeg het bericht toe aan het einde van het chatgeschiedenisdisplay
        chatHistoryDisplay.appendText(message + "\n");
    }

    public static void clearChatHistoryDisplay(TextArea chatHistoryDisplay) {
        // Maak het chatgeschiedenisdisplay leeg
        chatHistoryDisplay.clear();
    }

    public static void createChatMenuEntry(ListView<String> chatMenu, String chatName) {
        // Voeg een chatmenu-item toe aan de chatmenu-lijst
        chatMenu.getItems().add(chatName);
    }

    public static void updateChatHistoryDisplay(TextArea chatHistoryDisplay, List<String> chatMessages) {
        // Clear the chat history display
        chatHistoryDisplay.clear();

        // Set the updated chat messages in the chat history display
        for (String chatMessage : chatMessages) {
            chatHistoryDisplay.appendText(chatMessage + "\n");
        }
    }
}





