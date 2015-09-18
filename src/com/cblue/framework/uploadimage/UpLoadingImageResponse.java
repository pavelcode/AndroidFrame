package com.cblue.framework.uploadimage;

import com.cblue.framework.bean.BaseResponse;

/**
 * 上传图片响应对象
 * @author Administrator
 *
 */
public class UpLoadingImageResponse extends BaseResponse {


	private static final long serialVersionUID = 1747569043717239435L;
	
	public String head_url;//图像地址

	@Override
	public String toString() {
		return "UpLoadingImageResponse [head_url=" + head_url + "]";
	}

	
	
	
}
