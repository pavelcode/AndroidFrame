package com.cblue.framework.network;

/**
 * 服务接口定义
 * @author Administrator
 *
 */
public enum ServerInterfaceDefinition {

	/**
	 * 用户登录
	 * OPTION
	 */
	OPT_LOGIN("/Dingcan/user/list.do"),
	/**
	 * 第一次登录的第一次
	 */
	OPT_LOGIN_STEP_2("/user/first_1"),
	/**
	 * 第一次登陆的第二次
	 */
	OPT_LOGIN_STEP_3("/user/first_2"),
	/**
	 * 修改标签
	 */
	OPT_MODIFY_MARK("/user/update_user_tag"),
	/**
	 * 修改个人图像
	 */
	OPT_MODIFY_PERSONAL_HEAD_ICON("/user/change_head_img"),
	/**
	 * 搜索好友
	 */
	OPT_SEARCH_FRIENDD("/friend/searchfriend"),
	/**
	 * 修改详细信息
	 */
	OPT_MODIFY_DETAIL_INFO("/user/update_user_detail"),
	/**
	 * 修改密码
	 */
	OPT_MODIFY_PWD("/user/change_pwd"),
	/**
	 * 查看个人基本信息
	 */
	OPT_BASE_INFO_VIEW("/user/basic_info_view"),
	/**
	 * 查看个人详细信息
	 */
	OPT_DETAIL_INFO_VIEW("/user/get_user_detail"),
	/**
	 * 查看朋友圈好友的详细资料
	 */
	OPT_GET_CONTACT_DETAIL("/friend/get_friend_info"),
	/**
	 * 
	 * 获取民族列表
	 */
	OPT_GET_NATION_LIST("/common/nation"),
	/**
	 * 获取单位属性列表
	 */
	OPT_GET_OrganizationType_LIST("/common/organization_type"),
	/**
	 * 获取企业上市情况
	 */
	OPT_GET_organization_Market_LIST("/common/organization_market"),
	/**
	 * 获取企业列表
	 */
	OPT_GET_COMPANY_LIST("/company/get_company_list"),
	/**
	 * 获取公司信息
	 */
	OPT_GET_COMPANY_DETAIL("/company/get_company"),
	/**
	 * 获取个人标签信息
	 */
	OPT_GET_USER_TAG("/user/get_user_tag"),
	/**
	 * 修改个人的基本信息
	 */
	OPT_CHANGE_BASIC_INFO("/user/change_basic_info"),
	/**
	 * 企业推荐者列表
	 */
	OPT_COMPANY_RECOMMEND_LIST("/company/get_companyrecommend_list"),
	/**
	 * 获取当前的博雅之星
	 */
	OPT_GET_CURRENT_STAR("/star/get_current_star"),
	/**
	 * 获取博雅之星历史列表
	 */
	OPT_GET_STAR_HISTORY_LIST("/star/get_star_history"),
	/**
	 * 获取酒店列表/hotel/get_list
	 */
	OPT_HOTEL_GETLIST("/hotel/get_list"),

	/**
	 * 省市列表
	 */
	OPT_PROVINCE_CITY_LIST("/common/areas"),

	/**
	 * 班级列表
	 */
	OPT_GET_CLASS_LIST("/user/get_class_list"),

	/**
	 * 课程列表
	 */
	OPT_GET_COURSE_LIST("/class_course/get_list"),

	/**
	 * 课程信息
	 */
	OPT_GET_COURSE_INFO("/class_course/get_info"),

	/**
	 * 活动列表
	 */
	OPT_GET_ACTIVITY_LIST("/class_activity/get_list"),
	/**
	 * 活动信息
	 */
	OPT_GET_ACTIVITY_INFO("/class_activity/get_info"),
	/**
	 * 评论列表
	 */
	OPT_GET_COMMENT_LIST("/class_activity/get_comment_list"),

	/**
	 * 附近的人
	 */
	OPT_NEARBY_PEOPLE("/friend/nearbypeople"),

	/**
	 * 获取朋友动态
	 */
	OPT_GET_FRIENDS_LIST("/friend/get_friend_trends"),
	/**
	 * 获取某个用户个人的动态列表
	 */
	OPT_GET_PERSONAL_TRENDS_LIST("/friend/get_trends"),
	/**
	 * 赞
	 */
	OPT_SET_GOOD("/friend/set_good"),
	/**
	 * 添加评论
	 */
	OPT_ADD_COMMENT("/friend/send_comment"),

	/**
	 * 参加or请假
	 */
	OPT_JOIN_OR_LEAVE("/classes/leave"),

	/**
	 * 酒店列表
	 */
	OPT_GET_HOTEL_LIST("/hotel/get_list"),

	/**
	 * 活动赞
	 */
	OPT_ACTIVITY_RAISE("/class_activity/raise"),

	/**
	 * 活动评论
	 */
	OPT_ACTIVITY_COMMENT("/class_activity/comment"),
	/**
	 * 课件，班报列表
	 */
	OPT_GET_SEARCH_LIST("/classes/class_search_list"),
	/**
	 * 发布活动
	 */
	OPT_ACTIVITY_EXERCISE_PUBLISH("/class_activity/post_activity"),
	/**
	 * 获取好友的相册
	 */
	OPT_GET_FRINED_IMAGES("/friend/get_friend_images"),
	/**
	 * 发表动态
	 */
	OPT_FRIEND_SEND_MSG("/friend/send_message"),
	/**
	 * 发布签到
	 */
	OPT_SIGNIN_PUBLISH("/classes/pub_sign"),
	/**
	 * 中心课程
	 */
	OPT_GET_CENTER_CLASS("/course/course_list"),
	/**
	 * 版本升级
	 */
	OPT_APP_UPGRADE("/system/checkver"),
	/**
	 * 企业地图列表
	 */
	OPT_COMPANY_MAP_LIST("/company/get_company_map"),
	/**
	 * 修改企业的地理位置信息
	 */
	OPT_MODIFY_COMPANY_MAP_LOCATION("/company/update_company_map"),
	/**
	 * 获取活动列表
	 */
	OPT_GET_CENTER_ACTIVES("/activity/get_list"),
	/**
	 * 获取活动信息
	 */
	OPT_GET_CENTER_ACTIVE_INFO("/activity/get_info"),
	/**
	 * 获取评论列表
	 */
	OPT_GET_ACTIVE_COMMENTS("/activity/get_comment_list"),
	/**
	 * 发表评论
	 */
	OPT_SEND_ACTIVE_COMMENT("/activity/comment"),
	/**
	 * 发表赞
	 */
	OPT_SEND_ACTIVE_COMPLIMENT("/activity/raise"),
	/**
	 * 参加
	 */
	OPT_JOIN("/activity/join_or_leave"),
	/**
	 * 取消参加
	 */
	OPT_QUIT_JOIN("/activity/cancel_join_or_leave"),
	;

	private String opt;
	private RequestMethod requestMethod = RequestMethod.POST;
	private int retryNumber = 1;

	private ServerInterfaceDefinition(String opt) {
		this.opt = opt;
	}

	private ServerInterfaceDefinition(String opt, RequestMethod requestMethod) {
		this.opt = opt;
		this.requestMethod = requestMethod;
	}

	private ServerInterfaceDefinition(String opt, RequestMethod requestMethod, int retryNumber) {
		this.opt = opt;
		this.requestMethod = requestMethod;
		this.retryNumber = retryNumber;
	}

	public String getOpt() {
		return opt;
	}

	public RequestMethod getRequestMethod() {
		return requestMethod;
	}

	public int getRetryNumber() {
		return retryNumber;
	}

	public enum RequestMethod {
		POST("POST"), GET("GET");
		private String requestMethodName;

		RequestMethod(String requestMethodName) {
			this.requestMethodName = requestMethodName;
		}

		public String getRequestMethodName() {
			return requestMethodName;
		}
	}
}
