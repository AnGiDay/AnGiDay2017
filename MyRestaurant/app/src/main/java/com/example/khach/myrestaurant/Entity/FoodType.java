package com.example.khach.myrestaurant.Entity;

/**
 * Created by ThanhHai on 6/13/2017.
 */

public class FoodType {
    private String FoodTypeID;

    public FoodType() {
    }

    private String FoodTypeName;

    public FoodType(String foodTypeID, String foodTypeName) {
        FoodTypeID = foodTypeID;
        FoodTypeName = foodTypeName;
    }

    public String getFoodTypeID() {
        return FoodTypeID;
    }

    public void setFoodTypeID(String foodTypeID) {
        FoodTypeID = foodTypeID;
    }

    public String getFoodTypeName() {
        return FoodTypeName;
    }

    public void setFoodTypeName(String foodTypeName) {
        FoodTypeName = foodTypeName;
    }
}
