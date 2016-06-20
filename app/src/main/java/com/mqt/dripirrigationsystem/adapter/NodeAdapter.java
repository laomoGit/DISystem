package com.mqt.dripirrigationsystem.adapter;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.domain.Node;


import java.util.ArrayList;


/**
 * Created by Administrator on 2016/6/20.
 */
public class NodeAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<Node> data = new ArrayList<Node>();


    public NodeAdapter(Context context, ArrayList<Node>data){
        this.mContext = context;
        this.data = (ArrayList<Node>) data.clone();
    }

    @Override
    public int getCount() {
        //阀门个数
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        //获取当前阀门实例
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.node_item,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_nodeTitle = (TextView)view.findViewById(R.id.node_name);
            viewHolder.sc_nodeStatus = (Switch)view.findViewById(R.id.node_status);
            view.setTag(viewHolder);//将viewHolder存储在view中

        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_nodeTitle.setText(data.get(position).getValveName());
        viewHolder.sc_nodeStatus.setChecked(data.get(position).getStatus());
        return view;
    }

    //用来存储控件实例，进一步优化
    private class ViewHolder {
        TextView tv_nodeTitle;
        Switch sc_nodeStatus;
    }
}
