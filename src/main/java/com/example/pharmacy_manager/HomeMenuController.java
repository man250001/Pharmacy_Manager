package com.example.pharmacy_manager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeMenuController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}