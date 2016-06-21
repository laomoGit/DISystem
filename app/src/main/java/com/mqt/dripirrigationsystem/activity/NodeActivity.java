package com.mqt.dripirrigationsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.adapter.NodeAdapter;
import com.mqt.dripirrigationsystem.domain.Node;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/20.
 */
public class NodeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private GridView mGridView;
    private NodeAdapter nodeAdapter;
    private ArrayList<Node> data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node);
        mGridView = (GridView)findViewById(R.id.gridView);
        //模拟一些数据
        iniData();
        nodeAdapter = new NodeAdapter(this,data);
        mGridView.setAdapter(nodeAdapter);
        //监听
        mGridView.setOnItemClickListener(this);
        nodeAdapter.notifyDataSetChanged();

    }

    private void iniData() {
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
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //响应点击事件
        Node node = data.get(position);
        Intent nodeDetail = new Intent();
        nodeDetail.setClass(this,NodeDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("node",node);
        nodeDetail.putExtras(bundle);
        startActivity(nodeDetail);
    }
}
