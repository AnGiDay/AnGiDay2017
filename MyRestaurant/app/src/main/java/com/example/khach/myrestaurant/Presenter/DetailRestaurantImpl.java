package com.example.khach.myrestaurant.Presenter;

import com.example.khach.myrestaurant.Entity.Account;
import com.example.khach.myrestaurant.Entity.Restaurant;
import com.example.khach.myrestaurant.View.DetailRestaurantView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by khach on 14/06/2017.
 */

public class DetailRestaurantImpl implements DetailRestairantPresenter {
    DatabaseReference myData;
    DetailRestaurantView detailRestaurantView;

    public DetailRestaurantImpl(DetailRestaurantView detailRestaurantView) {
        this.detailRestaurantView = detailRestaurantView;
    }

    @Override
    public void getInfoRestaurant(String id) {
        myData = FirebaseDatabase.getInstance().getReference();
        myData.child("Restaurant").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                    if (restaurant != null) {
                        detailRestaurantView.getDetailRestaurant(restaurant);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void getPhone(String id) {
        myData = FirebaseDatabase.getInstance().getReference();
        myData.child("Account").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Account account = dataSnapshot.getValue(Account.class);
                    if (account != null) {
                        detailRestaurantView.getPhone(account.getPhoneNo());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
