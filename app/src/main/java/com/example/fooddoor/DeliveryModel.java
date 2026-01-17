package com.example.fooddoor;

import java.io.Serializable;

public class DeliveryModel implements Serializable {

    private double pickupLat;
    private double pickupLng;
    private double dropLat;
    private double dropLng;

    String orderId, paymentMethod, totalPayment, pickupAddress, dropAddress, time;

    // ✅ CONSTRUCTOR WITH LAT LNG
    public DeliveryModel(String orderId, String paymentMethod, String totalPayment,
                         String pickupAddress, double pickupLat, double pickupLng,
                         String dropAddress, double dropLat, double dropLng,
                         String time) {

        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.totalPayment = totalPayment;
        this.pickupAddress = pickupAddress;
        this.pickupLat = pickupLat;
        this.pickupLng = pickupLng;
        this.dropAddress = dropAddress;
        this.dropLat = dropLat;
        this.dropLng = dropLng;
        this.time = time;
    }

    public double getPickupLat() { return pickupLat; }
    public double getPickupLng() { return pickupLng; }
    public double getDropLat() { return dropLat; }
    public double getDropLng() { return dropLng; }

    public String getOrderId() { return orderId; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getTotalPayment() { return totalPayment; }
    public String getPickupAddress() { return pickupAddress; }
    public String getDropAddress() { return dropAddress; }
    public String getTime() { return time; }
}