package com.cblue.framework.parser;

import com.cblue.framework.bean.LoginResponse;

/**
 * 把json字符串解析成对象
 * @author Administrator
 *
 */
public class LoginBaseParser extends BaseParser<LoginResponse> {

	@Override
	public LoginResponse parse(String paramString) {
		// TODO Auto-generated method stub
		LoginResponse loginResponse  =  new LoginResponse();
		loginResponse.msg = paramString;
		return loginResponse;
	}

}
