package com.example.proj2.login;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;

    public UserManager() {
        users = new ArrayList<>();
        // Add test users
        users.add(new User("humberto", "1"));
        users.add(new User("keanu", "2"));
        users.add(new User("graige", "3"));

    }



    public boolean registerUser(String username, String password) {
        // Perform the registration logic here, including validation and adding the user to the list
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
        // Perform any validation checks as needed
        // For example, check if the username is already taken
        // You can customize this logic based on your requirements

        // Here, we simply check if the username is not empty and the password is at least 4 characters long
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

