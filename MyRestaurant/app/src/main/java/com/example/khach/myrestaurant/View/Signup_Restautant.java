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
import com.example.khach.myrestaurant.Entity.Restaurant;
import com.example.khach.myrestaurant.Entity.User;
import com.example.khach.myrestaurant.R;
import com.example.khach.myrestaurant.mySupport.MD5;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.khach.myrestaurant.R.id.edit_userID;

/**
 * Created by ThanhHai on 6/14/2017.
 */

public class Signup_Restautant extends AppCompatActivity implements Signup_Res_View {
    private TextView link_login;
    private Button btn_signup_res;
    private EditText edit_resID;
    private EditText edit_resName;
    private EditText edit_phone;
    private EditText edit_totalSpace;
    private EditText edit_pass;
    private EditText edit_rePass;
    private EditText edit_Address;

    private Button btn_location_signup;

    private DatabaseReference mData;

    private float latitude=0;
    private float longitude=0;

    ArrayList<String> lisAccountID = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup_restaurant);

        AddControl();
        AddEvent();
    }

    @Override
    public void AddControl() {
        link_login=(TextView)findViewById(R.id.link_login_res);
        btn_signup_res=(Button)findViewById(R.id.btn_signup_res);
        edit_resID=(EditText)findViewById(R.id.edit_resID_signup);
        edit_resName=(EditText)findViewById(R.id.edit_resname_signup);
        edit_phone=(EditText)findViewById(R.id.edit_phoneNo_res);
        edit_totalSpace=(EditText)findViewById(R.id.edit_totalSpace);
        edit_pass=(EditText)findViewById(R.id.edit_password_res);
        edit_rePass=(EditText)findViewById(R.id.edit_reEnterPassword_res);
        edit_Address=(EditText)findViewById(R.id.edit_Des_res);

        btn_location_signup=(Button)findViewById(R.id.btn_location_signup);

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

        btn_signup_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {
                    try {
                        Account account = new Account(edit_resID.getText().toString(), edit_resName.getText().toString(), MD5.encryptMD5(edit_pass.getText().toString()), edit_phone.getText().toString());
                        mData.child("Account").child(account.getUserID()).setValue(account);

                        Restaurant restaurant = new Restaurant(edit_resID.getText().toString(), edit_resName.getText().toString(), latitude,longitude, Integer.getInteger(edit_totalSpace.getText().toString()), 0, "", 0, 0, edit_Address.getText().toString());
                        mData.child("Restaurant").child(restaurant.getResID()).setValue(restaurant);
                        Toast.makeText(Signup_Restautant.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception ex)
                    {
                        Toast.makeText(Signup_Restautant.this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(Signup_Restautant.this, "Thông tin không hợp lệ. Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
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

        btn_location_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get LatLng here
            }
        });
    }

    @Override
    public boolean validate() {
        boolean valid = true;

        String ResID = edit_resID.getText().toString();
        String ResName = edit_resName.getText().toString();
        String Phone = edit_phone.getText().toString();
        int TotalSpace=Integer.getInteger(edit_totalSpace.getText().toString());
        String Pass = edit_pass.getText().toString();
        String RePass = edit_rePass.getText().toString();
        String Address=edit_Address.getText().toString();


        if (ResID.isEmpty() || ResID.length() < 3) {
            edit_resID.setError("Tên đăng nhập tối thiểu 3 ký tự");
            valid = false;
        } else if (lisAccountID.contains(ResID)) {
            edit_resID.setError("Tên đăng nhập đã tồn tại.");
            valid = false;
        } else {
            edit_resID.setError(null);
        }

        if (ResName.isEmpty()) {
            edit_resName.setError("Vui lòng nhập tên người dùng");
            valid = false;
        } else {
            edit_resName.setError(null);
        }

        if (Address.isEmpty()) {
            edit_Address.setError("Vui lòng nhập địa chỉ");
            valid = false;
        } else {
            edit_Address.setError(null);
        }

        if (Phone.isEmpty() || Phone.length()<10 || Phone.length()>11) {
            edit_phone.setError("Vui lòng nhập chính xác số điện thoại");
            valid = false;
        } else {
            edit_phone.setError(null);
        }

        if (TotalSpace<0) {
            edit_totalSpace.setError("Số chỗ ngồi không hợp lệ.");
            valid = false;
        } else {
            edit_totalSpace.setError(null);
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
