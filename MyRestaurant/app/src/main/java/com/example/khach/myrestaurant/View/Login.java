package com.example.khach.myrestaurant.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khach.myrestaurant.R;

/**
 * Created by ThanhHai on 6/14/2017.
 */

public class Login extends AppCompatActivity{
    private TextView link_signup_user;
    private TextView link_signup_res;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AddControl();
       AddEvent();
    }

    private void AddControl() {
        link_signup_user = (TextView) findViewById(R.id.link_signup_user);
        link_signup_res = (TextView) findViewById(R.id.link_signup_res);
    }

    private void AddEvent() {
        link_signup_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });

        link_signup_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Signup_Restautant.class);
                startActivity(intent);
            }
        });
    }
}
