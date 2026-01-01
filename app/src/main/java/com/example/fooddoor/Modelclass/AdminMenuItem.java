package com.example.fooddoor.Modelclass;
public class AdminMenuItem {
    private String id; // Use String for ID if using Firebase or a real API
    private String name;
    private String category;
    private double price;
    private String imageUrl;
    private String shortDescription;

    // Constructor
    public AdminMenuItem(String id, String name, String category, double price, String imageUrl, String shortDescription) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
        this.shortDescription = shortDescription;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
    public String getShortDescription() { return shortDescription; }

    // Setters (useful for editing)
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price) { this.price = price; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
}