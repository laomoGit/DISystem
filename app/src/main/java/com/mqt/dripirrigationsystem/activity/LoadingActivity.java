package com.mqt.dripirrigationsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.mqt.dripirrigationsystem.R;

public class LoadingActivity extends AppCompatActivity {
    private AlphaAnimation mAlphaAnimation;

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable(){

        @Override
        public void run() {
        //加载数据，耗时的加载
            upLoginActivity();
        }
    };

    //跳转到登录界面
    private void upLoginActivity() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        this.finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //屏蔽标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = View.inflate(this,R.layout.activity_loading,null);

        mAlphaAnimation = new AlphaAnimation(0.1f,1.0f);
        mAlphaAnimation.setDuration(2000);
        mAlphaAnimation.setFillAfter(true);
        view.setAnimation(mAlphaAnimation);//开始动画

        setContentView(view);
        //监听动画
        mAlphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束
                getWindow().getDecorView().post(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.post(mRunnable);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //防止返回键退出启动
        if(keyCode== KeyEvent.KEYCODE_BACK)
            return false;
        return false;
    }
}
