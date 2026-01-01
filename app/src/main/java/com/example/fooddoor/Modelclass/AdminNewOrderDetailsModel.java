package com.example.fooddoor.Modelclass;

import java.io.Serializable;
import java.util.List;

public class AdminNewOrderDetailsModel implements Serializable {

    public String newOrder_detailsCustomerName;
    public String newOrder_detailsTime;
    public String newOrder_detailsOrderId;

    public String newOrder_detailsPhone;
    public String newOrder_detailsEmail;
    public String newOrder_detailsAddress;
    public String newOrder_detailsMessage;

    public List<AdminNewOrderItemDetailsModel> newOrder_detailsItems;

    public AdminNewOrderDetailsModel(
            String customerName,
            String timeText,
            String orderId,
            String phone,
            String email,
            String address,
            String message,
            List<AdminNewOrderItemDetailsModel> items
    ) {
        this.newOrder_detailsCustomerName = customerName;
        this.newOrder_detailsTime = timeText;
        this.newOrder_detailsOrderId = orderId;
        this.newOrder_detailsPhone = phone;
        this.newOrder_detailsEmail = email;
        this.newOrder_detailsAddress = address;
        this.newOrder_detailsMessage = message;
        this.newOrder_detailsItems = items;
    }

    public double getNewOrderDetailsTotal() {
        double t = 0;
        for (AdminNewOrderItemDetailsModel m : newOrder_detailsItems) {
            t += m.newOrder_detailsPrice;
        }
        return t;
    }
}
