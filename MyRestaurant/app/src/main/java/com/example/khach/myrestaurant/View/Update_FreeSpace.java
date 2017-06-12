package com.example.khach.myrestaurant.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.khach.myrestaurant.R;

/**
 * Created by khach on 10/06/2017.
 */

public class Update_FreeSpace extends ActionBarActivity {
    private RadioButton rdOpen, rdClose;
    private EditText edtNSpace;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_space_table);
        edtNSpace = (EditText)  findViewById(R.id.edtNoSpace);
        rdClose = (RadioButton) findViewById(R.id.rdStateClose);
        rdOpen = (RadioButton) findViewById(R.id.rdStateFree);
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
    }
}

