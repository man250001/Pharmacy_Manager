module com.example.pharmacy_manager {
    requires transitive javafx.base;
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires java.sql;


    opens com.example.pharmacy_manager to javafx.fxml;
    exports com.example.pharmacy_manager;
}