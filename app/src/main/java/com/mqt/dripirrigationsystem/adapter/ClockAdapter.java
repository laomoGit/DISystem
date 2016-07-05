package com.mqt.dripirrigationsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.domain.Clock;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/1.
 */
public class ClockAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<Clock> data = new ArrayList<Clock>();

    public ClockAdapter(Context context,ArrayList<Clock> data){
        this.mContext = context;
        this.data = (ArrayList<Clock>) data.clone();
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.clock_item,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_clocktime = (TextView)view.findViewById(R.id.tv_clock_time);
            viewHolder.sw_launch = (Switch)view.findViewById(R.id.sw_launch_clock);
            viewHolder.tv_content = (TextView)view.findViewById(R.id.tv_content_clock);
            viewHolder.tv_space = (TextView)view.findViewById(R.id.tv_space_time);
            view.setTag(viewHolder);//将viewHolder存储在view中

        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_clocktime.setText(data.get(position).getClockTime());
        viewHolder.tv_content.setText(data.get(position).getClockContent());
        viewHolder.tv_space.setText(data.get(position).getClockSpace());
        viewHolder.sw_launch.setChecked(data.get(position).isClock());
        return view;
    }



    //用来存储控件实例，进一步优化
    private class ViewHolder {
        TextView tv_space;
        TextView tv_content;
        TextView tv_clocktime;
        Switch sw_launch;
    }
}
