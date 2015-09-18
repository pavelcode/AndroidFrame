package com.cblue.framework.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

import org.apache.http.conn.util.InetAddressUtils;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * 判断网络是否可用, 网络开关，网络提供商，wifi网络列表
 * 判断GSP是否可用,GSP开关
 * 本地IP地址
 * @author Administrator
 *
 */
public class NetUtil {
	
	private static final String TAG = "MobileUtils";

	/**
	 * 判断网络连接是否已开
	 * 
	 * @param context
	 * @return true 已打开 false 未打开
	 */
	public static boolean isNetDeviceAvailable(Context context) {
		boolean bisConnFlag = false;
		ConnectivityManager conManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo network = conManager.getActiveNetworkInfo();
		if (network != null) {
			bisConnFlag = conManager.getActiveNetworkInfo().isAvailable();
		}
		return bisConnFlag;
	}

	/**
	 * 判断网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null) {
			String typeName = info.getTypeName().toLowerCase(); // WIFI/MOBILE
			boolean isAvailable = false;
			if (typeName.equals("wifi")) {
				isAvailable = true;
			} else {
				String apnName = info.getExtraInfo().toLowerCase();
				if (apnName.contains("net")) {
					isAvailable = true;
				}
			}
			if (isAvailable
					&& info.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 提供商
	 * 
	 * @author Administrator
	 * 
	 */
	public static enum ProviderName {
		chinaMobile("中国移动"), chinaUnicom("中国联通"), chinaTelecom("中国电信"), chinaNetcom(
				"中国网通"), other("未知");
		private String text;

		private ProviderName(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}
	}

	
	public static ProviderName getProviderName(Context context) {
		String imsi = getIMSI(context);
		if (imsi != null) {
			// 因为移动网络编号46000下的IMSI已经用完,�?��虚拟了一�?6002编号�?34/159号段使用了此编号
			LogUtil.log("imsi", Log.INFO, imsi);
			if (imsi.startsWith("46000") || imsi.startsWith("46002")
					|| imsi.startsWith("46007")) {
				return ProviderName.chinaMobile;
			} else if (imsi.startsWith("46001")) {
				return ProviderName.chinaUnicom;
			} else if (imsi.startsWith("46003")) {
				return ProviderName.chinaTelecom;
			} else {
				return ProviderName.other;
			}
		} else {
			return ProviderName.other;
		}
	}

	/**
	 * IMEI 全称International Mobile Equipment Identity，中文翻译为国际移动装备辨识码，
	 * 即常所说的手机序列号，
	 * 用于在手机网络中识别每一部独立的手机，是国际上公认的手机标志序号，相当于移动电话的身份证。序列号共有15位数字，前6位（TAC）是型号核准号码
	 * 代表手机类型
	 * 。接2位（FAC）是装配号，代表产地。后6位（SNR）是串号，代表生产顺序号。最一位（SP）一般为0，是检验码，备用
	 * 国际移动装备辨识码一般贴于机身背面与外包装上，同时也存在于手机记忆体中，通过输入*#06#即可查询�?
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context) {
		TelephonyManager ts = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return ts.getDeviceId();
	}

	/**
	 * IMSI 全称International Mobile Subscriber Identity  中文翻译为国际移动用户识别码,它是在公众陆地移动电话网。
	 * （PLMN）中用于唯一识别移动用户的一个号码   在GSM网络，储存在SIM卡中，可用于区别移动用户的有效信息。
	 * 其总长度不超过15位，同样使用0~9的数字。其中MCC是移动用户所属国家代号，占3位数字，中国的MCC规定为460
	 * ；MNC是移动网号码，由两位或者三位数字组成，中国移动的移动网络编码（MNC）为00；用于识别移动用户所归属的移动通信网
	 * ；MSIN是移动用户识别码，用以识别某一移动通信网中的移动用户。
	 */
	public static String getIMSI(Context context) {
		TelephonyManager ts = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return ts.getSubscriberId();
	}

	/**
	 * gps是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isGPSEnable(Context context) {
		/*
		 * 用Setting.System来读取也可以，只是这是更旧的用法 String str =
		 * Settings.System.getString(getContentResolver(),
		 * Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		 */
		String str = Secure.getString(context.getContentResolver(),
				Secure.LOCATION_PROVIDERS_ALLOWED);
		Log.v("GPS", str);
		if (str != null) {
			return str.contains("gps");
		} else {
			return false;
		}
	}

	/**
	 * GPS开关
	 * 如果gps是打开就关闭，如果是关就打开 
	 * private static final int BUTTON_WIFI = 0;
	 * private static final int BUTTON_BRIGHTNESS = 1; 
	 * private static final int BUTTON_SYNC = 2; 
	 * private static final int BUTTON_GPS = 3; 
	 * private staticfinal int BUTTON_BLUETOOTH = 4;
	 */
	public static void toggleGPS(Context contex) {
		Intent gpsIntent = new Intent();
		gpsIntent.setClassName("com.android.settings",
				"com.android.settings.widget.SettingsAppWidgetProvider");
		gpsIntent.addCategory("android.intent.category.ALTERNATIVE");
		gpsIntent.setData(Uri.parse("custom:3"));
		try {
			PendingIntent.getBroadcast(contex, 0, gpsIntent, 0).send();
		} catch (CanceledException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断GPS是否可用
	 * @param contex
	 * @return
	 */
	public boolean isOpenGps(Context contex) {
		LocationManager manager = (LocationManager) contex
				.getSystemService(Context.LOCATION_SERVICE);
		return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	/**
	 * 关闭GPS
	 * @param contex
	 */
	public static void closeGps(Context contex) {
		if (isGPSEnable(contex)) {
			toggleGPS(contex);
		}
	}

	/**
	 * Wifi开关
	 * android.permission.ACCESS_WIFI_STATE 
	 * android.permission.CHANGE_WIFI_STATE
	 * android.permission.WAKE_LOCK wifi使用异步
	 */
	public static void toggleWifi(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);

		if (wifiManager.isWifiEnabled()) {
			wifiManager.setWifiEnabled(false);
		} else {
			wifiManager.setWifiEnabled(true);
		}
	}

	/**
	 * 扫描wifi信息
	 * @param context
	 */
	public static void getScanWifiResults(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		List<ScanResult> wifiResults = wifiManager.getScanResults();
		for (ScanResult wifi : wifiResults) {
			LogUtil.log(TAG, Log.DEBUG, wifi.toString());
		}

		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		LogUtil.log(TAG, Log.DEBUG, TelephonyManager.PHONE_TYPE_GSM + "----"
				+ tm.getPhoneType());
		List<NeighboringCellInfo> cellResults = tm.getNeighboringCellInfo();
		for (NeighboringCellInfo cell : cellResults) {
			LogUtil.log(TAG, Log.DEBUG,
					cell.getCid() + "-" + cell.getLac() + "-" + cell.getRssi()
							+ "-" + cell.getPsc() + "-" + cell.getNetworkType());
		}

		LogUtil.log(TAG, Log.DEBUG, getProviderName(context).getText());
	}

	

	/**
	 * 获取本机的Ip地址
	 * 
	 * @return
	 */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&& InetAddressUtils.isIPv4Address(inetAddress
									.getHostAddress())) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
		}
		return null;
	}
}
