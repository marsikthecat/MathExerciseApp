module com.example.kopfrechnen {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kopfrechnen to javafx.fxml;
    exports com.example.kopfrechnen;
    exports com.example.kopfrechnen.model;
    opens com.example.kopfrechnen.model to javafx.fxml;
    exports com.example.kopfrechnen.viewmodel;
    opens com.example.kopfrechnen.viewmodel to javafx.fxml;
    exports com.example.kopfrechnen.uiutils;
    opens com.example.kopfrechnen.uiutils to javafx.fxml;
}