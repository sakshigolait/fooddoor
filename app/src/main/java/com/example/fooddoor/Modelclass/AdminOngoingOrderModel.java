package com.example.fooddoor.Modelclass;

import java.io.Serializable;
import java.util.List;

public class AdminOngoingOrderModel implements Serializable {

    public String adminCustomerName;
    public String adminTimeText;
    public String adminOrderId;
    public List<AdminOngoingOrderItemModel> adminItems;
    public String adminCurrentStatus; // "Order Dispatched", ...

    public AdminOngoingOrderModel(String adminCustomerName,
                                  String adminTimeText,
                                  String adminOrderId,
                                  List<AdminOngoingOrderItemModel> adminItems,
                                  String adminCurrentStatus) {
        this.adminCustomerName = adminCustomerName;
        this.adminTimeText = adminTimeText;
        this.adminOrderId = adminOrderId;
        this.adminItems = adminItems;
        this.adminCurrentStatus = adminCurrentStatus;
    }

    public double getAdminTotal() {
        double t = 0;
        for (AdminOngoingOrderItemModel m : adminItems) {
            t += m.adminPrice;
        }
        return t;
    }
}