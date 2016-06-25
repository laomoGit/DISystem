package com.mqt.dripirrigationsystem.domain;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/6/25.
 */
public class User {
    private int userId;
    private String userName;
    private String password;
    private String modifyTime;
    private String userImg;
    private String nickName;

    public User(JSONObject obj) throws JSONException {
        if (obj.has("userId")) {
            userId = obj.getInt("userId");
        }
        if (obj.has("userName")) {
            userName = obj.getString("userName");
        }
        if (obj.has("password")) {
            password = obj.getString("password");
        }
        if (obj.has("modifyTime")) {
            modifyTime = obj.getString("modifyTime");
        }
        if (obj.has("userImg")) {
            userImg = obj.getString("userImg");
        }
        if(obj.has("nickName")){
            nickName = obj.getString("nickName");
        }
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
