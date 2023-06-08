    package com.example.proj2;

    import com.example.proj2.login.User;
    import javafx.event.ActionEvent;
    import javafx.geometry.Insets;
    import javafx.geometry.Pos;
    import javafx.scene.Node;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.*;
    import javafx.stage.Stage;

    import java.util.ArrayList;
    import java.util.List;

    public class HelloController {
        private List<User> users;
        private TextField usernameField;
        private PasswordField passwordField;

        public HelloController() {
            users = new ArrayList<>();
            // Add test users
            users.add(new User("humberto", "1"));
            users.add(new User("keanu", "2"));
            users.add(new User("graige", "3"));
        }

        public Scene createHelloScene() {
            VBox root = new VBox();
            root.setAlignment(Pos.CENTER);
            root.setSpacing(40);
            root.setStyle("-fx-background-color: white;");
            root.setPadding(new Insets(40));

            ImageView imageView = new ImageView(new Image("https://media.licdn.com/dms/image/C4D0BAQHX1qomlcFEUA/company-logo_200_200/0/1548250566657?e=2147483647&amp;v=beta&amp;t=uXtCqDfNta4julyp-JYgX3X2n2pDgF8tesPnDZuGtk8"));
            root.getChildren().add(imageView);

            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setVgap(20);
            gridPane.getColumnConstraints().add(new ColumnConstraints());

            gridPane.getRowConstraints().add(new RowConstraints());
            gridPane.getRowConstraints().add(new RowConstraints());

            Label titleLabel = new Label("AIsistify");
            titleLabel.setStyle("-fx-font-size: 36px; -fx-text-fill: darkblue; -fx-font-weight: bold;");
            gridPane.add(titleLabel, 0, 0);

            Label usernameLabel = new Label("Gebruikersnaam:");
            usernameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: lightblue;");
            gridPane.add(usernameLabel, 0, 1);

            usernameField = new TextField();
            usernameField.setPromptText("Voer gebruikersnaam in");
            usernameField.setStyle("-fx-background-color: white;");
            usernameField.setPrefWidth(200);
            usernameField.setPrefHeight(30);
            gridPane.add(usernameField, 1, 1);

            Label passwordLabel = new Label("Wachtwoord:");
            passwordLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: lightblue;");
            gridPane.add(passwordLabel, 0, 2);

            passwordField = new PasswordField();
            passwordField.setPromptText("Voer wachtwoord in");
            passwordField.setStyle("-fx-background-color: white;");
            passwordField.setPrefWidth(200);
            passwordField.setPrefHeight(30);
            gridPane.add(passwordField, 1, 2);

            Button loginButton = new Button("Log in");
            loginButton.setOnAction(this::handleLogin);
            loginButton.getStyleClass().add("hover-button");
            gridPane.add(loginButton, 0, 3, 2, 1);

            Button createAccountButton = new Button("Nieuw Account Aanmaken");
            createAccountButton.setOnAction(this::switchToScene2);
            createAccountButton.setStyle("-fx-min-width: 150px;");
            createAccountButton.getStyleClass().add("hover-button");
            gridPane.add(createAccountButton, 0, 4, 2, 1);

            Button forgotPasswordButton = new Button("Wachtwoord Vergeten");
            forgotPasswordButton.setOnAction(this::switchToScene3);
            forgotPasswordButton.setStyle("-fx-min-width: 150px;");
            forgotPasswordButton.getStyleClass().add("hover-button");
            gridPane.add(forgotPasswordButton, 0, 5, 2, 1);


            root.getChildren().add(gridPane);

            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

            return scene;
        }

        public void handleLogin(ActionEvent event) {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (validateLogin(username, password)) {
                switchToScene5(event);
            } else {
                // Display an error message or perform appropriate action for failed login
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password");
                alert.showAndWait();
            }
        }

        private boolean validateLogin(String username, String password) {
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
        public  void switchToHelloScene(ActionEvent event) {
            HelloController helloController = new HelloController();
            Scene helloScene = helloController.createHelloScene();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(helloScene);
            stage.setTitle("AIsistify");
            stage.show();
        }
        public void switchToScene2(ActionEvent event) {
            Scene2Controller scene2Controller = new Scene2Controller();
            scene2Controller.setUsers(users);

            Parent scene2Root = scene2Controller.createScene2UI();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene2Root, 800, 600));
            stage.setTitle("AIsistify");
            stage.show();
        }

        public void switchToScene3(ActionEvent event) {
            Scene3Controller scene3Controller = new Scene3Controller();

            VBox scene3Root = scene3Controller.createScene3UI();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene3Root, 800, 600));
            stage.setTitle("AIsistify");
            stage.show();
        }

        public void switchToScene4(ActionEvent event){
            Scene4Controller scene4Controller = new Scene4Controller();

            VBox scene4Root = scene4Controller.createScene4UI();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene4Root, 800, 600));
            stage.setTitle("AIsistify");
            stage.show();
        }

        public void switchToScene5(ActionEvent event) {
            Scene5Controller scene5Controller = new Scene5Controller();

            VBox scene5Root = scene5Controller.createScene5UI();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene5Root, 800, 600));
            stage.setTitle("AIsistify");
            stage.show();
        }

        public void openSettings(ActionEvent event) {
            SettingsController settingsController = new SettingsController();
            AnchorPane settingsRoot = settingsController.createSettingsUI();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(settingsRoot, 800, 600));
            stage.setTitle("AIsistify");
            stage.show();
        }
        public List<User> getUsers() {
            return users;
        }

    }
