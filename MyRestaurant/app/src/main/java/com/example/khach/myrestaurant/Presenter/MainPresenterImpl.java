package com.example.khach.myrestaurant.Presenter;

import com.example.khach.myrestaurant.Entity.FoodType;
import com.example.khach.myrestaurant.Entity.Restaurant;
import com.example.khach.myrestaurant.View.MainView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by khach on 14/06/2017.
 */

public class MainPresenterImpl implements MainPresenter {
    DatabaseReference myData = FirebaseDatabase.getInstance().getReference();
    MainView mainView;
    public  MainPresenterImpl(MainView mainView){
        this.mainView = mainView;
    }
    @Override
    public void getAllRestaurant() {
        myData.child("Restaurant").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    ArrayList<Restaurant> listRestaurant = new ArrayList<Restaurant>();
                    Iterable<DataSnapshot> listData = dataSnapshot.getChildren();
                    for(DataSnapshot child : listData){
                        Restaurant restaurant = child.getValue(Restaurant.class);
                        listRestaurant.add(restaurant);
                    }
                    if(listRestaurant.size()>0){
                        mainView.getAllRestaurant(listRestaurant);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void getAllFoodType() {
        myData.child("FoodType").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    ArrayList<FoodType> listFoodTypes = new ArrayList<FoodType>();
                    Iterable<DataSnapshot> listData = dataSnapshot.getChildren();
                    for(DataSnapshot child : listData){
                        FoodType foodType = child.getValue(FoodType.class);
                        listFoodTypes.add(foodType);
                    }
                    if(listFoodTypes.size()>0){
                        mainView.getAllFoodTypes(listFoodTypes);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
