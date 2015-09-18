package com.cblue.framework.uploadimage;

import com.alibaba.fastjson.JSONObject;
import com.cblue.framework.parser.BaseParser;

public class PublishParser extends BaseParser<PublishResponse> {

	@Override
	public PublishResponse parse(String paramString) {
		PublishResponse response = new PublishResponse();
		JSONObject jsonObject = JSONObject.parseObject(paramString);
		response.code = jsonObject.getIntValue("code");
		response.msg = jsonObject.getString("msg");
		return response;
	}

}
