package com.example.fooddoor.Modelclass;

import java.io.Serializable;

public class AdminOrderItemModel implements Serializable {


        public String adminItemName;
        public int adminQty;
        public double adminPrice;   // total for that item line

        public AdminOrderItemModel(String adminItemName, int adminQty, double adminPrice) {
            this.adminItemName = adminItemName;
            this.adminQty = adminQty;
            this.adminPrice = adminPrice;
        }
    }

