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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.adapter.MenuItemAdapter;
import com.mqt.dripirrigationsystem.adapter.NodeAdapter;
import com.mqt.dripirrigationsystem.domain.LvMenuItem;
import com.mqt.dripirrigationsystem.domain.Node;
import com.mqt.dripirrigationsystem.interfac.OnUIRequestCallback;
import com.mqt.dripirrigationsystem.manager.NodeManager;
import com.mqt.dripirrigationsystem.service.NodeService;

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

    private int userId;

    private static final int USER_EDIT_CODE = 0;
    private static final String NODE_URL = "http://192.168.155.1:8080/DripIrrigationSystem/node";

    private NodeService nodeService;
    private ProgressBar pb;
    private OnUIRequestCallback callback = new OnUIRequestCallback() {
        @Override
        public void onUIRequestStart() {
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        public void onUIRequestSuccess(String res) {
            pb.setVisibility(View.GONE);
            updateData();
        }

        @Override
        public void onUIRequestError(Exception e) {
            pb.setVisibility(View.GONE);
            Toast.makeText(NodeActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node);
        userId = getIntent().getIntExtra("userId",0);

        mGridView = (GridView)findViewById(R.id.gridView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
        mLvLeftMenu = (ListView) findViewById(R.id.id_lv_left_menu);
        pb = (ProgressBar)findViewById(R.id.loading_process_dialog_progressBar);


        //是否返回有数据
        if(isInitData()){
            updateData();
        }
        nodeService = new NodeService();
        if(NodeManager.getInstance().getNodeCount()<=0){
            nodeService.sendRequest(NodeActivity.this,callback,NODE_URL,"GET","userid="+userId);
        }

       // nodeService.sendRequest(NodeActivity.this,callback,NODE_URL,"GET","userid="+userId);
        initNavigation();
        setUpDrawer();
    }

    private void updateData() {

        nodeAdapter = new NodeAdapter(this,NodeManager.getInstance().getNodes());
        mGridView.setAdapter(nodeAdapter);
        //监听
        mGridView.setOnItemClickListener(this);
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
                    case R.drawable.access_alarms:
                        mIntent = new Intent(NodeActivity.this,ClockActivity.class);
                        startActivity(mIntent);
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

   private boolean isInitData() {
       return NodeManager.getInstance().getNodeCount()>0?true:false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //响应点击事件
        Node node = NodeManager.getInstance().getNode(position);
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
