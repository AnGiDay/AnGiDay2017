package com.example.khach.myrestaurant.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khach.myrestaurant.Entity.Account;
import com.example.khach.myrestaurant.R;
import com.example.khach.myrestaurant.mySupport.MD5;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by ThanhHai on 6/14/2017.
 */

public class Login extends AppCompatActivity{
    private TextView link_signup_user;
    private TextView link_signup_res;
    private EditText edit_userID;
    private EditText edit_pass;
    private Button btn_login;

    private ArrayList<Account> listAccount=new ArrayList<Account>();
    private ArrayList<String> listAccountID=new ArrayList<String >();

    private DatabaseReference mData;

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
        edit_userID=(EditText)findViewById(R.id.edit_userID_login);
        edit_pass=(EditText)findViewById(R.id.edit_password_login);
        btn_login=(Button)findViewById(R.id.btn_login);
        mData= FirebaseDatabase.getInstance().getReference();
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

        mData.child("Account").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> data = dataSnapshot.getChildren();
                for (DataSnapshot child : data) {
                    Account account = child.getValue(Account.class);
                    listAccount.add(account);
                    listAccountID.add(account.getUserID());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit_userID.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, "Vui long điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else if(!listAccountID.contains(edit_userID.getText().toString())){
                    Toast.makeText(Login.this, "Tên đăng nhập không tồn tại", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    for (int i=0;i<listAccount.size();i++)
                    {
                        if(listAccount.get(i).getUserID().equals(edit_userID.getText().toString()))
                        {
                            if(listAccount.get(i).getPassword().equals(MD5.encryptMD5(edit_pass.getText().toString())))
                            {
                                Toast.makeText(Login.this, "Đang nhập thành công", Toast.LENGTH_SHORT).show();
                                // chuyển đi đến màn hình gì đó
                            }
                            else
                            {
                                Toast.makeText(Login.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
    }
}
