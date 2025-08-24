package com.example.fooddoor;

public class FoodItem {
    private final String name;
    private final String price;
    private final int imageRes;
    private final String category;  // ✅ नया field जोड़ा

    public FoodItem(String name, String price, int imageRes, String category) {
        this.name = name;
        this.price = price;
        this.imageRes = imageRes;
        this.category = category;
    }

    // ✅ Getters
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getCategory() {
        return category;
    }
}
