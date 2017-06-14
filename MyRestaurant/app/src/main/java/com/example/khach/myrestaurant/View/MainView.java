package com.example.khach.myrestaurant.View;

import com.example.khach.myrestaurant.Entity.FoodType;
import com.example.khach.myrestaurant.Entity.Restaurant;

import java.util.ArrayList;

/**
 * Created by khach on 14/06/2017.
 */

public interface MainView {
    void getAllRestaurant(ArrayList<Restaurant> listRestaurant);

    void getAllFoodTypes(ArrayList<FoodType> listRestaurant);
}
