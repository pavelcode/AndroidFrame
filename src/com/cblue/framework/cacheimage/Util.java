package com.cblue.framework.cacheimage;

import java.security.MessageDigest;

import android.util.Log;


public class Util {
	
	
	/**
	 * 打印日志
	 * @param log
	 */
	public static void log(String log) {
		if(Constants.DEBUG) {
			Log.i(Constants.TAG, log);
		}
	}
	
	
	/**
	 * MD5加密
	 * @param source
	 * @return
	 * @throws Exception
	 */
	public static String MD5(String source) throws Exception {
		String resultHash = null;
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] result = new byte[md5.getDigestLength()];
		md5.reset();
		md5.update(source.getBytes("UTF-8"));
		result = md5.digest();

		StringBuffer buf = new StringBuffer(result.length * 2);

		for (int i = 0; i < result.length; i++) {
			int intVal = result[i] & 0xff;
			if (intVal < 0x10) {
				buf.append("0");
			}
			buf.append(Integer.toHexString(intVal));
		}

		resultHash = buf.toString();

		return resultHash.toString();
	
	}

}
