package com.example.khach.myrestaurant.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khach.myrestaurant.Entity.Restaurant;
import com.example.khach.myrestaurant.Presenter.UpdateSpaceImpl;
import com.example.khach.myrestaurant.Presenter.UpdateSpacePresenter;
import com.example.khach.myrestaurant.R;

import static com.example.khach.myrestaurant.myAdapter.ShareReference.KEY_ID;
import static com.example.khach.myrestaurant.myAdapter.ShareReference.PREF_NAME;

/**
 * Created by khach on 10/06/2017.
 */

public class Update_FreeSpace extends ActionBarActivity implements UpdateSpaceView {
    private RadioButton rdOpen, rdClose;
    private EditText edtNSpace;
    private Button btnUpdate;
    private String id;
    private TextView name;
    private TextView adress;
    private RatingBar rating;
    private SharedPreferences sharedPreferences;
    private UpdateSpacePresenter updateSpacePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_space_table);
        initView();
		updateSpacePresenter.getRestaurant(id);
        rdOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNSpace.setVisibility(View.VISIBLE);
            }
        });
        rdClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNSpace.setVisibility(View.GONE);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int newSpace;
                    if (rdClose.isChecked()) {
                        newSpace = 0;
                    } else {
                        newSpace = Integer.parseInt(edtNSpace.getText().toString());
                    }
                    updateSpacePresenter.updateFreeSpace(id, newSpace);
                } catch (Throwable e) {
                    Toast.makeText(Update_FreeSpace.this, "Cập nhật trạng thái thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void initView() {
        edtNSpace = (EditText) findViewById(R.id.edtNoSpace);
        rdClose = (RadioButton) findViewById(R.id.rdStateClose);
        rdOpen = (RadioButton) findViewById(R.id.rdStateFree);
        btnUpdate = (Button) findViewById(R.id.btnUpdateSpace);
        name = (TextView) findViewById(R.id.txtUpdateSpace_NameRes);
        adress = (TextView) findViewById(R.id.txtUpdateSpace_AdresRes);
        rating = (RatingBar) findViewById(R.id.rd_UpdateSpace_Rating);
        updateSpacePresenter = new UpdateSpaceImpl(this);
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        id = sharedPreferences.getString(KEY_ID, "");
    }

    @Override
    public void inputSpaceBiggerMaxSpace() {
        Toast.makeText(Update_FreeSpace.this, "Số lượng nhập vào lớn hơn số chổ tối đa", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateSuccess() {
        Toast.makeText(Update_FreeSpace.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getRestaurant(Restaurant restaurant) {
        name.setText(restaurant.getResName());
        rating.setRating(restaurant.getAverageRating());
        adress.setText(restaurant.getDescription());
    }
}

