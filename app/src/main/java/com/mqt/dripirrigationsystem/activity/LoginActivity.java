package com.mqt.dripirrigationsystem.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.interfac.OnUIRequestCallback;
import com.mqt.dripirrigationsystem.manager.UserManager;
import com.mqt.dripirrigationsystem.service.LoginService;
import com.mqt.dripirrigationsystem.service.NodeService;

/**
 * Created by Administrator on 2016/6/20.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String IS_REMEMBER = "isRemember";
    private static final String USER_NAME = "userName";
    private static final String PASSWORD = "password";
    private static final String USER_ID = "userId";
    private static final String LOGIN_URL = "http://192.168.155.1:8080/DripIrrigationSystem/login";

    private EditText et_username;
    private EditText et_psswd;
    private Button bt_login;
    private CheckBox cb_remember;

    private ProgressBar pb;
    private SharedPreferences spf;

    private OnUIRequestCallback callback = new OnUIRequestCallback() {
        @Override
        public void onUIRequestStart() {
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        public void onUIRequestSuccess(String res) {
            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

            //登录成功后实现本地持久化。
            boolean isRemember = spf.getBoolean(IS_REMEMBER,false);
            UserManager manager = UserManager.getInstance();
            if(!isRemember){
                SharedPreferences.Editor editor = spf.edit();
                editor.putBoolean(IS_REMEMBER,true).commit();
                editor.putString(USER_NAME,manager.getUser(0).getUserName()).commit();
                editor.putString(PASSWORD,manager.getUser(0).getPassword()).commit();
                editor.putInt(USER_ID,manager.getUser(0).getUserId()).commit();
            }
            Intent mainActivity = new Intent(LoginActivity.this,NodeActivity.class);
            mainActivity.putExtra("userId",manager.getUser(0).getUserId());
            startActivity(mainActivity);
            LoginActivity.this.finish();

        }

        @Override
        public void onUIRequestError(Exception e) {
            pb.setVisibility(View.GONE);
            Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        et_username = (EditText)findViewById(R.id.wel_username_et);
        et_psswd = (EditText)findViewById(R.id.wel_password_et);
        bt_login = (Button)findViewById(R.id.requst_btn);
        pb = (ProgressBar)findViewById(R.id.login_process_dialog_progressBar);
        cb_remember = (CheckBox)findViewById(R.id.login_checkbox);
        //登录
        bt_login.setOnClickListener(this);
        //
        spf = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        //检查是否保存过密码
        checkRemember();
    }

    private void checkRemember() {
        if(cb_remember.isChecked() && spf.getBoolean(IS_REMEMBER,false)){
            et_username.setText(spf.getString(USER_NAME,""));
            et_psswd.setText(spf.getString(PASSWORD,""));
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.requst_btn){
            LoginService service = new LoginService();
            boolean isLogin = service.isLogin(LoginActivity.this,
                    et_username.getText().toString(),et_psswd.getText().toString());
          /* Intent mainActivity = new Intent(LoginActivity.this,NodeActivity.class);
           // mainActivity.putExtra("userId",manager.getUser(0).getUserId());
            startActivity(mainActivity);
            LoginActivity.this.finish();*/
           if(isLogin){
                service.sendRequest(LoginActivity.this,callback,
                        LOGIN_URL,"GET","username="+et_username.getText().toString()+"&"+"password="+et_psswd.getText().toString());
            }
        }
    }

}
