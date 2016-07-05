package com.mqt.dripirrigationsystem.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.mqt.dripirrigationsystem.R;

/**
 * Created by Administrator on 2016/6/23.
 */
public class WeatherActivity extends AppCompatActivity{
    private TextView tv_weatherId;
    private TextView tv_airTemp;
    private TextView tv_airHum;
    private TextView tv_airPressure;
    private TextView tv_airSpeed;
    private TextView tv_illumination;
    private TextView tv_radiate;
    private TextView tv_recvTime;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("气象站数据");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_weather);
        initView();
    }

    private void initView() {
        tv_weatherId = (TextView)findViewById(R.id.tv_number);
        tv_airTemp = (TextView)findViewById(R.id.tv_temper);
        tv_airHum = (TextView)findViewById(R.id.tv_humidity);
        tv_airPressure = (TextView)findViewById(R.id.tv_airpressure);
        tv_airSpeed = (TextView)findViewById(R.id.tv_airspeed);
        tv_illumination = (TextView)findViewById(R.id.tv_illumination);
        tv_radiate = (TextView)findViewById(R.id.tv_radiate);
        tv_recvTime = (TextView)findViewById(R.id.tv_recvTime);

        tv_weatherId.setText("13543839525");
        tv_airTemp.setText("23℃");
        tv_airHum.setText("67%");
        tv_airPressure.setText("340");
        tv_airSpeed.setText("2");
        tv_illumination.setText("3");
        tv_radiate.setText("0.0");
        tv_recvTime.setText("2016-07-01 11:20:30");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
