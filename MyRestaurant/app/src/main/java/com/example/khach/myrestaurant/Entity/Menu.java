package com.example.khach.myrestaurant.Entity;

/**
 * Created by ThanhHai on 6/13/2017.
 */

public class Menu {
    private String ResID;
    private String FoodTypeID;
    private String FoodName;
    private String Price;
    private String Image;
    private float Rating;

    public Menu() {
    }

    public Menu(String resID, String foodTypeID, String foodName, String price, String image, float rating) {
        ResID = resID;
        FoodTypeID = foodTypeID;
        FoodName = foodName;
        Price = price;
        Image = image;
        Rating = rating;
    }

    public String getResID() {
        return ResID;
    }

    public void setResID(String resID) {
        ResID = resID;
    }

    public String getFoodTypeID() {
        return FoodTypeID;
    }

    public void setFoodTypeID(String foodTypeID) {
        FoodTypeID = foodTypeID;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }
}
