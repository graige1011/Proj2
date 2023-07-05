package com.example.proj2.GuiManager;

import com.example.proj2.chatMessage.ChatHistoryManager;
import com.example.proj2.login.User;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SceneFunctions {

    public static void applyLanguage(Label usernameLabel, Label passwordLabel, Button loginButton,
                                     Button createAccountButton, Button forgotPasswordButton,
                                     Button forgotUsernameButton, Button forgotEmailButton,
                                     String selectedLanguage) {
        if (selectedLanguage.equals("English")) {
            usernameLabel.setText("Username:");
            passwordLabel.setText("Password:");
            loginButton.setText("Log in");
            createAccountButton.setText("Create New Account");
            forgotPasswordButton.setText("Forgot Password");
            forgotUsernameButton.setText("Change Username");
            forgotEmailButton.setText("Change email");
        } else if (selectedLanguage.equals("Dutch")) {
            usernameLabel.setText("Gebruikersnaam:");
            passwordLabel.setText("Wachtwoord:");
            loginButton.setText("Inloggen");
            createAccountButton.setText("Nieuw Account Aanmaken");
            forgotPasswordButton.setText("Wachtwoord Vergeten");
            forgotUsernameButton.setText("Gebruikersnaam Veranderen");
            forgotEmailButton.setText("Email Veranderen");
        } else if (selectedLanguage.equals("Spanish")) {
            usernameLabel.setText("Nombre de usuario:");
            passwordLabel.setText("Contraseña:");
            loginButton.setText("Iniciar sesión");
            createAccountButton.setText("Crear nueva cuenta");
            forgotPasswordButton.setText("Olvidé mi contraseña");
            forgotUsernameButton.setText("Olvidé mi nombre de usuario");
            forgotEmailButton.setText("Cambiar email");
        }
    }
}



