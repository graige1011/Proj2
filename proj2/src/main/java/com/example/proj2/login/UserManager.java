package com.example.proj2.login;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;

    public UserManager() {
        users = new ArrayList<>();
        users.add(new User("humberto", "1"));
        users.add(new User("keanu", "2"));
        users.add(new User("graige", "3"));

    }



    public boolean registerUser(String username, String password) {
        if (validateRegistration(username, password)) {
            User newUser = new User(username, password);
            users.add(newUser);
            return true;
        } else {
            return false;
        }
    }
    // Method to validate registration credentials
    public boolean validateRegistration(String username, String password) {
        return !username.isEmpty() && password.length() >= 4;
    }
    public boolean validateLogin(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public User zoekGebruikerOpGebruikersnaam(String gebruikersnaam) {
        for (User user : users) {
            if (user.getUsername().equals(gebruikersnaam)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User newUser) {
        users.add(newUser);
          }
    public List<User> getUsers() {
        return users;
       }
    }

