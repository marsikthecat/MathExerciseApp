module com.example.kopfrechnen {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kopfrechnen to javafx.fxml;
    exports com.example.kopfrechnen;
}