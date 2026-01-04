package com.example.fooddoor.Modelclass;

import java.io.Serializable;

public class AdminNewOrderItemDetailsModel implements Serializable {

    public String newOrder_detailsItemName;
    public int newOrder_detailsQty;
    public double newOrder_detailsPrice;

    public AdminNewOrderItemDetailsModel(String itemName, int qty, double price) {
        this.newOrder_detailsItemName = itemName;
        this.newOrder_detailsQty = qty;
        this.newOrder_detailsPrice = price;
    }
}