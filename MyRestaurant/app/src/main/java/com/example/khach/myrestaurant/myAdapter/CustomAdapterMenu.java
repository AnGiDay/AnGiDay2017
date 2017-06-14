package com.example.khach.myrestaurant.myAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.khach.myrestaurant.Entity.Menu;
import com.example.khach.myrestaurant.R;

import java.util.ArrayList;

/**
 * Created by khach on 22/05/2017.
 */

public class CustomAdapterMenu
        extends ArrayAdapter<Menu> {

    private ArrayList<Menu> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView txtName;
        TextView txtFee;
        TextView txtRate;
    }

    public CustomAdapterMenu(ArrayList<Menu> data, Context context) {
        super(context, R.layout.menu_res_row, data);
        this.dataSet = data;
        this.mContext = context;
    }

    public int getCount() {
        return dataSet.size();
    }

    private int lastPosition = -1;

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result;
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.menu_res_row, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtFoodName);
            viewHolder.txtFee = (TextView) convertView.findViewById(R.id.txtFoodFee);
            viewHolder.txtRate = (TextView) convertView.findViewById(R.id.txtRateFood);
            convertView.setTag(viewHolder);
            result = convertView;
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;
        Menu dataModel = getItem(position);
        viewHolder.txtName.setText(dataModel.getFoodName());
        viewHolder.txtFee.setText(dataModel.getPrice());
        viewHolder.txtRate.setText(String.valueOf(dataModel.getRating()));
        return result;
    }
}
