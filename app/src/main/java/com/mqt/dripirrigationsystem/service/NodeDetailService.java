package com.mqt.dripirrigationsystem.service;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.interfac.OnUIRequestCallback;

/**
 * Created by Administrator on 2016/7/5.
 */
public class NodeDetailService extends BaseService{

    public void modifyUsePattern(OnUIRequestCallback callbackPattern,
                                 Activity activity, String patternUrl, String mother, String params){
        sendRequest(activity,callbackPattern,patternUrl,mother,params);
    }

    @Override
    protected void onResponseToJson(String response) {
        //Toast.makeText(this.activity,response,Toast.LENGTH_SHORT).show();
    }
}
