package com.example.administrator.seanwonder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.administrator.seanwonder.activity.LoginActivity;

/**
 * Created by sean on 2016/11/1.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 3000);
    }
}
