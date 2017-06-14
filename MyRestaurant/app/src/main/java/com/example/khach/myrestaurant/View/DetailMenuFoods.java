package com.example.khach.myrestaurant.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.khach.myrestaurant.Entity.Menu;
import com.example.khach.myrestaurant.Presenter.MenuRestaurantImpl;
import com.example.khach.myrestaurant.Presenter.MenuRestaurantPresenter;
import com.example.khach.myrestaurant.R;
import com.example.khach.myrestaurant.myAdapter.CustomAdapterMenu;

import java.util.ArrayList;

/**
 * Created by khach on 09/06/2017.
 */

public class DetailMenuFoods extends Fragment implements  DetailMenuFoodsView {
    private ListView lvFoods;
    private MenuRestaurantPresenter menuRestaurantPresenter;
    //SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menu_restaurant,container,false);
        lvFoods =(ListView) rootView.findViewById(R.id.listFoods);
        menuRestaurantPresenter = new MenuRestaurantImpl(this);
        /*sharedPreferences = getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString(KEY_ID, "");*/
        Bundle bundle = getActivity().getIntent().getExtras();
        String id = bundle.getString("ID");
        menuRestaurantPresenter.getAllFoodOfRes(id);
        return  rootView;
    }

    @Override
    public void getAllFoodOfRes(ArrayList<Menu> listFoods) {
        //int size = listFoods.size();
        CustomAdapterMenu customAdapterMenu = new CustomAdapterMenu(listFoods,DetailMenuFoods.this.getActivity().getApplicationContext());
        lvFoods.setAdapter(customAdapterMenu);
    }
}
