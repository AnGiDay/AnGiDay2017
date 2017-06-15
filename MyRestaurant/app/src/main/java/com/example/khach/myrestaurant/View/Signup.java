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
import com.example.khach.myrestaurant.Entity.User;
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

public class Signup extends AppCompatActivity implements Signup_User_View {
    private TextView link_login;
    private Button btn_Signup;
    private EditText edit_userID;
    private EditText edit_userName;
    private EditText edit_phone;
    private EditText edit_pass;
    private EditText edit_rePass;

    private DatabaseReference mData;
    ArrayList<String> lisAccountID = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        AddControl();
        AddEvent();
    }

    @Override
    public void AddControl() {
        link_login=(TextView)findViewById(R.id.link_login_user);
        btn_Signup=(Button)findViewById(R.id.btn_signup_user);
        edit_userID=(EditText)findViewById(R.id.edit_userID);
        edit_userName=(EditText)findViewById(R.id.edit_username);
        edit_phone=(EditText)findViewById(R.id.edit_phoneNo_user);
        edit_pass=(EditText)findViewById(R.id.edit_password_signup);
        edit_rePass=(EditText)findViewById(R.id.edit_reEnterPassword_signup);

        mData= FirebaseDatabase.getInstance().getReference();
    }


    @Override
    public void AddEvent() {
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
                if(validate()) {
                    try {
                        Account account = new Account(edit_userID.getText().toString(), edit_userName.getText().toString(), MD5.encryptMD5(edit_pass.getText().toString()), edit_phone.getText().toString());
                        mData.child("Account").child(account.getUserID()).setValue(account);

                        User user = new User(edit_userID.getText().toString(), edit_userName.getText().toString());
                        mData.child("User").child(user.getUserID()).setValue(user);
                        Toast.makeText(Signup.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception ex)
                    {
                        Toast.makeText(Signup.this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(Signup.this, "Thông tin không hợp lệ. Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mData.child("Account").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> data = dataSnapshot.getChildren();
                for (DataSnapshot child : data) {
                    Account account = child.getValue(Account.class);
                    lisAccountID.add(account.getUserID());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean validate() {
        boolean valid = true;

        String UserID = edit_userID.getText().toString();
        String UserName = edit_userName.getText().toString();
        String Phone = edit_phone.getText().toString();
        String Pass = edit_pass.getText().toString();
        String RePass = edit_rePass.getText().toString();

        if (UserID.isEmpty() || UserID.length() < 3) {
            edit_userID.setError("Tên đăng nhập tối thiểu 3 ký tự");
            valid = false;
        } else if (lisAccountID.contains(UserID)) {
            edit_userID.setError("Tên đăng nhập đã tồn tại.");
            valid = false;
        } else {
            edit_userID.setError(null);
        }

        if (UserName.isEmpty()) {
            edit_userName.setError("Vui lòng nhập tên người dùng");
            valid = false;
        } else {
            edit_userName.setError(null);
        }


        if (Phone.isEmpty() || Phone.length()<10 || Phone.length()>11) {
            edit_phone.setError("Vui lòng nhập chính xác số điện thoại");
            valid = false;
        } else {
            edit_phone.setError(null);
        }

        if (Pass.isEmpty() || Pass.length() < 4 ) {
            edit_pass.setError("Mật khẩu tối thiểu 4 ký tự");
            valid = false;
        } else {
            edit_pass.setError(null);
        }

        if (RePass.isEmpty() || RePass.length() < 4 || !(RePass.equals(Pass))) {
            edit_rePass.setError("Mật khẩu không khớp");
            valid = false;
        } else {
            edit_rePass.setError(null);
        }

        return valid;
    }
}

