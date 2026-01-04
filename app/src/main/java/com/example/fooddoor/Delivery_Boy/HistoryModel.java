package com.example.fooddoor.Delivery_Boy;

public class HistoryModel {
    String orderId, paymentMethod, amount, status;

    public HistoryModel(String orderId, String paymentMethod, String amount, String status) {
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = status;
    }

}
