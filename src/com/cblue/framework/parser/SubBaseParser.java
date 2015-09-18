package com.cblue.framework.parser;



import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.cblue.framework.bean.SubBaseResponse;

public class SubBaseParser extends BaseParser<SubBaseResponse> {

	@Override
	public SubBaseResponse parse(String paramString) {
		SubBaseResponse result = null;
		try {
			result = new SubBaseResponse();
			JSONObject loginJsonObject = JSONObject.parseObject(paramString);
			result.code = loginJsonObject.getIntValue("code");
			result.msg = loginJsonObject.getString("msg");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

}
