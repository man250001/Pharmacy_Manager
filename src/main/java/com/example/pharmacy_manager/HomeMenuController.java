package com.example.pharmacy_manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

@SuppressWarnings("unused")
public class HomeMenuController {
    @FXML
    private SplitPane DashboardData;

    @FXML
    private AnchorPane Leftbox, MedicineFields, MedicineTable, Rightbox, buyPane, buyTable, DashboardChart;

    @FXML
    private Button buyButton, cartButton;
}