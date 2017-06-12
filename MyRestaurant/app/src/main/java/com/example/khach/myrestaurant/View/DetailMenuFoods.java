package com.example.khach.myrestaurant.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.khach.myrestaurant.myAdapter.CustomAdapterMenu;
import com.example.khach.myrestaurant.Entity.Food;
import com.example.khach.myrestaurant.R;

import java.util.ArrayList;

/**
 * Created by khach on 09/06/2017.
 */

public class DetailMenuFoods extends Fragment {
    private ListView lvFoods;
    private ArrayList<Food> mListFoods = new ArrayList<Food>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menu_restaurant,container,false);
        lvFoods =(ListView) rootView.findViewById(R.id.listFoods);
        doCreateFakeData();
        CustomAdapterMenu customAdapterMenu = new CustomAdapterMenu(mListFoods,DetailMenuFoods.this.getActivity().getApplicationContext());
        lvFoods.setAdapter(customAdapterMenu);
        return  rootView;
    }
    private  void doCreateFakeData(){
        Food food1 = new Food();
        food1.setName("Rau Muống Xào Tỏi");
        food1.setFee("69.000 đồng");
        food1.setRate(7);

        Food food2 = new Food();
        food2.setName("Đậu Hủ Chiên Giòn");
        food2.setFee("69.000 đồng");
        food2.setRate(4);
        Food food3 = new Food();
        food3.setName("Kho Quet Chấm Mắm Ruốt");
        food3.setFee("69.000 đồng");
        food3.setRate(5);

        Food food4 = new Food();
        food4.setName("Cơm Chiên Hải Sản");
        food4.setFee("69.000 đồng");
        food4.setRate(6);
        Food food8 = new Food();
        food8.setName("Rau Muống Xào Tỏi");
        food8.setFee("69.000 đồng");
        food8.setRate(2);

        Food food6 = new Food();
        food6.setName("Đậu Hủ Chiên Giòn");
        food6.setFee("69.000 đồng");
        food6.setRate(5);
        Food food5 = new Food();
        food5.setName("Kho Quet Chấm Mắm Ruốt");
        food5.setFee("69.000 đồng");
        food5.setRate(7);

        Food food7 = new Food();
        food7.setName("Cơm Chiên Hải Sản");
        food7.setFee("69.000 đồng");
        food7.setRate(7);
        mListFoods.add(food1);
        mListFoods.add(food2);
        mListFoods.add(food3);
        mListFoods.add(food4);
        mListFoods.add(food5);
        mListFoods.add(food6);
        mListFoods.add(food7);
        mListFoods.add(food8);
    }

}
