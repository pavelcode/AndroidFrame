package com.cblue.framework.util;

import java.security.MessageDigest;


public class CrcUtil {
	
	/**
	 * CRC即循环冗余校验码（Cyclic Redundancy Check）是数据通信领域中最常用的一种差错校验码
	 * CRC检验原理实际上就是在一个p位二进制数据序列之后附加一个r位二进制检验码(序列)，从而构成一个总长为n＝p＋r位的二进制序列；
	 * 附加在数据序列之后的这个检验码与数据序列的内容之间存在着某种特定的关系。
	 * 如果因干扰等原因使数据序列中的某一位或某些位发生错误，这种特定关系就会被破坏。
	 * 因此，通过检查这一关系，就可以实现对数据正确性的检验。
	 * @param timeStamp
	 * @param IMEI
	 * @param UID
	 * @param passwordWithMd5
	 * @param infoString
	 * @return
	 * @throws Exception
	 */
    public static String getCrc(String timeStamp,String IMEI,String UID,String passwordWithMd5,String infoString) throws Exception {
		
		String crc = MD5(timeStamp+IMEI+UID+passwordWithMd5+infoString);
		
		return crc;
		
	}
	
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
