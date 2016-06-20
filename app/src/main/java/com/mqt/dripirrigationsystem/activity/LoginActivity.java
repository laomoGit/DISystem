package com.mqt.dripirrigationsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mqt.dripirrigationsystem.R;

/**
 * Created by Administrator on 2016/6/20.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_username;
    private EditText et_psswd;
    private Button bt_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        et_username = (EditText)findViewById(R.id.wel_username_et);
        et_psswd = (EditText)findViewById(R.id.wel_password_et);
        bt_login = (Button)findViewById(R.id.requst_btn);
        //登录
        bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.requst_btn){
            if(!isInputError()){
                //TODO 发送请求
                //请求成功返回用户数据，跳转到主界面
                Intent mainActivity = new Intent(this,NodeActivity.class);
                startActivity(mainActivity);
                Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
    }

    //判断输入是否为空
    private boolean isInputError() {
        if(TextUtils.isEmpty(et_username.getText()) || TextUtils.isEmpty(et_psswd.getText())){
            Toast.makeText(this,"账号或密码输入不能为空",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
