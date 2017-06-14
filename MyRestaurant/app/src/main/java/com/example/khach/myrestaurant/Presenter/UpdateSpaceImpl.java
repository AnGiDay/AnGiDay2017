package com.example.khach.myrestaurant.Presenter;

import com.example.khach.myrestaurant.Entity.Restaurant;
import com.example.khach.myrestaurant.View.UpdateSpaceView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by khach on 12/06/2017.
 */

public class UpdateSpaceImpl implements UpdateSpacePresenter {

    UpdateSpaceView updateSpaceView;
    DatabaseReference myData = FirebaseDatabase.getInstance().getReference();
    ;

    public UpdateSpaceImpl(UpdateSpaceView updateSpaceView) {
        this.updateSpaceView = updateSpaceView;
    }

    @Override
    public void updateFreeSpace(final String ResID, final int newSpace) {
        myData.child("Restaurant").child(ResID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                    if (restaurant.getTotalSpace() < newSpace) {
                        updateSpaceView.inputSpaceBiggerMaxSpace();
                    } else {
                        myData.child("Restaurant").child(ResID).child("FreeSpace").setValue(newSpace);
                        updateSpaceView.updateSuccess();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void getRestaurant(String ResID) {
        myData.child("Restaurant").child(ResID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                    updateSpaceView.getRestaurant(restaurant);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
