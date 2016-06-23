package com.mqt.dripirrigationsystem.manager;

import android.app.Activity;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/23.
 */
public class ActivityManager {
    private static ActivityManager mActivityManager;
    private Map<String,Activity> activities;
    private List<String>names;

    private ActivityManager(){
        activities = new HashMap<String,Activity>();
        names = new ArrayList<String>();
    }

    public static ActivityManager getInstance(){
        if(mActivityManager == null){
            mActivityManager = new ActivityManager();
        }
        return null;
    }

    public Activity getActivity(String actName){
        return activities.get(actName);
    }

    public void addActivity(String actName,Activity activity){
        if(!TextUtils.isEmpty(actName) && activity instanceof Activity){
            activities.put(actName,activity);
        }
    }

    public void removeAllActivities(){
        while (activities.isEmpty()){

        }
    }
}
