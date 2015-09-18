package com.cblue.framework.contant;

public class Constants {

	public static final int ERROR_CODE_OK = 200;

	//新浪相关参数
	public static final int SINA_AUTHORIZE_CANCEL = 0;
	public static final int SINA_AUTHORIZE_SUCCESS = 1;
	public static final int SINA_AUTHORIZE_ERROR = 2;
	public static final int SINA_AUTHORIZE_EXCEPTION = 3;
	public static final int SINA_SHARE_WEIBO_COMPLETE = 4;
	public static final int SINA_SHARE_WEIBO_WEIBO_EXCEPTION = 5;
	public static final int SINA_SHARE_WEIBO_IOEXCEPTION = 6;
	
	
	

	public static final String APP_CONFIG_FILE_NAME = "AppConfig.json";
	public static final String FROM_PAGE = "fromPage";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String PASSWORD = "password";
	public static final String WEIBO_TYPE_SINA = "新浪";
	public static final String WEIBO_TYPE_TENCENT = "腾讯";
	public static final int QQ_LOGIN_FAILED = 10;// QQ登录失败
	public static final int QQ_LOGIN_SUCCESS = 11;// QQ登录成功

	/**
	 * 新浪微博
	 */
	public static final String SINAWEIBO_USER_APPKEY = "";// 新浪appkey
	public static final String SINAWEIBO_USER_REDIRECTURL = "";// 新浪回调地址

	/**
	 * QQ互联（空间）
	 */
	public static final String QZONE_APPID = "";
	public static final String QZONE_APPKEY = "";

	/**
	 * 腾讯微博
	 */
	public static final String TENCENTWEIBO_APPKEY = "";// 腾讯
	public static final String TENCENTWEIBO_APPSECRET = "";// 腾讯appSecret
	public static final String TENCENTWEIBO_REDIRECTURI = "";// 腾讯回调地址
	public static final int TENCENTWEIBO_REQUEST_CODE = 10001;// 腾讯微博授权请求码(可自定义一个唯一的)
	public static final int TENCENTWEIBO_RESULT_CODE = 2;// 腾讯微博授权成功之后的固定的返回码resultCode(不能修改)

	/**
	 * 微信
	 */
	// APP_ID 替换为你的应用从官方网站申请到的合法appId
	public static final String WX_APP_ID = "";

	public static final String KERUN_QRCODE_DIR = "";
	public static final String KERUN_QRCODE = "";

	

	/** 请求数据的方式 */
	public static final int DEFAULT_REQUEST = 0;// 默认请求
	public static final int MORE_REQUEST = 1;// 加载更多请求
	public static final int REFRESH_REQUEST = 2;// 下拉刷新请求

	/** 默认页数 */
	public static final int DEFAULT_PAGE = 1;
	/** 默认数量 */
	public static final int DEFAULT_NUM = 10;

	public static final int RELOAD_EXERCISE_COMMENT_CODE = 0;// 重新加载活动评论
	
	
	public static final String XMPP_NICKNAME_SEPARATOR = "_and_";//xmpp昵称中的分隔符

}
