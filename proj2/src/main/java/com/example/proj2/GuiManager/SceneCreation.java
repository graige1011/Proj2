package com.example.proj2.GuiManager;
import com.example.proj2.chatMessage.*;
import com.example.proj2.login.User;
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
    private Button forgotEmailButton;
    private PasswordField passwordField;
    private TextField usernameField;
    private Map<String, ChatType> chatMap = new HashMap<>();

    private Map<String, QueryResolutionStrategy<?, ?>> chats = new HashMap<>();

    Map<String, ChatHistoryManager> chatHistoryMap = new HashMap<>();

    private ArrayList<User> userList;

    List<String> chatMessages = new ArrayList<>();
    private SceneCreationStrategy loginPageStrategy;
    private SceneCreationStrategy newAccountPageStrategy;
    private SceneCreationStrategy resetPasswordPageStrategy;
    private SceneCreationStrategy resetUsernamePageStrategy;
    private SceneCreationStrategy createChatpageStrategy;
    private SceneCreationStrategy createSettingsStrategy;
    private SceneCreationStrategy resetEmailPageStrategy;

    public SceneCreation() {
        sceneFunctions = new SceneFunctions();
        sceneSwitcher = new SceneSwitcher(this);
        userList =new ArrayList<User>();

        loginPageStrategy = new LoginPageStrategy(sceneSwitcher,userList);
        newAccountPageStrategy = new NewAccountPageStrategy(sceneSwitcher,userList);
        resetPasswordPageStrategy = new ResetPasswordPageStrategy(sceneSwitcher,userList);
        resetUsernamePageStrategy = new ResetUsernamePageStrategy(sceneSwitcher,userList);
        createChatpageStrategy = new CreateChatPageStrategy(sceneSwitcher);
        createSettingsStrategy = new CreateSettingsStrategy(sceneSwitcher,sceneFunctions);
        resetEmailPageStrategy = new ResetEmailPageStrategy(sceneSwitcher,userList);

    }
    enum ChatType {
        BOOLEAN,
        TEXT
    }
    public Scene createLoginPage() {
        return loginPageStrategy.createScene();
    }
    public Scene createNewAccountPage() {
        return newAccountPageStrategy.createScene();
    }
    public Scene createResetPasswordPage() {
        return resetPasswordPageStrategy.createScene();
    }
    public Scene createResetUsernamePage(){return resetUsernamePageStrategy.createScene();}
    public Scene createChatPage() {return createChatpageStrategy.createScene();
    }
    public Scene createSettings(){return createSettingsStrategy.createScene();}

    public Scene createResetEmailPage(){return resetEmailPageStrategy.createScene();}




}
