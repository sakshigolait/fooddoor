package com.example.fooddoor;

public class DeliveryModel {
    String orderId, paymentMethod, totalPayment, pickupAddress, dropAddress, time;

    public DeliveryModel(String orderId, String paymentMethod, String totalPayment,
                         String pickupAddress, String dropAddress, String time) {
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.totalPayment = totalPayment;
        this.pickupAddress = pickupAddress;
        this.dropAddress = dropAddress;
        this.time = time;
    }

    public String getOrderId() { return orderId; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getTotalPayment() { return totalPayment; }
    public String getPickupAddress() { return pickupAddress; }
    public String getDropAddress() { return dropAddress; }
    public String getTime() { return time; }
}