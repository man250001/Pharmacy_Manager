package com.example.pharmacy_manager;

public record Transaction(int medId, String brandName, String prodName, String type, String status, Double price, int quantity) {
}
