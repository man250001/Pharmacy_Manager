package com.example.pharmacy_manager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("unused")
public class LoginController implements Initializable {
    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        loginButton.setOnAction(event -> {
            try {
                if (DBUtils.logInUser(event, usernameField.getText(), passwordField.getText())) {
                    DBUtils.changeScene(event, "HomeMenu.fxml", "Home Menu");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}