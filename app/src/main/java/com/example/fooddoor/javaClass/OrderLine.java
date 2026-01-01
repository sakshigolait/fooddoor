package com.example.fooddoor.javaClass;

import java.io.Serializable;

public class OrderLine implements Serializable {
    public String name;
    public int qty;
    public double price;

    public OrderLine(String name, int qty, double price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public double getLineTotal() {
        return qty * price;
    }
}
