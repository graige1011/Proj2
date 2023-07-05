package com.example.proj2.login;

public class User {
    private String username;
    private String password;
    private String email;

    public User(String username, String password,String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public static boolean validateRegistration(String username, String password) {
        return !username.isEmpty() && password.length() >= 4;
    }
}
