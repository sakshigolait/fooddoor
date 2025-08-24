package com.example.fooddoor;

public class CartModel {
    private String name;
    private int quantity;
    private int price;
    private int image;

    public CartModel(String name, int quantity, int price, int image) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public int getPrice() { return price; }
    public int getImage() { return image; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
