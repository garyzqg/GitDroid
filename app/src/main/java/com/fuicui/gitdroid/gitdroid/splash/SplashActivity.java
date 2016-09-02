package com.fuicui.gitdroid.gitdroid.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fuicui.gitdroid.gitdroid.MainActivity;
import com.fuicui.gitdroid.gitdroid.R;
import com.fuicui.gitdroid.gitdroid.commons.ActivityUtils;
import com.fuicui.gitdroid.gitdroid.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：yuanchao on 2016/8/23 0023 11:20
 * 邮箱：yuanchao@feicuiedu.com
 */
public class SplashActivity extends AppCompatActivity {

    private ActivityUtils activityUtils;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activityUtils = new ActivityUtils(this);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnLogin) void login() {
        activityUtils.startActivity(LoginActivity.class);
        finish();
    }

    @OnClick(R.id.btnEnter) void enter() {
        activityUtils.startActivity(MainActivity.class);
        finish();
    }
}
