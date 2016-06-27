package com.mqt.dripirrigationsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.adapter.MenuItemAdapter;
import com.mqt.dripirrigationsystem.adapter.NodeAdapter;
import com.mqt.dripirrigationsystem.domain.LvMenuItem;
import com.mqt.dripirrigationsystem.domain.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 */
public class NodeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private GridView mGridView;
    private NodeAdapter nodeAdapter;
    private ArrayList<Node> data;

    private DrawerLayout mDrawerLayout;
    private ListView mLvLeftMenu;
    private List<LvMenuItem> items;
    private LvMenuItem item;

    private TextView tv_username;
    private ImageView iv_userportrait;

    private Intent mIntent;
    private boolean closeMenu = false;
    private static final int USER_EDIT_CODE = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node);
        mGridView = (GridView)findViewById(R.id.gridView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
        mLvLeftMenu = (ListView) findViewById(R.id.id_lv_left_menu);
        initNavigation();
        setUpDrawer();

        //模拟一些数据
        //iniData();
       /* nodeAdapter = new NodeAdapter(this,data);
        mGridView.setAdapter(nodeAdapter);
        //监听
        mGridView.setOnItemClickListener(this);
        nodeAdapter.notifyDataSetChanged();*/

    }

    private void setUpDrawer() {
        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout headerLayout = (RelativeLayout) inflater.inflate(R.layout.header_just_username,mLvLeftMenu,false);
        tv_username = (TextView)headerLayout.findViewById(R.id.id_tv_username);
        tv_username.setOnClickListener(this);
        iv_userportrait = (ImageView)headerLayout.findViewById(R.id.id_iv_user_portrait);
        iv_userportrait.setOnClickListener(this);
        mLvLeftMenu.addHeaderView(headerLayout);


        //创建menu的item
        items = new ArrayList<LvMenuItem>(
                Arrays.asList(
                        new LvMenuItem("气象数据",R.drawable.collections_cloud),
                        new LvMenuItem("设置",R.drawable.action_settings),
                        new LvMenuItem("设置闹钟",R.drawable.access_alarms),
                        new LvMenuItem("拍照存档",R.drawable.access_camera),
                        new LvMenuItem("监控视频",R.drawable.access_video),
                        new LvMenuItem("帮助与反馈",R.drawable.action_help),
                        new LvMenuItem("退出应用",R.drawable.navigation_cancel)
                )
        );

        mLvLeftMenu.setAdapter(new MenuItemAdapter(NodeActivity.this,items));
        //左侧菜单listView 的点击事件
        mLvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    return;
                }
               item = items.get(position-1);

                switch(item.getIcon()){
                    case R.drawable.collections_cloud:
                        mIntent = new Intent(NodeActivity.this,WeatherActivity.class);
                        startActivity(mIntent);
                        break;
                    case R.drawable.action_settings:
                        mIntent = new Intent(NodeActivity.this,SettingActivity.class);
                        startActivity(mIntent);
                        break;
                    case R.drawable.action_help:
                        mIntent = new Intent(NodeActivity.this,HelpActivity.class);
                        startActivity(mIntent);
                        break;
                    case R.drawable.navigation_cancel:
                        finish();
                        break;
                    default:
                        break;
                }

            }
        });

    }


    private void initNavigation() {
        /*Toolbar toolbar = (Toolbar)findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);*/

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

   /*private void iniData() {
        data = new ArrayList<Node>();
        Node node = new Node();
        node.setSysId(1);
        node.setValveName("0001-A");
        node.setUsePattern(true);
        node.setPressure(20.0);
        node.setStatus(false);
        node.setAddress("beijing");
        node.setNumber("20160620");
        node.setSensorH1Value(30.0);
        node.setSensorH2Value(31.0);
        node.setSensorT1Value(25.4);
        node.setSensorT2Value(28.9);

        data.add(node);
        node = null;
        node = new Node();

        node.setSysId(2);
        node.setValveName("0002-A");
        node.setUsePattern(true);
        node.setPressure(20.0);
        node.setStatus(true);
        node.setAddress("beijing");
        node.setNumber("20160621");
        node.setSensorH1Value(30.0);
        node.setSensorH2Value(31.0);
        node.setSensorT1Value(25.4);
        node.setSensorT2Value(28.9);

        data.add(node);
        node = null;
        node = new Node();

        node.setSysId(3);
        node.setValveName("0003-A");
        node.setUsePattern(true);
        node.setPressure(20.0);
        node.setStatus(true);
        node.setAddress("beijing");
        node.setNumber("20160621");
        node.setSensorH1Value(29.0);
        node.setSensorH2Value(34.0);
        node.setSensorT1Value(26.4);
        node.setSensorT2Value(28.9);

        data.add(node);
        node = null;
        node = new Node();
        node.setSysId(4);
        node.setValveName("0004-A");
        node.setUsePattern(true);
        node.setPressure(20.0);
        node.setStatus(true);
        node.setAddress("beijing");
        node.setNumber("20160621");
        node.setSensorH1Value(29.0);
        node.setSensorH2Value(34.0);
        node.setSensorT1Value(26.4);
        node.setSensorT2Value(28.9);

        data.add(node);
        node = null;
        node = new Node();
        node.setSysId(5);
        node.setValveName("0005-A");
        node.setUsePattern(true);
        node.setPressure(20.0);
        node.setStatus(false);
        node.setAddress("beijing");
        node.setNumber("20160621");
        node.setSensorH1Value(29.0);
        node.setSensorH2Value(34.0);
        node.setSensorT1Value(26.4);
        node.setSensorT2Value(28.9);

        data.add(node);
        node = null;
        node = new Node();
        node.setSysId(6);
        node.setValveName("0006-B");
        node.setUsePattern(true);
        node.setPressure(20.0);
        node.setStatus(true);
        node.setAddress("beijing");
        node.setNumber("20160621");
        node.setSensorH1Value(29.0);
        node.setSensorH2Value(34.0);
        node.setSensorT1Value(26.4);
        node.setSensorT2Value(28.9);

        data.add(node);
    }*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //响应点击事件
        Node node = data.get(position);
        mIntent = new Intent();
        mIntent.setClass(this,NodeDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("node",node);
        mIntent.putExtras(bundle);
        startActivity(mIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            if(!closeMenu){
                mDrawerLayout.openDrawer(GravityCompat.START);
                closeMenu = true;
            }else{
                mDrawerLayout.closeDrawers();
                closeMenu = false;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_iv_user_portrait:
            case R.id.id_tv_username:
                mIntent = new Intent(NodeActivity.this,PortraitActivity.class);
                //Bundle bundle = new Bundle();
                //bundle.putSerializable("userPortrait", (Serializable) getResources().getDrawable(R.drawable.user_portrait));
                startActivityForResult(mIntent,USER_EDIT_CODE);
                break;
            default:
                break;
        }
    }
}
