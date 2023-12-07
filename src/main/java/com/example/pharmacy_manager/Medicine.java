package com.example.pharmacy_manager;

@SuppressWarnings("unused")
public record Medicine(int id, int medicineId, String brand, String productName, String type, String status,
                       double price, String date) {
}
