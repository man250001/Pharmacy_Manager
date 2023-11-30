package com.example.pharmacy_manager;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.Field;
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
    private ChoiceBox<?> typeBuy, medIdBuy, brandNameBuy, prodNameBuy;

    @FXML
    private TextField quantityBuy, amountBuy;

    @FXML
    private Label totalBuy, balanceBuy;

    @FXML
    private Button buyButton, cartButton;

    @FXML
    private TableView<?> tableViewBuy;
    //endregion

    //region Add Medicine Related
    @FXML
    private TextField medIdAdd, brandNameAdd, prodNameAdd, priceAdd;

    @FXML
    private Button medAdd, medUpdate, medDelete, medClear;

    @FXML
    private ChoiceBox<?> typeAdd, statusAdd;

    @FXML
    private TableView<?> tableViewAdd;
    //endregion

    //region Dashboard Related
    @FXML
    private Label dashmednum, dashincome, dashcustom;

    @FXML
    private LineChart<?, ?> dashchart;
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