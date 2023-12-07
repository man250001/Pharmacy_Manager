package com.example.pharmacy_manager;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class HomeMenuController implements Initializable {
    //region Header Related
    @FXML
    private Hyperlink dashLink, medLink, buyLink, logOut;

    @FXML
    private AnchorPane Leftbox, MedicineFields, MedicineTable, Rightbox, buyPane, buyTable, DashboardChart;

    @FXML
    private SplitPane DashboardData;
    //endregion

    //region Buy Medicine Related
    @FXML
    private ChoiceBox<?> typeBuy, medIdBuy, brandBuy, prodNameBuy;

    @FXML
    private TextField quantityBuy, amountBuy;

    @FXML
    private Label priceBuy, balanceBuy;

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
    private TableColumn<Medicine, String> nameCol, prodCol, typeCol, statusCol;

    @FXML
    private ChoiceBox<String> typeAdd, statusAdd;

    @FXML
    private TableView<Medicine> tableViewAdd;

    @FXML
    private TableColumn<Medicine, Integer> idCol;

    @FXML
    private TableColumn<Medicine, Double> priceCol;
    //endregion

    //region Dashboard Related
    @FXML
    private Label dashmednum, dashincome, dashcustom;

    @FXML
    private LineChart<?, ?> dashLineChart;
    //endregion

    //region Lists
    final ObservableList<String> typeList = FXCollections.observableArrayList("Pain Relievers", "Antibiotics", "Cardiovascular", "Metabolic", "Respiratory");

    final ObservableList<String> statusList = FXCollections.observableArrayList("Available", "Unavailable");
    ObservableList<Medicine> medList = FXCollections.observableArrayList();
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        typeAdd.setItems(typeList);
        statusAdd.setItems(statusList);
        medList = FXCollections.observableList(DBUtils.getMedicine(null, 0));
        tableViewAdd.setItems(medList);
        idCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));
        prodCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()));
        typeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        statusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        priceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        tableViewAdd.getColumns().setAll(idCol, nameCol, prodCol, typeCol, statusCol, priceCol);

        dashLink.setOnAction(event -> Platform.runLater(() -> {
            PaneDisabler();
            CompletelyEnable(DashboardChart);
            CompletelyEnable(DashboardData);
        }));
        medLink.setOnAction(event -> Platform.runLater(() -> {
            PaneDisabler();
            CompletelyEnable(MedicineFields);
            CompletelyEnable(MedicineTable);
        }));
        buyLink.setOnAction(event -> Platform.runLater(() -> {
            PaneDisabler();
            CompletelyEnable(buyPane);
            CompletelyEnable(buyTable);
        }));
        logOut.setOnAction(event -> Platform.runLater(() -> DBUtils.changeScene(event, "Login.fxml", "Login")));
        medAdd.setOnAction(event -> {
            try {
                DBUtils.addMedicine(event, Integer.parseInt(medIdAdd.getText()), brandNameAdd.getText(), prodNameAdd.getText(), typeAdd.getValue(), statusAdd.getValue(), Double.parseDouble(priceAdd.getText()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        medClear.setOnAction(event -> Platform.runLater(this::ClearAllFields));
        medDelete.setOnAction(event -> {
            try {
                DBUtils.removeMedicine(event, Integer.parseInt(medIdAdd.getText()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        tableViewAdd.setOnMouseClicked(event -> {
            Medicine med = tableViewAdd.getSelectionModel().getSelectedItem();
            medIdAdd.setText(String.valueOf(med.getId()));
            brandNameAdd.setText(med.getBrand());
            prodNameAdd.setText(med.getProductName());
            typeAdd.setValue(med.getType());
            statusAdd.setValue(med.getStatus());
            priceAdd.setText(String.valueOf(med.getPrice()));
        });
    }

    public void PaneDisabler() {
        CompletelyDisable(buyPane);
        CompletelyDisable(buyTable);
        CompletelyDisable(DashboardChart);
        CompletelyDisable(DashboardData);
        CompletelyDisable(MedicineFields);
        CompletelyDisable(MedicineTable);
    }

    public void ClearAllFields() {
        medIdAdd.clear();
        brandNameAdd.clear();
        prodNameAdd.clear();
        priceAdd.clear();
        quantityBuy.clear();
        amountBuy.clear();
        priceBuy.setText("0.00");
        balanceBuy.setText("0.00");
        typeBuy.setValue(null);
        medIdBuy.setValue(null);
        brandBuy.setValue(null);
        prodNameBuy.setValue(null);
        typeAdd.setValue(null);
        statusAdd.setValue(null);
    }

    //region Disable and Enable Methods
    //CompletelyDisable Pane Object
    public void CompletelyDisable(Pane pane) {
        pane.setDisable(true);
        pane.setVisible(false);
    }

    //Overloaded method
    //Reason: SplitPane is not a subclass of Pane
    public void CompletelyDisable(SplitPane pane) {
        pane.setDisable(true);
        pane.setVisible(false);
    }

    //CompletelyEnable Pane Object
    public void CompletelyEnable(Pane pane) {
        pane.setDisable(false);
        pane.setVisible(true);
    }

    //Overloaded method
    //Reason: SplitPane is not a subclass of Pane
    public void CompletelyEnable(SplitPane pane) {
        pane.setDisable(false);
        pane.setVisible(true);
    }
    //endregion

    //region Add Medicine Methods
    //endregion


}