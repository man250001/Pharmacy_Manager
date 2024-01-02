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
import java.util.ArrayList;

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

    public static void addMedicine(ActionEvent event, int medId, String brandName, String prodName, String type, String status, Double price) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "password");
             PreparedStatement psAddMedicine = conn.prepareStatement("INSERT INTO medicine (medicineId, brand, productName, type, status, price, date) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            psAddMedicine.setInt(1, medId);
            psAddMedicine.setString(2, brandName);
            psAddMedicine.setString(3, prodName);
            psAddMedicine.setString(4, type);
            psAddMedicine.setString(5, status);
            psAddMedicine.setDouble(6, price);
            psAddMedicine.setDate(7, new Date(System.currentTimeMillis()));
            psAddMedicine.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeMedicine(ActionEvent event, int medId) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "password");
             PreparedStatement psRemoveMedicine = conn.prepareStatement("DELETE FROM medicine WHERE medicineId = ?")) {

            psRemoveMedicine.setInt(1, medId);
            psRemoveMedicine.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateMedicine(ActionEvent event, int medId, String brandName, String prodName, String type, String status, Double price) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "password");
             PreparedStatement psUpdateMedicine = conn.prepareStatement("UPDATE medicine SET brand = ?, productName = ?, type = ?, status = ?, price = ?, date = ? WHERE medicineId = ?")) {

            psUpdateMedicine.setString(1, brandName);
            psUpdateMedicine.setString(2, prodName);
            psUpdateMedicine.setString(3, type);
            psUpdateMedicine.setString(4, status);
            psUpdateMedicine.setDouble(5, price);
            psUpdateMedicine.setDate(6, new Date(System.currentTimeMillis()));
            psUpdateMedicine.setInt(7, medId);
            psUpdateMedicine.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addToCart(ActionEvent event, int medId, String brandName, String prodName, String type, String status, Double price, int quantity) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "password")){
            PreparedStatement psGetMedicine = conn.prepareStatement("SELECT * FROM medicine WHERE medicineId = ?");
            psGetMedicine.setInt(1, medId);
            ResultSet rs = psGetMedicine.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Medicine> getMedicine(ActionEvent event, int medId) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "password")){
             PreparedStatement psGetMedicine = conn.prepareStatement("SELECT * FROM medicine");
                ResultSet rs = psGetMedicine.executeQuery();

                ArrayList<Medicine> medicineList = new ArrayList<>();
                while (rs.next()) {
                    medicineList.add(new Medicine(rs.getInt("id"), rs.getInt("medicineId"), rs.getString("brand"), rs.getString("productName"), rs.getString("type"), rs.getString("status"), rs.getDouble("price"), rs.getString("date")));
                }
                return medicineList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
