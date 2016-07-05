package com.mqt.dripirrigationsystem.activity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.dialog.CustemDialog;

import java.sql.Time;
import java.util.Calendar;

/**
 * Created by Administrator on 2016/7/2.
 */
public class AddClockActivity extends AppCompatActivity implements View.OnClickListener {

    private TableRow tr_repeatTime;
    private TableRow tr_ring;
    private TableRow tr_hintContent;
    private Switch sw_vibrate;
    private Button bt_clockTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarLayout(R.layout.clock_actionbar,AddClockActivity.this);
        setContentView(R.layout.activity_add_clock);

        initView();
    }

    private void initView() {
        tr_repeatTime = (TableRow)findViewById(R.id.tr_repeat_clock_time);
        tr_repeatTime.setOnClickListener(this);
        tr_ring = (TableRow)findViewById(R.id.tr_ring_clock);
        tr_ring.setOnClickListener(this);
        tr_hintContent = (TableRow)findViewById(R.id.tr_hint_content_clock);
        tr_hintContent.setOnClickListener(this);
        sw_vibrate = (Switch)findViewById(R.id.sw_vibrate_clock);
        sw_vibrate.setOnClickListener(this);
        bt_clockTime = (Button)findViewById(R.id.bt_setting_add_clock_time);
        bt_clockTime.setOnClickListener(this);

    }

    public void setActionBarLayout(int layoutId, Context mContext) {
       ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

            LayoutInflater inflater = (LayoutInflater) this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(layoutId, new LinearLayout(mContext),
                    false);

            //获取自定义标题栏，并注册监听
            setView(v);

            ActionBar.LayoutParams layout = new ActionBar.LayoutParams
                    (ActionBar.LayoutParams.MATCH_PARENT,
                            ActionBar.LayoutParams.MATCH_PARENT);
            actionBar.setCustomView(v, layout);
            actionBar.setDisplayShowCustomEnabled(true);
            //设置自定义actionbar完全填充宽度
            Toolbar parent = (Toolbar) v.getParent();
            parent.setContentInsetsAbsolute(0, 0);
        }
    }

    private void setView(View v) {
        ImageView iv_back = (ImageView)v.findViewById(R.id.cancel_edit_clock);
        iv_back.setOnClickListener(this);
        ImageView iv_sure = (ImageView)v.findViewById(R.id.sure_edit_clock);
        iv_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_edit_clock:
                finish();
                Toast.makeText(AddClockActivity.this,"取消",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sure_edit_clock:

                Toast.makeText(AddClockActivity.this,"确定保存",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tr_repeat_clock_time:
                Toast.makeText(AddClockActivity.this,"重复时间",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tr_ring_clock:
                Toast.makeText(AddClockActivity.this,"铃声",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tr_hint_content_clock:
                Toast.makeText(AddClockActivity.this,"提醒内容",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sw_vibrate_clock:
                Toast.makeText(AddClockActivity.this,"振动",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_setting_add_clock_time:
               settingTime();
                break;

            default:

        }
    }

    /**
     * 设定时间
     */
    private void settingTime() {
        long time = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int munite = calendar.get(Calendar.MINUTE);
        new CustemDialog(AddClockActivity.this).createTimeDialog(hour,munite, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = "";
                if(hourOfDay<10){
                    time +="0";
                }
                time +=hourOfDay+":";
                if(minute<10){
                    time +="0";
                }
                time += minute;
                bt_clockTime.setText(time);
            }
        }).show();

        Toast.makeText(AddClockActivity.this,"设置时间",Toast.LENGTH_SHORT).show();
    }

}
