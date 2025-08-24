package com.example.fooddoor.Modelclass;

public class FoodModel {

    int Foodid,Foodimage;
    String Foodname,Foodprice;

    public FoodModel(int foodid, int foodimage, String foodname, String foodprice) {
        Foodid = foodid;
        Foodimage = foodimage;
        Foodname = foodname;
        Foodprice = foodprice;
    }

    public int getFoodid() {
        return Foodid;
    }

    public void setFoodid(int foodid) {
        Foodid = foodid;
    }

    public int getFoodimage() {
        return Foodimage;
    }

    public void setFoodimage(int foodimage) {
        Foodimage = foodimage;
    }

    public String getFoodname() {
        return Foodname;
    }

    public void setFoodname(String foodname) {
        Foodname = foodname;
    }

    public String getFoodprice() {
        return Foodprice;
    }

    public void setFoodprice(String foodprice) {
        Foodprice = foodprice;
    }
}
