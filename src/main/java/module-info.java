module com.example.pharmacy_manager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pharmacy_manager to javafx.fxml;
    exports com.example.pharmacy_manager;
}