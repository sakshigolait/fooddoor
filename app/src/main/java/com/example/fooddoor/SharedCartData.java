package com.example.fooddoor;

import java.util.ArrayList;
import java.util.List;

public class SharedCartData {
    private static SharedCartData instance;
    private List<CartModel> cartItems;

    private SharedCartData() {
        cartItems = new ArrayList<>();
    }

    public static SharedCartData getInstance() {
        if (instance == null) {
            instance = new SharedCartData();
        }
        return instance;
    }

    public List<CartModel> getCartItems() {
        return cartItems;
    }

    public void addItem(CartModel item) {
        // check if already in cart
        for (CartModel c : cartItems) {
            if (c.getName().equals(item.getName())) {
                c.setQuantity(c.getQuantity() + item.getQuantity());
                return;
            }
        }
        cartItems.add(item);
    }
}
