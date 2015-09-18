package com.cblue.framework.util;


public class StringUtil {

	/**
	 * 判断字符串是否为null或空字符串
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		boolean result = false;
		if (null == str || "".equals(str.trim())) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 判断字符串是否为null或空字符串
	 * @param string
	 * @return
	 */
	public static boolean isNotNull(String string) {
		if (null != string && !"".equals(string.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 如果i小于10，补齐后生成string
	 * 
	 * @param i
	 * @return
	 */
	public static String addZreoIfLessThanTen(int i) {

		String string = "";
		int ballNum = i + 1;
		if (ballNum < 10) {
			string = "0" + ballNum;
		} else {
			string = ballNum + "";
		}
		return string;
	}

	

	/**
	 * 去掉字符串中的所有的单个空格
	 * 
	 * @param string
	 */
	public static String replaceSpaceCharacter(String string) {
		if (null == string || "".equals(string)) {
			return "";
		}
		return string.replace(" ", "");
	}

	/**
	 * 获取小数位为6位的经纬度
	 * @param string
	 * @return
	 */
	public static String getStringLongitudeOrLatitude(String string) {
		if (StringUtil.isNullOrEmpty(string)) {
			return "";
		}
		if (string.contains(".")) {
			String[] splitArray = string.split("\\.");
			if (splitArray[1].length() > 6) {
				String substring = splitArray[1].substring(0, 6);
				return splitArray[0] + "." + substring;
			} else {
				return string;
			}
		} else {
			return string;
		}
	}


}
