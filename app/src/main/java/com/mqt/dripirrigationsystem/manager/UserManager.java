package com.mqt.dripirrigationsystem.manager;

import com.mqt.dripirrigationsystem.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */
public class UserManager {

    private static UserManager manager;
    private User user;
    private List<User> users;//可以实现多个号切换登录
    private UserManager(){
        users = new ArrayList<User>();
    }

    public static UserManager getInstance(){
        if(manager == null){
            manager = new UserManager();
        }
        return manager;
    }

    public boolean saveUser(User user){
        return users.add(user);
    }

    public boolean removeUser(User user){
        return users.remove(user);
    }

    public User getUser(int location){
        return users.get(location);
    }
}
