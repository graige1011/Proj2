module com.example.proj2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.proj2 to javafx.fxml;
    exports com.example.proj2.login;
    opens com.example.proj2.login to javafx.fxml;
    exports com.example.proj2.GUI;
    opens com.example.proj2.GUI to javafx.fxml;
}