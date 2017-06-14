package com.example.khach.myrestaurant.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.khach.myrestaurant.Entity.Restaurant;
import com.example.khach.myrestaurant.Presenter.DetailRestairantPresenter;
import com.example.khach.myrestaurant.Presenter.DetailRestaurantImpl;
import com.example.khach.myrestaurant.R;

/**
 * Created by khach on 08/06/2017.
 */

public class DetailRestaurant extends Fragment implements DetailRestaurantView {
    DetailRestairantPresenter detailRestairantPresenter;
    TextView txtName;
    TextView txtAdress;
    TextView txtPhone;
    RatingBar rdRating;
    Restaurant restaurant;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_restaurant, container, false);
        initView(rootView);

        Bundle bundle = getActivity().getIntent().getExtras();
        String id = bundle.getString("ID");
        detailRestairantPresenter.getInfoRestaurant(id);
        detailRestairantPresenter.getPhone(id);
        return rootView;
    }

    public void initView(View view) {
        txtAdress = (TextView) view.findViewById(R.id.txtDetail_Adress);
        txtName = (TextView) view.findViewById(R.id.txtDetail_ResName);
        rdRating = (RatingBar) view.findViewById(R.id.rdDetail_Rating);
        txtPhone = (TextView) view.findViewById(R.id.txtDetail_Phone);
        detailRestairantPresenter = new DetailRestaurantImpl(this);
    }
    @Override
    public void getDetailRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        txtName.setText(this.restaurant.getResName());
        txtAdress.setText(this.restaurant.getDescription());
        rdRating.setRating(restaurant.getAverageRating());
    }

    @Override
    public void getPhone(String phone) {
        if(phone!=null) {
            txtPhone.setText(phone);
        }
    }
}
