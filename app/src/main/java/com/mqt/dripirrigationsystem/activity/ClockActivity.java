package com.mqt.dripirrigationsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.adapter.ClockAdapter;
import com.mqt.dripirrigationsystem.domain.Clock;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/1.
 */
public class ClockActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private ListView lv_clock;

    private ClockAdapter clockAdapter;
    private ArrayList<Clock> clocks;

    public static final int CLOCK_ADD_CODE = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("设置闹钟");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_clock);


        //模拟数据
        initData();
        lv_clock = (ListView)findViewById(R.id.lv_alarm_clock);
        clockAdapter = new ClockAdapter(ClockActivity.this,clocks);
        lv_clock.setOnItemClickListener(this);
        lv_clock.setOnItemLongClickListener(this);
        lv_clock.setAdapter(clockAdapter);
        clockAdapter.notifyDataSetChanged();

    }

    private void initData() {
        clocks = new ArrayList<Clock>();
        Clock clock = new Clock();
        clock.setClock(true);
        clock.setClockContent("关闭001-A");
        clock.setClockSpace("12时30分后响铃");
        clock.setClockTime("12:15");
        clocks.add(clock);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_add_clock:
                Intent intent = new Intent(ClockActivity.this,AddClockActivity.class);
                startActivityForResult(intent,CLOCK_ADD_CODE);
                break;
            case R.id.menu_setting_clock:

                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // Intent intent = new Intent(ClockActivity.this,)
        Toast.makeText(ClockActivity.this,"点击",Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.clock_menu,menu);
        return true;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ClockActivity.this,"长按",Toast.LENGTH_SHORT).show();
        return true;
    }
}
