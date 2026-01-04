package com.example.fooddoor.Modelclass;

import java.io.Serializable;
import java.util.List;

public class AdminPastOrderModel implements Serializable {
    public String adminCustomerName;
    public String adminTimeText;
    public String adminOrderId;
    public List<AdminOrderItemModel> adminItems;
    public boolean adminIsDelivered; // true = delivered, false = cancelled

    public AdminPastOrderModel(String adminCustomerName, String adminTimeText,
                           String adminOrderId, List<AdminOrderItemModel> adminItems,
                           boolean adminIsDelivered) {
        this.adminCustomerName = adminCustomerName;
        this.adminTimeText = adminTimeText;
        this.adminOrderId = adminOrderId;
        this.adminItems = adminItems;
        this.adminIsDelivered = adminIsDelivered;
    }

    public double getAdminTotal() {
        double t = 0;
        for (AdminOrderItemModel m : adminItems) {
            t += m.adminPrice;
        }
        return t;
    }
}
