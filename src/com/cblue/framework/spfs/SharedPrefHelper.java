package com.cblue.framework.spfs;

import android.content.Context;
import android.content.SharedPreferences;

import com.cblue.framework.application.SoftApplication;

public class SharedPrefHelper {
	/**
	 * SharedPreferences的名字
	 */
	private static final String SP_FILE_NAME = "APPLICATION_SP";
	private static SharedPrefHelper sharedPrefHelper = null;
	private static SharedPreferences sharedPreferences;
	/**
	 * 经度
	 */
	private static final String LONGITUDE = "LONGITUDE";
	/**
	 * 纬度
	 */
	private static final String LATITUDE = "LATITUDE";
	
	public static synchronized SharedPrefHelper getInstance() {
		if (null == sharedPrefHelper) {
			sharedPrefHelper = new SharedPrefHelper();
		}
		return sharedPrefHelper;
	}
	
	private SharedPrefHelper() {
		sharedPreferences = SoftApplication.softApplication.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
	}

	public void setPhoneNumber(String phoneNumber) {
		sharedPreferences.edit().putString("phoneNumber", phoneNumber).commit();
	}
	public String getPhoneNumber(){
		return sharedPreferences.getString("phoneNumber", "");
	}

	public void setPassword(String password) {
		sharedPreferences.edit().putString("password", password).commit();
	}
	
	public String getPassword(){
		return sharedPreferences.getString("password", "");
	}
	
	public void setRememberAccount(boolean bool){
		sharedPreferences.edit().putBoolean("rememberAccount", bool).commit();
	}
	public boolean isRememberAccount(){
		return sharedPreferences.getBoolean("rememberAccount",false);
	}
	

	/**
	 * 设置经度
	 * @param longitude
	 */
	public void setLongitude(String longitude){
		sharedPreferences.edit().putString(LONGITUDE, longitude).commit();
	}
	
	/**
	 * 获取经度
	 * @return
	 */
	public String getLongitude(){
		return sharedPreferences.getString(LONGITUDE, "");
	}
	
	/**
	 * 设置纬度
	 */
	public void setLatitude(String latitude){
		sharedPreferences.edit().putString(LATITUDE, latitude).commit();
	}
	
	/**
	 * 获取纬度
	 */
	public String getLatitude(){
		return sharedPreferences.getString(LATITUDE, "");
	}

}
