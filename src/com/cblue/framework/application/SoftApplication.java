package com.cblue.framework.application;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.cblue.framework.config.AppConfig;
import com.cblue.framework.config.AppInfo;
import com.cblue.framework.network.HttpRequestAsyncTask;
import com.cblue.framework.network.Request;
import com.cblue.framework.network.HttpRequestAsyncTask.OnCompleteListener;
import com.cblue.framework.spfs.SharedPrefHelper;
import com.cblue.framework.util.CrcUtil;
import com.cblue.framework.util.NetUtil;
import com.cblue.service.user.bean.UserInfo;

/**
 * 保存经纬度信息到SharedPreference
 * 得到应用信息
 * 设置或获取用户信息   注销账号
 * 退出应用
 * 网络请求
 * @author Administrator
 */
public class SoftApplication extends Application {
	/**
	 * 存放活动状态的(未被销毁)的Activity列表
	 */
	public static List<Activity> unDestroyActivityList = new ArrayList<Activity>();
	public static SoftApplication softApplication;
	private static AppInfo appInfo;
	
	private static UserInfo userInfo;
	private static boolean isLogin;//判断是否已经登录
	private static String passwordWithMd5 = "";
	
	@Override
	public void onCreate() {
		softApplication = this;
		
		super.onCreate();
		
		appInfo = initAppInfo();
	}
	

	/**
	 * 将经纬度存至SharedPref
	 * 
	 * @param longitude 经度
	 * @param latitude 纬度
	 */
	public void setLonAndLatToSharedPref(String longitude, String latitude) {
		// 设置经纬度
		SharedPrefHelper.getInstance().setLongitude(longitude);
		SharedPrefHelper.getInstance().setLatitude(latitude);
	}


	/**
	 * 实例化AppInfo
	 */
	private AppInfo initAppInfo() {
		AppInfo appInfo = AppConfig.getAppConfigInfo(softApplication);
		appInfo.imei = NetUtil.getIMEI(getApplicationContext());
		appInfo.imsi = NetUtil.getIMSI(getApplicationContext()) == null ? "" : NetUtil.getIMSI(getApplicationContext());
		appInfo.osVersion = getOSVersion();
		appInfo.appVersionCode = getAppVersionCode();
		return appInfo;
	}
	
	/**
	 * 得到系统的版本号
	 * @return
	 */
	public String getOSVersion() {
		return android.os.Build.VERSION.RELEASE;
	}
	
	
	
	/**
	 * VersionCode和VersionName的区别：
	 * android:versionCode——整数值，代表应用程序代码的相对版本，也就是版本更新过多少次。
     * 整数值有利于其它程序比较，检查是升级还是降级。你可以把这个值设定为任何想设的值，但是，你必须保证后续更新版的值要比这个大。系统不会强制要求这一行为，但是随着版本更新值也增加是正常的行为。
     * 一般来说，你发布的第一版程序的versionCode设定为1，然后每次发布都会相应增加，不管发布的内容是较大还是较小的。这意味着android:versionCode不像应用程序的发布版本（看下面的android:versionName）那样显示给用户。应用程序和发布的服务不应该显示这个版本值给用户。
     * android:versionName——字符串值，代表应用程序的版本信息，需要显示给用户。与android:versionCode一样，系统不会为了任何内部的目的使用这个值，除了显示给用户外。发布的服务也需要提取这个值来显示给用户。
	 */
	
	
	/**
	 * 得到应用的版本号
	 * VersionCode
	 * @return
	 */
	public int getAppVersionCode() {
		PackageManager packageManager = getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo;
		int versionCode = 0;
		try {
			packInfo = packageManager.getPackageInfo(getPackageName(), 0);
			versionCode = packInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}
	
	/**
	 * 得到应用的版本名
	 * @return
	 */
	public String getAppVersionName() {
		PackageManager packageManager = getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo;
		String versionCode = "";
		try {
			packInfo = packageManager.getPackageInfo(getPackageName(), 0);
			versionCode = packInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}
	
	/**
	 * 获取Assert文件夹中的配置文件信息
	 * @return
	 */
	public AppInfo getAppInfo() {
		return appInfo;
	}
	
	public String getFrom(){
		return appInfo == null ? "" : appInfo.os;
	}
	
	public String getApiUser(){
		return appInfo == null ? "" : appInfo.api_user;
	}
	
	public String getApiPassword(){
		return appInfo == null ? "" : appInfo.api_pwd;
	}
	
	
	/**
	 * 退出应用
	 */
	public void quit(){
		for (Activity activity : unDestroyActivityList) {
			if (null != activity) {
				activity.finish();
			}
		}
		unDestroyActivityList.clear();
		logout();
		
	}
	
	/**
	 * 网络请求
	 * @param request
	 * @param onCompleteListener
	 */
	public void requestNetWork(Request request, @SuppressWarnings("rawtypes") OnCompleteListener onCompleteListener) {
		HttpRequestAsyncTask httpRequestTask = new HttpRequestAsyncTask();
		httpRequestTask.setOnCompleteListener(onCompleteListener);
		httpRequestTask.execute(request);
	}
	
	/**
	 * 注销帐号
	 */
	public void logout() {
		userInfo = null;
		isLogin = false;
		try {
			passwordWithMd5 = CrcUtil.MD5(appInfo.crc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 保存登录成功之后用户的信息
	 * @param result
	 */
	public void setUserInfo(UserInfo result) {
		userInfo = result;
	}
	
	/**
	 * 更新用户信息
	 * @param result
	 */
	public void updateUserInfo(UserInfo result){
		if(result == null){
			return;
		}
		userInfo = result;
	}
	
	
	public String getPasswordWithMd5() {
		return passwordWithMd5;
	}

	public void setPasswordWithMd5(String passwordWithMD5) {
		passwordWithMd5 = passwordWithMD5;
	}

	public boolean isLogin() {
		return isLogin;
	}

	/**
	 * 获取用户的信息
	 * @return
	 */
	public UserInfo getUserInfo(){
		return userInfo;
	}



	/**
	 * 设置用户登录状态
	 * @param b
	 */
	public void setLoginStatus(boolean b) {
		isLogin = b;
	}


	
}
