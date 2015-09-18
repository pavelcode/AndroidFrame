package com.cblue.framework.bean;

import java.io.Serializable;

/**
 * 基本响应对象
 * @author Administrator
 *
 */
public abstract class BaseResponse implements Serializable{
	
	private static final long serialVersionUID = 5375804597574885028L;
	public int code = -10000;
	public String msg;
}
