package com.cblue.framework.config;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.json.JSONObject;

import android.content.Context;
import android.content.res.AssetManager;

import com.cblue.framework.contant.Constants;


public final class AppConfig {
	private static AppInfo appInfo;
	private AppConfig() {
		
	}
	/**
	 * 读取系统文件信息
	 * @param context
	 * @return
	 */
	public static AppInfo getAppConfigInfo(Context context) {
		AssetManager am = context.getAssets();
		try {
			InputStream is = am.open(Constants.APP_CONFIG_FILE_NAME);
			return parse(is);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static AppInfo parse(InputStream is) throws Exception {
		appInfo = new AppInfo();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		String configString = baos.toString();
		JSONObject appConfig = new JSONObject(configString);
		appInfo.serverAddress = appConfig.getString("server_address");
		appInfo.os = appConfig.getString("os");
		appInfo.crc = appConfig.getString("crc");
		appInfo.appKey = appConfig.getString("app_key");
		appInfo.sourceId = appConfig.getString("source_id");
		appInfo.ver = appConfig.getString("ver");
		appInfo.uid = appConfig.getString("uid");
		appInfo.api_user = appConfig.getString("api_user");
		appInfo.api_pwd = appConfig.getString("api_pwd");
		baos.close();
		is.close();
		return appInfo;
	}

}
