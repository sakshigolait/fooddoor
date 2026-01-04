package com.example.fooddoor.javaClass;

import java.util.List;

public class Order implements java.io.Serializable {
    public String customerName;
    public String time;
    public String orderId;
    public String message;
    public List<OrderLine> lines;
    public String avatarPath;
    public String phone = "";
    public String email = "";
    public String address = "";


    public Order(String customerName, String time, String orderId, String message, List<OrderLine> lines, String avatarPath) {
        this.customerName = customerName;
        this.time = time;
        this.orderId = orderId;
        this.message = message;
        this.lines = lines;
        this.avatarPath = avatarPath;
    }

    public double getTotal() {
        double total = 0;
        for (OrderLine l : lines) total += l.getLineTotal();
        return total;
    }
}

