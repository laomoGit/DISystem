package com.mqt.dripirrigationsystem.service;

import android.app.Activity;
import com.mqt.dripirrigationsystem.interfac.OnUIRequestCallback;
import com.mqt.dripirrigationsystem.utils.NetUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2016/6/26.
 */
public abstract class BaseService {
    Activity activity;
    OnUIRequestCallback callback;

    public BaseService(){

    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setCallBack(OnUIRequestCallback callback) {
        this.callback = callback;
    }
    /**
     *
     * @param httpUrl
     * @param method
     * @param params
     */
    public void sendRequest(Activity activity,OnUIRequestCallback callback,final String httpUrl, final String method, final String params){
        this.activity = activity;
        this.callback = callback;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    onStart();
                    String response = getResponse(httpUrl, method, params);
                    onSuccess(response);
                }catch (Exception e){
                    e.printStackTrace();
                    onError(e);
                }
            }
        }).start();
    }

    private void onError(final Exception e) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(callback!=null){
                    callback.onUIRequestError(e);
                }
            }
        });
    }

    private String getResponse(String httpUrl, String method, String params) throws Exception {
        return NetUtils.sendGetRequest(httpUrl, method, params);
    }

    private void onStart() {
        if(activity == null){
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(callback!=null){
                    callback.onUIRequestStart();
                }
            }
        });
    }

    private void onSuccess(final String response) {
        if(activity == null){
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在UI界面显示前先将数据转换成相应的实体
                onResponseToJson(response);
                if(callback!=null){
                    callback.onUIRequestSuccess(response);
                }
            }
        });
    }



    protected abstract void onResponseToJson(String response);


}
