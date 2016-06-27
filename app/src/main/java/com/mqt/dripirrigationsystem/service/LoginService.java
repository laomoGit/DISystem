package com.mqt.dripirrigationsystem.service;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.mqt.dripirrigationsystem.domain.User;
import com.mqt.dripirrigationsystem.manager.UserManager;
import com.mqt.dripirrigationsystem.utils.LogInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/6/25.
 */
public class LoginService extends BaseService{
    UserManager manager;
    public LoginService(){}
    @Override
    protected void onResponseToJson(String response) {
        try {
            //保存用户信息
            manager = UserManager.getInstance();
            JSONObject json = new JSONObject(response);
            JSONObject jsonUser = json.getJSONObject("user");
            User user = new User(jsonUser);
            manager.saveUser(user);
            LogInfo.info(user.getNickName());
            LogInfo.info(user.getModifyTime());
            LogInfo.info(user.getPassword());
            LogInfo.info(user.getUserImg());
            LogInfo.info(user.getUserName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isLogin(Context context,String userName, String password){
        if(TextUtils.isEmpty(userName)){
            Toast.makeText(context,"请输入账号!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(context,"请输入密码!",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)){
            return true;
        }
        return false;
    }


}
