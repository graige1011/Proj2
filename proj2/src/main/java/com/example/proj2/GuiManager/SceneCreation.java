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
    private ArrayList<User> userList;
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
