package com.example.khach.myrestaurant.Presenter;

import com.example.khach.myrestaurant.Entity.Menu;
import com.example.khach.myrestaurant.View.DetailMenuFoodsView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by khach on 15/06/2017.
 */

public class MenuRestaurantImpl implements MenuRestaurantPresenter {
    DatabaseReference myData;
    DetailMenuFoodsView detailMenuFoodsView;
    public MenuRestaurantImpl(DetailMenuFoodsView detailMenuFoodsView){
        this.detailMenuFoodsView = detailMenuFoodsView;
    }
    @Override
    public void getAllFoodOfRes(String id) {
        final String res_id = id;
        myData = FirebaseDatabase.getInstance().getReference();
        myData.child("Menu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    ArrayList<Menu> listMenus = new ArrayList<Menu>();
                    Iterable<DataSnapshot> snap = dataSnapshot.getChildren();
                    for(DataSnapshot child : snap){
                        if(child.getKey().contains(res_id)){
                            Menu menu = child.getValue(Menu.class);
                            listMenus.add(menu);
                        }
                    }
                    if(listMenus.size()>0){
                        detailMenuFoodsView.getAllFoodOfRes(listMenus);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
