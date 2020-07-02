package com.xiang.restserver;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MediaType;

public class OkRequest {
	private static OkHttpClient client = new OkHttpClient();
	public static String getRemoteIp() {
		String ip = "";
		try {
			String api = "https://www.eecp123.com/ip/ip.php";
			Request request = new Request.Builder()
					.url(api)
					.get() 
					.build();
			final Call call = client.newCall(request);
			okhttp3.Response response;
			response = call.execute();
			ip = response.body().string().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}

	    return ip;
	}
	public static String getStringData(String api, JSONObject params) {
		JSONObject result = getByUrlAndParams(api, params);
		if(result.getBooleanValue("success")) {
			return result.getString("data");
		}
		return null;
	}
	public static JSONObject getJSONData(String api, JSONObject params) {
		JSONObject result = getByUrlAndParams(api, params);
		if(result.getBooleanValue("success")) {
			return result.getJSONObject("data");
		}
		return null;
	}
	private static JSONObject getByUrlAndParams(String api, JSONObject params) {
		JSONObject result = new JSONObject();
		try {
			String query = "";
			if(null != params) {
				Set<String> keys = params.keySet();
				for(String k : keys) {
					query += k+"="+URLEncoder.encode(params.getString(k), "utf-8");
				}
			}
			Request request = new Request.Builder()
					.url(api + "?" + query) 
					.get()
					.build();
			final Call call = client.newCall(request);
			okhttp3.Response response = call.execute();
			String res = response.body().string().trim();
			try {
				result = JSONObject.parseObject(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static JSONObject postByUrlAndParams(String api, JSONObject params) {
		JSONObject result = new JSONObject();
		try {
			RequestBody bodybuilder = RequestBody.create(params.toJSONString(), MediaType.get("application/json; charset=utf-8"));
			Request request = new Request.Builder()
					.url(api) 
					.post(bodybuilder)
					.build();
			final Call call = client.newCall(request);
			okhttp3.Response response = call.execute();
			String res = response.body().string().trim();
			try {
				result = JSONObject.parseObject(res);
			} catch (Exception e) {
				JSONObject status = new JSONObject();
				status.put("code", "100100");
				status.put("msg", res);
				result.put("status", status);
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
