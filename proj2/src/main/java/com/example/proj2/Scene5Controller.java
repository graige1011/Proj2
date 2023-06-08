import com.example.proj2.SettingsController;
import com.example.proj2.chatMessage.ChatHistoryManager;
import com.example.proj2.chatMessage.ChatMessageFactory;
import com.example.proj2.chatMessage.chatMessage;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Scene5Controller {
    private TextField textBox;
    private Button enterButton;
    private Label chatHistoryLabel;
    private VBox chatHistoryContainer;
    private ChatMessageFactory messageFactory;
    private ChatHistoryManager historyManager;

    public VBox createScene5UI() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        chatHistoryContainer = new VBox();
        chatHistoryContainer.setAlignment(Pos.TOP_RIGHT);

        chatHistoryLabel = new Label("Chat History");
        chatHistoryLabel.setStyle("-fx-font-weight: bold;");

        HBox settingsBox = new HBox();
        settingsBox.setAlignment(Pos.BOTTOM_LEFT);

        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction(this::openSettings);

        settingsBox.getChildren().add(settingsButton);

        HBox inputBox = new HBox();
        inputBox.setAlignment(Pos.BOTTOM_CENTER);

        textBox = new TextField();
        textBox.setPromptText("Enter text");
        textBox.setStyle("-fx-background-color: white;");

        enterButton = new Button("Enter");
        enterButton.getStyleClass().add("hover-button");
        enterButton.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        enterButton.setOnAction(this::enter);

        inputBox.getChildren().addAll(textBox, enterButton);

        root.getChildren().addAll(chatHistoryContainer, settingsBox, inputBox);

        return root;
    }

    public void openSettings(ActionEvent event) {
        SettingsController settingsController = new SettingsController();
        AnchorPane settingsRoot = settingsController.createSettingsUI();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(settingsRoot, 800, 600));
        stage.setTitle("AIsistify");
        stage.show();
    }

    public void enter(ActionEvent event) {
        String message = textBox.getText().trim();
        if (!message.isEmpty()) {
            // Create chat message using the factory
            chatMessage chatMessage1 = messageFactory.createMessage(ChatMessageFactory.MessageType.TEXT, message);

            // Add chat message to the history manager
            historyManager.addMessage(chatMessage1);

            displayMessage(chatMessage1);
            textBox.clear();
        }
    }

    private void displayMessage(ChatMessage message) {
        Label messageLabel = new Label(message.getContent());
        chatHistoryContainer.getChildren().add(messageLabel);
    }

    public void setMessageFactory(ChatMessageFactory messageFactory) {
        this.messageFactory = messageFactory;
    }

    public void setHistoryManager(ChatHistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    public void setChatHistoryLabel(String label) {
        chatHistoryLabel.setText(label);
    }
}
