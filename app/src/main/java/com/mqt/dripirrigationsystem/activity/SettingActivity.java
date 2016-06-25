package com.mqt.dripirrigationsystem.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Switch;

import com.mqt.dripirrigationsystem.R;

/**
 * Created by Administrator on 2016/6/23.
 */
public class SettingActivity extends AppCompatActivity{
    private Switch s_refresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("设置");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.setting_layout);

        s_refresh = (Switch)findViewById(R.id.switch_refresh_button);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
