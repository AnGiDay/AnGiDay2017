package com.example.khach.myrestaurant.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.khach.myrestaurant.Entity.Account;
import com.example.khach.myrestaurant.Entity.User;
import com.example.khach.myrestaurant.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by ThanhHai on 6/14/2017.
 */

public class Signup extends AppCompatActivity {
    private TextView link_login;
    private Button btn_Signup;

    private DatabaseReference mData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        AddControl();
        AddEvent();
    }

    private void AddControl() {
        link_login=(TextView)findViewById(R.id.link_login_user);
        btn_Signup=(Button)findViewById(R.id.btn_signup_user);

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

        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check ivald
                Account account=new Account("thanhhai","user","123","0987654213");
                mData.child("Account").child(account.getUserID()).setValue(account);

                User user=new User("thanhhai","Thanh Hải");
                mData.child("User").child(user.getUserID()).setValue(user);
            }
        });


    }
}

