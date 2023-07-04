package com.example.proj2.GuiManager;
import com.example.proj2.chatMessage.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.*;

public class SceneCreation {

    private SceneFunctions sceneFunctions;
    private SceneSwitcher sceneSwitcher;
    //deze zijn nodig voor createScene5
    private TextField chatField;
    private Button enterButton;
    private VBox chatHistoryMenu;
    private Label placeholderLabel;
    private Label chatHistoryLabel;
    private VBox chatBox;
    private AbstractChatFactory<String,String> TextchatFactory;
    private AbstractChatFactory<String,Boolean> BooleanchatFactory;
    private Label usernameLabel;
    private Label passwordLabel;
    private Button loginButton;
    private Button createAccountButton;
    private Button forgotPasswordButton;
    private Button forgotUsernameButton;
    private Map<String, ChatType> chatMap = new HashMap<>();

    private Map<String, QueryResolutionStrategy<?, ?>> chats = new HashMap<>();

    Map<String, ChatHistoryManager> chatHistoryMap = new HashMap<>();

    private ArrayList<Userr> userList;

    List<String> chatMessages = new ArrayList<>();

    public SceneCreation() {
        sceneFunctions = new SceneFunctions();
        sceneSwitcher = new SceneSwitcher(this);
        userList =new ArrayList<Userr>();
    }

    enum ChatType {
        BOOLEAN,
        TEXT
    }

    public Scene createLoginPage() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setStyle("-fx-background-color: white;");
        root.setPadding(new Insets(40));

        ImageView imageView = new ImageView(new Image("https://media.licdn.com/dms/image/C4D0BAQHX1qomlcFEUA/company-logo_200_200/0/1548250566657?e=2147483647&amp;v=beta&amp;t=uXtCqDfNta4julyp-JYgX3X2n2pDgF8tesPnDZuGtk8"));
        root.getChildren().add(imageView);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20); // Adjusted vgap value
        gridPane.getColumnConstraints().add(new ColumnConstraints());

        gridPane.getRowConstraints().add(new RowConstraints());
        gridPane.getRowConstraints().add(new RowConstraints());

        Label titleLabel = new Label("AIsistify");
        titleLabel.setStyle("-fx-font-size: 36px; -fx-text-fill: darkblue; -fx-font-weight: bold;");
        gridPane.add(titleLabel, 0, 0);

        usernameLabel = new Label("Gebruikersnaam:");
        usernameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: lightblue;");
        gridPane.add(usernameLabel, 0, 1);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Voer gebruikersnaam in");
        usernameField.setStyle("-fx-background-color: white;");
        usernameField.setPrefWidth(200);
        usernameField.setPrefHeight(30);
        gridPane.add(usernameField, 1, 1);

        passwordLabel = new Label("Wachtwoord:");
        passwordLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: lightblue;");
        gridPane.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Voer wachtwoord in");
        passwordField.setStyle("-fx-background-color: white;");
        passwordField.setPrefWidth(200);
        passwordField.setPrefHeight(30);
        gridPane.add(passwordField, 1, 2);



        ChoiceBox<String> languageChoiceBox = new ChoiceBox<>();
        languageChoiceBox.getItems().addAll("English", "Dutch", "Spanish");
        languageChoiceBox.setValue("English");
        languageChoiceBox.setStyle("-fx-background-color: white;");
        gridPane.add(languageChoiceBox, 1, 10);



        loginButton = new Button("Log in");
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Call a method or class to handle the login logic
            boolean isAuthenticated = false;
            for (Userr user : userList) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    isAuthenticated = true;
                    break;
                }
            }

            if (isAuthenticated) {
                sceneSwitcher.switchTocreateChatPage(event);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password. Please try again.");
                alert.showAndWait();
            }
        });

        loginButton.getStyleClass().add("hover-button");
        loginButton.setStyle("-fx-min-width: 150px;");
        gridPane.add(loginButton, 0, 3, 2, 1);

        createAccountButton = new Button("Nieuw Account Aanmaken");
        createAccountButton.setOnAction(event -> {
            // sceneFunctions.handleAccountCreation();
            sceneSwitcher.switchToNewAccountPage(event);
        });
        createAccountButton.getStyleClass().add("hover-button");
        createAccountButton.setStyle("-fx-min-width: 150px;");
        gridPane.add(createAccountButton, 0, 4, 2, 1);

        forgotPasswordButton = new Button("Wachtwoord Vergeten");
        forgotPasswordButton.setOnAction(event -> {
            // sceneFunctions.handlePasswordRecovery();
            sceneSwitcher.switchToResetPasswordPage(event);
        });
        forgotPasswordButton.getStyleClass().add("hover-button");
        forgotPasswordButton.setStyle("-fx-min-width: 150px;");
        gridPane.add(forgotPasswordButton, 0, 5, 2, 1);

        forgotUsernameButton = new Button("Gebruikersnaam Vergeten");
        forgotUsernameButton.setOnAction(event -> {
            sceneSwitcher.switchToResetUsernamePage(event);
        });
        forgotUsernameButton.getStyleClass().add("hover-button");
        forgotUsernameButton.setStyle("-fx-min-width: 150px;");
        gridPane.add(forgotUsernameButton, 0, 6, 2, 1);

        root.getChildren().add(gridPane);


        languageChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            applyLanguage(newValue);
        });


        // Allow the VBox and GridPane to grow and fill the available space
        VBox.setVgrow(gridPane, Priority.ALWAYS);

        return new Scene(root, 800, 600); // Set the desired size of the scene
    }
    private void applyLanguage(String selectedLanguage) {
        if (selectedLanguage.equals("English")) {
            usernameLabel.setText("Username:");
            passwordLabel.setText("Password:");
            loginButton.setText("Log in");
            createAccountButton.setText("Create New Account");
            forgotPasswordButton.setText("Forgot Password");
            forgotUsernameButton.setText("Forgot Username");
        } else if (selectedLanguage.equals("Dutch")) {
            usernameLabel.setText("Gebruikersnaam:");
            passwordLabel.setText("Wachtwoord:");
            loginButton.setText("Log in");
            createAccountButton.setText("Nieuw Account Aanmaken");
            forgotPasswordButton.setText("Wachtwoord Vergeten");
            forgotUsernameButton.setText("Gebruikersnaam Vergeten");
        } else if (selectedLanguage.equals("Spanish")) {
            usernameLabel.setText("Nombre de usuario:");
            passwordLabel.setText("Contraseña:");
            loginButton.setText("Iniciar sesión");
            createAccountButton.setText("Crear nueva cuenta");
            forgotPasswordButton.setText("Olvidé mi contraseña");
            forgotUsernameButton.setText("Olvidé mi nombre de usuario");
        }
    }


    public Scene createNewAccountPage() {
        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white;");

        Label welcomeText = new Label("Welkom bij AIsistify");
        welcomeText.setStyle("-fx-font-size: 24px; -fx-text-fill: darkblue;");

        TextField textField = new TextField();
        textField.setPromptText("Voer je naam in");
        textField.setStyle("-fx-background-color: white;");

        TextField textField3 = new TextField();
        textField3.setPromptText("Voer je email in");
        textField3.setStyle("-fx-background-color: white;");

        TextField textField4 = new TextField();
        textField4.setPromptText("Voer je gebruikersnaam in");
        textField4.setStyle("-fx-background-color: white;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Voer wachtwoord in");
        passwordField.setStyle("-fx-background-color: white;");

        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> {
            // sceneFunctions.handleRegistration(textField.getText(), textField3.getText(), textField4.getText(), passwordField.getText());
            String name = textField4.getText();
            String password = passwordField.getText();
            Userr newUser = new Userr(name,password);
            userList.add(newUser);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("Registration completed successfully!");
            alert.showAndWait();

            // Print current user list in the terminal
            System.out.println("Current User List:");
            for (Userr user : userList) {
                System.out.println("Username: " + user.getUsername() + ", Password: " + user.getPassword());
            }

            // Reset the registration form
            textField.clear();
            textField3.clear();
            textField4.clear();
            passwordField.clear();
            sceneSwitcher.switchToLoginPage(event);

             // dit moet iets anders zijn
        });
        registerButton.getStyleClass().add("hover-button");

        root.getChildren().addAll(welcomeText, textField, textField3, textField4, passwordField, registerButton);

        return new Scene(root, 600, 800); // Set the desired size of the scene
    }
    public Scene createResetPasswordPage() {
        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white;");

        Label welcomeText = new Label("Reset je wachtwoord");
        welcomeText.setStyle("-fx-font-size: 24px; -fx-text-fill: darkblue;");

        TextField textField = new TextField();
        textField.setPromptText("Voer je gebruikersnaam in");
        textField.setStyle("-fx-background-color: white;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Voer je nieuwe wachtwoord in");
        passwordField.setStyle("-fx-background-color: white;");

        PasswordField passwordField2 = new PasswordField();
        passwordField2.setPromptText("Bevestig je nieuwe wachtwoord");
        passwordField2.setStyle("-fx-background-color: white;");

        Button resetPasswordButton = new Button("Wachtwoord herstellen");
        resetPasswordButton.setOnAction(event -> {
            // sceneFunctions.resetPassword(textField.getText(), passwordField.getText(), passwordField2.getText());
            String username = textField.getText();
            String newPassword = passwordField.getText();
            String confirmPassword = passwordField2.getText();

            if (newPassword.equals(confirmPassword)) {
                // Perform the password reset logic here
                boolean isPasswordResetSuccessful = resetPassword(username, newPassword);

                if (isPasswordResetSuccessful) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Password Reset Successful");
                    alert.setHeaderText(null);
                    alert.setContentText("Your password has been successfully reset!");
                    alert.showAndWait();

                    // Switch back to the login page
                    sceneSwitcher.switchToLoginPage(event);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Password Reset Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to reset your password. Please try again.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Reset Failed");
                alert.setHeaderText(null);
                alert.setContentText("The passwords entered do not match. Please make sure to enter the same password in both fields.");
                alert.showAndWait();
            }
        });
        resetPasswordButton.getStyleClass().add("hover-button");



        Button backButton = new Button("Terug gaan");
        backButton.setOnAction(event -> sceneSwitcher.switchToLoginPage(event));
        backButton.getStyleClass().add("hover-button");

        root.getChildren().addAll(welcomeText, textField, passwordField, passwordField2, backButton, resetPasswordButton);

        return new Scene(root, 600, 800); // Set the desired size of the scene
    }
    private boolean resetPassword(String username, String newPassword) {
        // Implement your password reset logic here
        // This is just a placeholder method
        // Replace it with the actual logic to reset the password for the given username
        // Return true if the password reset is successful, false otherwise
        // You can store the username-password mapping in the userList or a separate database

        for (Userr user : userList) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword);
                return true;
            }
        }

        return false; // Password reset failed
    }
    public Scene createResetUsernamePage() {
        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white;");

        Label welcomeText = new Label("Reset je gebruikersnaam");
        welcomeText.setStyle("-fx-font-size: 24px; -fx-text-fill: darkblue;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Voer je huidige gebruikersnaam in");
        usernameField.setStyle("-fx-background-color: white;");

        TextField newUsernameField = new TextField();
        newUsernameField.setPromptText("Voer je nieuwe gebruikersnaam in");
        newUsernameField.setStyle("-fx-background-color: white;");

        Button resetUsernameButton = new Button("Gebruikersnaam herstellen");
        resetUsernameButton.setOnAction(event -> {
            String username = usernameField.getText();
            String newUsername = newUsernameField.getText();

            boolean isUsernameResetSuccessful = resetUsername(username, newUsername);

            if (isUsernameResetSuccessful) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Username Reset Successful");
                alert.setHeaderText(null);
                alert.setContentText("Your username has been successfully reset!");
                alert.showAndWait();

                // Switch back to the login page
                sceneSwitcher.switchToLoginPage(event);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Username Reset Failed");
                alert.setHeaderText(null);
                alert.setContentText("Failed to reset your username. Please try again.");
                alert.showAndWait();
            }
        });
        resetUsernameButton.getStyleClass().add("hover-button");

        Button backButton = new Button("Terug gaan");
        backButton.setOnAction(event -> sceneSwitcher.switchToLoginPage(event));
        backButton.getStyleClass().add("hover-button");

        root.getChildren().addAll(welcomeText, usernameField, newUsernameField, resetUsernameButton, backButton);

        return new Scene(root, 600, 800); // Set the desired size of the scene
    }
    private boolean resetUsername(String oldUsername, String newUsername) {
        // Implement your username reset logic here
        // This is just a placeholder method
        // Replace it with the actual logic to reset the username for the given oldUsername
        // Return true if the username reset is successful, false otherwise
        // You can store the username-password mapping in the userList or a separate database

        for (Userr user : userList) {
            if (user.getUsername().equals(oldUsername)) {
                user.setUsername(newUsername);
                return true;
            }
        }

        return false; // Username reset failed
    }
    public Scene createChatPage() {
        BorderPane root = new BorderPane();

        // Create a VBox to hold the chat menu items
        VBox chatMenuContainer = new VBox();

        // Create the chat menu as a ListView
        ListView<String> chatMenu = new ListView<>();
        chatMenu.setPrefWidth(200);

        // Create a placeholder item in the chat menu
        chatMenu.getItems().add("Select a chat");

        // Create the create chat button
        Button createChatButton = new Button("Create Chat");
        Button backButton = new Button("Logout");
        backButton.setOnAction(event -> sceneSwitcher.switchToLoginPage(event));

        // Create a map to store the chat history managers for each chat
        Map<String, ChatHistoryManager> chatHistoryMap = new HashMap<>();

        createChatButton.setOnAction(event -> {
            // Prompt the user to enter a name for the new chat
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Create Chat");
            dialog.setHeaderText("Enter the name for the new chat:");
            Optional<String> result = dialog.showAndWait();

            // Create a new chat with the entered name and type
            result.ifPresent(chatName -> {
                // Prompt the user to select the chat type
                ChoiceDialog<ChatType> typeDialog = new ChoiceDialog<>(ChatType.BOOLEAN, ChatType.BOOLEAN, ChatType.TEXT);
                typeDialog.setTitle("Select Chat Type");
                typeDialog.setHeaderText("Select the type for the new chat:");
                typeDialog.setContentText("Chat Type:");
                Optional<ChatType> typeResult = typeDialog.showAndWait();

                // Add the new chat to the chat menu with its associated type
                typeResult.ifPresent(chatType -> {
                    chatMenu.getItems().add(chatName);
                    chatMap.put(chatName, chatType);
                    chatHistoryMap.put(chatName, new ChatHistoryManager());
                });
            });
        });

        // Add the create chat button to the chat menu container
        chatMenuContainer.getChildren().addAll(chatMenu, createChatButton,backButton);

        // Set the chat menu container on the left side of the root layout
        root.setLeft(chatMenuContainer);

        // Create the chat history display box
        TextArea chatHistoryDisplay = new TextArea();
        chatHistoryDisplay.setEditable(false);
        chatHistoryDisplay.setWrapText(true);

        // Create the message input box
        TextField messageInput = new TextField();
        messageInput.setPromptText("Type your message here");

        // Create the send button
        Button sendButton = new Button("Send");
        sendButton.setDefaultButton(true);

        // Create the chat area containing the chat history display, message input, and send button
        VBox chatArea = new VBox(10);
        chatArea.setPadding(new Insets(10));
        chatArea.getChildren().addAll(chatHistoryDisplay, messageInput, sendButton);

        sendButton.setOnAction(event -> {
            String message = messageInput.getText();
            // Process and send the message to the selected chat
            QueryResolutionForm<String> queryForm = new QueryResolutionForm<>(message);

            // Get the selected chat from the chat menu
            String selectedChat = chatMenu.getSelectionModel().getSelectedItem();

            // Get the chat type associated with the selected chat
            ChatType selectedChatType = chatMap.get(selectedChat);

            // Get the ChatHistoryManager for the selected chat
            ChatHistoryManager chatHistoryManager = chatHistoryMap.get(selectedChat);

            // Set the selected chat factory based on the chat type
            AbstractChatFactory<String, ?> selectedChatFactory;
            if (selectedChatType == ChatType.BOOLEAN) {
                selectedChatFactory = new TextToBooleanChatFactory();
            } else if (selectedChatType == ChatType.TEXT) {
                selectedChatFactory = new TextToTextChatFactory();
            } else {
                // Handle any additional chat types here
                selectedChatFactory = null; // Provide the appropriate factory for the additional chat types
            }

            if (selectedChatFactory != null) {
                QueryResolutionStrategy<String, ?> strategy = selectedChatFactory.createStrategy();
                strategy.setChatHistoryManager(chatHistoryManager);
                QueryResolutionResult<?> result = strategy.resolve(queryForm);

                // Append the user's message to the chat history display
                chatHistoryDisplay.appendText("User: " + message + "\n");
                // Append the chat response to the chat history display
                chatHistoryDisplay.appendText("Bot: " + result.getResultData().toString() + "\n");
            }

            // Clear the message input box
            messageInput.clear();
        });

        chatMenu.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the chat history display when a new chat is selected
            chatHistoryDisplay.clear();
            // Load the chat history for the selected chat and display it in the chat history display
            if (newValue != null && !newValue.equals("Select a chat")) {
                ChatHistoryManager chatHistoryManager = chatHistoryMap.get(newValue);
                List<String> chatHistory = chatHistoryManager.getChatHistory();
                // Append the chat history to the chat history display
                for (int i = 0; i < chatHistory.size(); i += 2) {
                    String userMessage = chatHistory.get(i);
                    String botResponse = chatHistory.get(i + 1);
                    chatHistoryDisplay.appendText("User: " + userMessage + "\n");
                    chatHistoryDisplay.appendText("Bot: " + botResponse + "\n");
                }
            }
        });

        // Set the chat area as the center of the root layout
        root.setCenter(chatArea);

        return new Scene(root, 800, 600); // Set the desired size of the scene
    }
    public Scene createSettings() {
        AnchorPane root = new AnchorPane();
        root.setPrefSize(600, 400);

        Button lightModeButton = new Button("Light Mode");
        lightModeButton.getStyleClass().add("settings-button");
        lightModeButton.setLayoutX(50);
        lightModeButton.setLayoutY(50);
        lightModeButton.setOnAction(event -> sceneFunctions.changeToLightMode());

        Button darkModeButton = new Button("Dark Mode");
        darkModeButton.getStyleClass().add("settings-button");
        darkModeButton.setLayoutX(150);
        darkModeButton.setLayoutY(50);
        darkModeButton.setOnAction(event -> sceneFunctions.changeToDarkMode());

        Button languageButton = new Button("Change Language");
        languageButton.getStyleClass().add("settings-button");
        languageButton.setLayoutX(250);
        languageButton.setLayoutY(50);
        languageButton.setOnAction(event -> sceneFunctions.changeLanguage());

        Button returnButton = new Button("Return to Chat");
        returnButton.getStyleClass().add("settings-button");
        returnButton.setLayoutX(350);
        returnButton.setLayoutY(50);
        returnButton.setOnAction(event -> sceneSwitcher.switchTocreateChatPage(event));

        Button logoutButton = new Button("Logout");
        logoutButton.getStyleClass().add("settings-button");
        logoutButton.setLayoutX(450);
        logoutButton.setLayoutY(50);
        logoutButton.setOnAction(event -> sceneFunctions.logout());

        root.getChildren().addAll(lightModeButton, darkModeButton, languageButton, returnButton, logoutButton);

        return new Scene(root);
    }


}
