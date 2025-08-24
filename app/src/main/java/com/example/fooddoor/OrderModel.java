package com.example.fooddoor;

public class OrderModel {

    private final String itemName; // ⭐ New field
    private final String status;
    private final String date;
    private final int qty;
    private final int imageRes;

    public OrderModel(String itemName, String status, String date, int qty, int imageRes) {
        this.itemName = itemName;
        this.status = status;
        this.date = date;
        this.qty = qty;
        this.imageRes = imageRes;
    }

    public String getItemName() { return itemName; } // ⭐ Getter for name
    public String getStatus() { return status; }
    public String getDate() { return date; }
    public int getQty() { return qty; }
    public int getImageRes() { return imageRes; }
}
