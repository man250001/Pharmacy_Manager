package com.example.pharmacy_manager;

public class Medicine {
    private final int id;
    private final int medicineId;
    private final String brand;
    private final String productName;
    private final String type;
    private final String status;
    private final double price;
    private final String date;

    public Medicine(int id, int medicineId, String brand, String productName, String type, String status, double price, String date) {
        this.id = id;
        this.medicineId = medicineId;
        this.brand = brand;
        this.productName = productName;
        this.type = type;
        this.status = status;
        this.price = price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public String getBrand() {
        return brand;
    }

    public String getProductName() {
        return productName;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }
}
