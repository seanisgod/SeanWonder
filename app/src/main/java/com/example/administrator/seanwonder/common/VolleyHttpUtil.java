package com.example.administrator.seanwonder.common;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;

//volley请求工具类
public class VolleyHttpUtil {
	// 请求队列
	private static RequestQueue requestQueue;

	// get请求字符串
	public static void getRequest(Context context, String url, VolleyCallBack volleyCallBack) {
		requestQueue = Volley.newRequestQueue(context);
		// 字符串请求
		StringRequest stringRequest = new StringRequest(url, volleyCallBack.listener(),
				volleyCallBack.errorListener()) {
			// 自定义头部信息
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("apikey", "572dc7cf1f32ee04ab48d1bf25a38cd4");
				return map;
			}
		};
		requestQueue.add(stringRequest);
	}

	// post请求字符串
	public static void postRequest(Context context, String url, final Map<String, String> map,
								   VolleyCallBack volleyCallBack) {
		requestQueue = Volley.newRequestQueue(context);
		// POST请求获取String
		StringRequest stringRequest = new StringRequest(Method.POST, url, volleyCallBack.listener(),
				volleyCallBack.errorListener()) {
			// 自定义头部信息
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("apikey", "be730814dc7c15470116f8e89f0284e2");
				return map;
			}

			// 添加请求参数
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return map;
			}
		};
		requestQueue.add(stringRequest);
	}

}
