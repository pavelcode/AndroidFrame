package com.cblue.service.user.bean;

import java.io.Serializable;

public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7186526560446769302L;

	private String user_id;
	private String user_name;
	private String user_password;
	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	private String user_email;
	private String phone;
	private static UserInfo uif = null;

	private UserInfo() {
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static UserInfo getUif() {
		return uif;
	}

	public static void setUif(UserInfo uif) {
		UserInfo.uif = uif;
	}

	public static UserInfo getUserInfoInstance() {
		if (uif == null) {
			uif = new UserInfo();
		}

		return uif;
	}
}
