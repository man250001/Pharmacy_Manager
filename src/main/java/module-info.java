module com.example.pharmacy_manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.pharmacy_manager to javafx.fxml;
    exports com.example.pharmacy_manager;
}