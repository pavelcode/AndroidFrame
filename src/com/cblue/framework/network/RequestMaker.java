package com.cblue.framework.network;

import com.cblue.framework.application.SoftApplication;

public class RequestMaker {

	@SuppressWarnings("unused")
	private static final String FROM = "from";
	@SuppressWarnings("unused")
	private static final String API_USER = "api_user";
	@SuppressWarnings("unused")
	private SoftApplication softApplication;

	private RequestMaker() {
		softApplication = SoftApplication.softApplication;
	}

	private static RequestMaker requestMaker = null;

	/**
	 * 得到JsonMaker的实例
	 * 
	 * @param context
	 * @return
	 */
	public static RequestMaker getInstance() {
		if (requestMaker == null) {
			requestMaker = new RequestMaker();
			return requestMaker;
		} else {
			return requestMaker;
		}
	}

	
}
