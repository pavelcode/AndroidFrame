package com.cblue.framework.uploadimage;

import com.alibaba.fastjson.JSONObject;
import com.cblue.framework.parser.BaseParser;

public class UpLoadingImageParser extends BaseParser<UpLoadingImageResponse> {

	@Override
	public UpLoadingImageResponse parse(String paramString) {
		UpLoadingImageResponse response = new UpLoadingImageResponse();
		JSONObject loginJsonObject = JSONObject.parseObject(paramString);
		response.code = loginJsonObject.getIntValue("code");
		response.msg = loginJsonObject.getString("msg");
		
		JSONObject jsonObject = loginJsonObject.getJSONObject("data");
		if(jsonObject != null){
			response.head_url = jsonObject.getString("head_url");
		}
		return response;
	}

}
