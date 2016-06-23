package com.mqt.dripirrigationsystem.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mqt.dripirrigationsystem.R;

/**
 * Created by Administrator on 2016/6/23.
 */
public class PortraitActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("个人信息编辑");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_edit_user);


    }
}
