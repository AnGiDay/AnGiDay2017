package com.example.khach.myrestaurant.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.khach.myrestaurant.Entity.Account;
import com.example.khach.myrestaurant.Entity.Restaurant;
import com.example.khach.myrestaurant.Entity.User;
import com.example.khach.myrestaurant.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ThanhHai on 6/14/2017.
 */

public class Signup_Restautant extends AppCompatActivity {
    private TextView link_login;
    private Button btn_signup_res;
    private DatabaseReference mData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup_restaurant);

        AddControl();
        AddEvent();
    }

    private void AddControl() {
        link_login=(TextView)findViewById(R.id.link_login_res);
        btn_signup_res=(Button)findViewById(R.id.btn_signup_res);
        mData= FirebaseDatabase.getInstance().getReference();
    }

    private void AddEvent() {
        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        btn_signup_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account=new Account();
                mData.child("Account").child(account.getUserID()).setValue(account);

                Restaurant restaurant=new Restaurant();
                mData.child("User").child(restaurant.getResID()).setValue(restaurant);
            }
        });
    }
}
