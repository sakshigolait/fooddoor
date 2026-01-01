package com.example.fooddoor.Modelclass;

import java.io.Serializable;

public class AdminOngoingOrderItemModel implements Serializable {

    public String adminItemName;
    public int adminQty;
    public double adminPrice;   // total price for this line

    public AdminOngoingOrderItemModel(String adminItemName, int adminQty, double adminPrice) {
        this.adminItemName = adminItemName;
        this.adminQty = adminQty;
        this.adminPrice = adminPrice;
    }
}