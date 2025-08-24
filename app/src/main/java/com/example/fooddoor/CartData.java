package com.example.fooddoor;

import java.util.ArrayList;
import java.util.List;

public class CartData {
    private static CartData instance;
    private List<CartModel> cartList = new ArrayList<>();

    private CartData() {}

    public static CartData getInstance() {
        if (instance == null) {
            instance = new CartData();
        }
        return instance;
    }

    public List<CartModel> getCartList() {
        return cartList;
    }

    public void setCartList(List<CartModel> list) {
        cartList = list;
    }
}
