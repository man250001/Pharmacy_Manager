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

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class HomeMenuController implements Initializable {
    //region Header Related
    @FXML
    private Hyperlink dashLink, medLink, buyLink;

    @FXML
    private AnchorPane Leftbox, MedicineFields, MedicineTable, Rightbox, buyPane, buyTable, DashboardChart;

    @FXML
    private SplitPane DashboardData;
    //endregion

    //region Buy Medicine Related
    @FXML
    private Button buyButton, cartButton;
    //endregion

    //region Add Medicine Related
    //endregion

    //region Dashboard Related
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        dashLink.setOnAction(event -> Platform.runLater(() -> {
            PaneDisabler();
            DashboardChart.setVisible(true);
            DashboardData.setVisible(true);
        }));
        medLink.setOnAction(event -> Platform.runLater(() -> {
            PaneDisabler();
            MedicineFields.setVisible(true);
            MedicineTable.setVisible(true);
        }));
        buyLink.setOnAction(event -> Platform.runLater(() -> {
            PaneDisabler();
            buyPane.setVisible(true);
            buyTable.setVisible(true);
        }));
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