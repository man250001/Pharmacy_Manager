package com.example.pharmacy_manager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

@SuppressWarnings("unused")
public class DBUtils {

    public static void changeScene(ActionEvent event, String _fxmlFile, String _title) {
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(_fxmlFile));
            Parent root = loader.load();

            Platform.runLater(() -> {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle(_title);
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.centerOnScreen();
                stage.show();
            });
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean logInUser(ActionEvent event, String username, String pwd) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "password");
             PreparedStatement psCheckIfUserExists = conn.prepareStatement("SELECT * FROM admin WHERE username = ?")) {

            psCheckIfUserExists.setString(1, username);
            try (ResultSet resultSet = psCheckIfUserExists.executeQuery()) {
                while (resultSet.next()) {
                    if (resultSet.getString("password").equals(pwd)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
