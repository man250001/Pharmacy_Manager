package com.example.pharmacy_manager;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("unused")
public class HomeMenuController implements Initializable {
    @FXML
    private SplitPane DashboardData;

    @FXML
    private AnchorPane Leftbox, MedicineFields, MedicineTable, Rightbox, buyPane, buyTable, DashboardChart;

    @FXML
    private Button buyButton, cartButton;

    @FXML
    private Hyperlink dashlink, medlink, buylink;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        dashlink.setOnAction(event -> {
            PaneDisabler();
            Platform.runLater(() -> {
                DashboardChart.setVisible(true);
                DashboardData.setVisible(true);
            });
        });
        medlink.setOnAction(event -> {
            PaneDisabler();
            Platform.runLater(() -> {
                MedicineFields.setVisible(true);
                MedicineTable.setVisible(true);
            });
        });
        buylink.setOnAction(event -> {
            PaneDisabler();
            Platform.runLater(() -> {
                buyPane.setVisible(true);
                buyTable.setVisible(true);
            });
        });
    }

    public void PaneDisabler() {
        buyPane.setVisible(false);
        buyTable.setVisible(false);
        DashboardChart.setVisible(false);
        DashboardData.setVisible(false);
        MedicineFields.setVisible(false);
        MedicineTable.setVisible(false);
    }
}