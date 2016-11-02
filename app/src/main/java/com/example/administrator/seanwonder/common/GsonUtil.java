package com.example.administrator.seanwonder.common;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//Gson工具类
public class GsonUtil {
	private static Gson gson;
	static {
		if (gson == null) {
			gson = new Gson();
		}
	}

	// 把对象转换成json
	public static String objectToJsonString(Object object) {
		return gson.toJson(object);
	}

	// 把json字符串转换成数据bean
	public static <T> Object jsonToObject(String jsonString, Class<T> cls) {
		T t;
		t = gson.fromJson(jsonString, cls);
		return t;
	}

	// 把json数组字符串转换成List
	public static <T> List<T> jsonToList(String json, Class<T> cls) {
		List<T> list;
		list = gson.fromJson(json, new TypeToken<List<T>>() {
		}.getType());
		return list;
	}

	// 把json字符串转换成Map
	public static <V> Map<String, V> jsonToMap(String json) {
		Map<String, V> map = null;
		gson.fromJson(json, new TypeToken<Map<String, V>>() {
		}.getType());
		return map;
	}
}
