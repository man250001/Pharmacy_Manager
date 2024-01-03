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
import java.util.ArrayList;
import java.util.ResourceBundle;

@SuppressWarnings({"unused", "SpellCheckingInspection", "unchecked"})
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
    private ChoiceBox<String> typeBuy, brandBuy, prodNameBuy;

    @FXML
    private TableColumn<Transaction, String> nameColBuy, pNameColBuy, typeColBuy;

    @FXML
    private TableColumn<Transaction, Integer> idColBuy, quanColBuy;

    @FXML
    private TextField quantityBuy, amountBuy;

    @FXML
    private Label priceBuy, balanceBuy;

    @FXML
    private Button buyButton, cartButton;

    @FXML
    private ChoiceBox<Integer> medIdBuy;

    @FXML
    private TableColumn<Transaction, Double> priceColBuy;

    @FXML
    private TableView<Transaction> tableViewBuy;
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

    ObservableList<Transaction> cart = FXCollections.observableArrayList();
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        UpdateTable();
        fillMedicine();

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
                UpdateTable();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        medClear.setOnAction(event -> Platform.runLater(this::ClearAllFields));
        medDelete.setOnAction(event -> {
            try {
                DBUtils.removeMedicine(event, Integer.parseInt(medIdAdd.getText()));
                UpdateTable();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        medUpdate.setOnAction(event -> {
            try {
                DBUtils.updateMedicine(event, Integer.parseInt(medIdAdd.getText()), brandNameAdd.getText(), prodNameAdd.getText(), typeAdd.getValue(), statusAdd.getValue(), Double.parseDouble(priceAdd.getText()));
                UpdateTable();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        tableViewAdd.setOnMouseClicked(event -> {
            Medicine med = tableViewAdd.getSelectionModel().getSelectedItem();
            medIdAdd.setText(String.valueOf(med.id()));
            brandNameAdd.setText(med.brand());
            prodNameAdd.setText(med.productName());
            typeAdd.setValue(med.type());
            statusAdd.setValue(med.status());
            priceAdd.setText(String.valueOf(med.price()));
        });
        cartButton.setOnAction(event -> {
            try {
                DBUtils.addToCart(event, Integer.parseInt(medIdBuy.getValue().toString()), brandBuy.getValue(), prodNameBuy.getValue(), typeBuy.getValue(), Integer.parseInt(quantityBuy.getText()));
                UpdateBuyTable();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    //region Menu Methods
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
    //endregion

    //region Add Medicine Methods
    private void UpdateTable() {
        typeAdd.setItems(typeList);
        statusAdd.setItems(statusList);
        medList = FXCollections.observableList(DBUtils.getMedicine(null));
        tableViewAdd.setItems(medList);
        idCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().id()).asObject());
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().brand()));
        prodCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().productName()));
        typeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().type()));
        statusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().status()));
        priceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().price()).asObject());
        tableViewAdd.getColumns().setAll(idCol, nameCol, prodCol, typeCol, statusCol, priceCol);
    }
    //endregion

    //region Buy Medicine Methods
    public void fillMedicine() {
        ArrayList<Medicine> medList = DBUtils.getAvailableMedicine(null);
        ArrayList<Integer> medIdList = new ArrayList<>();
        ArrayList<String> brandList = new ArrayList<>(), prodNameList = new ArrayList<>(), typeList = new ArrayList<>();

        for (Medicine med : medList) {
            typeList.add(med.type());
            medIdList.add(med.medicineId());
            brandList.add(med.brand());
            prodNameList.add(med.productName());
        }
        typeBuy.setItems(FXCollections.observableList(typeList));
        medIdBuy.setItems(FXCollections.observableList(medIdList));
        brandBuy.setItems(FXCollections.observableList(brandList));
        prodNameBuy.setItems(FXCollections.observableList(prodNameList));
    }

    public void UpdateBuyTable() {
        cart = FXCollections.observableArrayList(DBUtils.cart);
        tableViewBuy.setItems(cart);
        pNameColBuy.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().prodName()));
        nameColBuy.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().brandName()));
        typeColBuy.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().type()));
        idColBuy.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().medId()).asObject());
        quanColBuy.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().quantity()).asObject());
        priceColBuy.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().price()).asObject());
        tableViewBuy.getColumns().setAll(idColBuy, nameColBuy, pNameColBuy, typeColBuy, quanColBuy, priceColBuy);
    }
    //endregion

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




}