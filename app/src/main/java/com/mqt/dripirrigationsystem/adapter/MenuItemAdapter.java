package com.mqt.dripirrigationsystem.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TextViewCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.domain.LvMenuItem;

import java.util.List;

/**
 * Created by Administrator on 2016/6/23.
 */
public class MenuItemAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int mIconSize;

    private List<LvMenuItem>menuItems;

    public MenuItemAdapter(Context context,List<LvMenuItem> items){
        this.menuItems = items;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mIconSize = context.getResources().getDimensionPixelSize(R.dimen.drawer_icon_size);
    }


    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return menuItems.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return menuItems.get(0).getTYPE_COUNT();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //这里有三种类型的item，但现在只需要正常的类型
        LvMenuItem item = menuItems.get(position);
        switch (item.getType()){
            case LvMenuItem.TYPE_NORMAL:
                if(convertView == null){
                    convertView = mLayoutInflater.inflate(R.layout.design_drawer_item,parent,false);
                }
                TextView itemView = (TextView) convertView;
                itemView.setText(item.getName());
                Drawable icon = mContext.getResources().getDrawable(item.getIcon());
                setIconColor(icon);

                if(icon != null){
                    icon.setBounds(0,0,mIconSize,mIconSize);
                    TextViewCompat.setCompoundDrawablesRelative(itemView,icon,null,null,null);
                }
                break;

        }
        return convertView;
    }

    private void setIconColor(Drawable icon) {

        int textColorSecondary = android.R.attr.textColorSecondary;
        TypedValue value = new TypedValue();
        if (!mContext.getTheme().resolveAttribute(textColorSecondary, value, true))
        {
            return;
        }
        int baseColor = mContext.getResources().getColor(value.resourceId);
        icon.setColorFilter(baseColor, PorterDuff.Mode.MULTIPLY);
    }
}
