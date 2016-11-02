package com.example.administrator.seanwonder.common;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import android.util.Log;

//请求完成接口回调
public abstract class VolleyCallBack {
	public static Listener<String> mListener;
	public static ErrorListener mErrorListener;

	public VolleyCallBack(Listener<String> listener, ErrorListener errorListener) {
		this.mListener = listener;
		this.mErrorListener = errorListener;
	}

	// 请求成功之后回调
	public abstract void onMySuccess(String response);

	// 请求失败之后回调
	public abstract void onMyError(VolleyError volleyError);

	// 请求成功的事件监听方法
	public Listener<String> listener() {
		// 创建一个监听对象
		mListener = new Listener<String>() {
			@Override
			public void onResponse(String arg0) {
				Log.d("xy", arg0);
				onMySuccess(arg0);
			}
		};
		// 返回监听
		return mListener;
	}

	// 请求失败的事件监听方法
	public ErrorListener errorListener() {
		mErrorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				Log.e("xy", arg0.toString());
				onMyError(arg0);
			}
		};
		return mErrorListener;
	}
}
