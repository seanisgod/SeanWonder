package com.example.administrator.seanwonder.common;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.administrator.seanwonder.MyApplication;
import com.example.administrator.seanwonder.R;

import android.graphics.Bitmap;
import android.widget.ImageView;

//图片加载工具类
public class VolleyImageLoaderUtil {
	// 通过ImageRequest请求图片
	public static void showImage(String url, final ImageView imageView) {
		// 创建图片请求
		@SuppressWarnings("deprecation")
		ImageRequest imageRequest = new ImageRequest(url, new Listener<Bitmap>() {
			// 请求成功返回的图片
			@Override
			public void onResponse(Bitmap bitmap) {
				imageView.setImageBitmap(bitmap);
			}
		}, 0, 0, Bitmap.Config.RGB_565, new ErrorListener() {
			// 请求失败返回的错误
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				imageView.setImageResource(R.mipmap.fail);
			}
		});
		// 添加到请求队列
		MyApplication.getRequestQueue().add(imageRequest);
	}

	// 通过ImageLoader请求图片
	public static void setImageLoader(String url, ImageView imageView) {
		// 创建ImageLoader
		ImageLoader imageLoader = new ImageLoader(MyApplication.getRequestQueue(), new ImageCache() {

			@Override
			public void putBitmap(String arg0, Bitmap arg1) {

			}

			@Override
			public Bitmap getBitmap(String arg0) {
				return null;
			}
		});
		// ImageLoader监听
		ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageView, R.mipmap.loading,
				R.mipmap.fail);
		// 开始获取图片
		imageLoader.get(url, imageListener);
	}

	// 通过NetworkImageView请求图片
	public static void setNetworkImageView(String url, NetworkImageView networkImageView) {
		ImageLoader imageLoader = new ImageLoader(MyApplication.getRequestQueue(), new ImageCache() {

			@Override
			public void putBitmap(String arg0, Bitmap arg1) {

			}

			@Override
			public Bitmap getBitmap(String arg0) {
				return null;
			}
		});
		// 设置默认图片
		networkImageView.setDefaultImageResId(R.mipmap.loading);
		// 设置请求失败图片
		networkImageView.setErrorImageResId(R.mipmap.fail);
		// 请求图片并显示
		networkImageView.setImageUrl(url, imageLoader);
	}
}
