package com.mqt.dripirrigationsystem.domain;

import android.text.TextUtils;

/**
 * Created by Administrator on 2016/6/23.
 */
public class LvMenuItem {
    private int type;
    private String name;
    private int icon;
    private final int TYPE_COUNT = 3;

    public static final int NO_ICON = 0;
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_NO_ICON = 1;
    public static final int TYPE_SEPARATOR = 2;

    public LvMenuItem(String name,int icon){
        this.icon = icon;
        this.name = name;

        if(icon == NO_ICON && TextUtils.isEmpty(name)){
            type = TYPE_SEPARATOR;
        }else if(icon == NO_ICON){
            type = TYPE_NO_ICON;
        }else {
            type = TYPE_NORMAL;
        }

        if(type!=TYPE_SEPARATOR && TextUtils.isEmpty(name)){
            throw new IllegalArgumentException("你需要为列表项设置一个名字");
        }
    }

    public LvMenuItem(String name){
        this(name,NO_ICON);
    }

    public LvMenuItem(){
        this(null);
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    public int getTYPE_COUNT() {
        return TYPE_COUNT;
    }
}
