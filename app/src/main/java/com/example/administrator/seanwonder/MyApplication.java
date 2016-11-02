package com.example.administrator.seanwonder;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by sean on 2016/11/2.
 */

public class MyApplication extends Application {
    public static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        // 创建请求队列
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    // 获取RequestQueue
    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
