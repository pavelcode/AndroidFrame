package com.cblue.service.user.activity;


import java.util.HashMap;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cblue.framework.activity.BaseActivity;
import com.cblue.framework.bean.BaseResponse;
import com.cblue.framework.bean.LoginResponse;
import com.cblue.framework.network.HttpRequestAsyncTask;
import com.cblue.framework.network.Request;
import com.cblue.framework.network.ServerInterfaceDefinition;
import com.cblue.framework.network.HttpRequestAsyncTask.OnCompleteListener;
import com.cblue.framework.network.ServerInterfaceDefinition.RequestMethod;
import com.cblue.framework.parser.BaseParser;
import com.cblue.framework.parser.LoginBaseParser;
import com.cblue.framework.util.LogUtil;
import com.cblue.framework.util.NetUtil;
import com.cblue.service.user.bean.UserInfo;
import com.xingrun.kuaixue.R;

public class LoginActivity extends BaseActivity {
	
	
	private static final String TAG =LoginActivity.class.getSimpleName();
	private UserInfo userInfo;
    private LoginBaseParser loginBaseParser;
	
	private EditText login_et_name;
	private EditText login_et_password;
	private Button   login_btn_doLogin;
	private Button   login_btn_quit;


	
	private String name="18600510596";
	private String password="123456";

	
	/* (non-Javadoc)
	 * @see com.xingrun.kuaixue.framework.activity.BaseActivity#setContentLayout()
	 * 设置布局
	 */
	@Override
	public void setContentLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.login);
	}
	
	/* (non-Javadoc)
	 * @see com.xingrun.kuaixue.framework.activity.BaseActivity#dealLogicBeforeInitView()
	 * 设置布局后的逻辑处理
	 */
	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub
		  userInfo =  UserInfo.getUserInfoInstance();
		  loginBaseParser = new LoginBaseParser();
		
		
	}

	/* (non-Javadoc)
	 * @see com.xingrun.kuaixue.framework.activity.BaseActivity#initView()
	 * 初始化控件
	 */
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		login_et_name  = (EditText) findViewById(R.id.login_name);
		login_et_password = (EditText)findViewById(R.id.login_password);
		login_btn_doLogin = (Button)findViewById(R.id.login_doLog);
		login_btn_quit = (Button)findViewById(R.id.login_quit);
		
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.xingrun.kuaixue.framework.activity.BaseActivity#dealLogicAfterInitView()
	 * 请求网络的方法
	 */
	@Override
	public void dealLogicAfterInitView() {
		// TODO Auto-generated method stub
		login_btn_doLogin.setOnClickListener(this);
		login_btn_quit.setOnClickListener(this);
		
	}

	/* (non-Javadoc)
	 * @see com.xingrun.kuaixue.framework.activity.BaseActivity#onClickEvent(android.view.View)
	 * 点击事件
	 */
	@Override
	public void onClickEvent(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.login_doLog:
			showToast("登录");
			showProgressDialog();
			/*try {
				Thread.sleep(3*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		    name = login_et_name.getText().toString().trim();
		    password = login_et_password.getText().toString().trim();
		    LogUtil.log(TAG, Log.INFO,"name="+name+";password="+password);
			userInfo.setUser_name(name);
			userInfo.setUser_password(password);
			if(NetUtil.isNetAvailable(softApplication)){
				Login(userInfo);
			}else{
				showToast("网络未连接");
			}
			break;
		case R.id.login_quit:
			showToast("退出");
			softApplication.quit();
			break;
		}
		
		
	}
	
	private void Login(UserInfo userInfo) {
		// TODO Auto-generated method stub
		ServerInterfaceDefinition optLogin = ServerInterfaceDefinition.OPT_LOGIN;
		RequestMethod post = RequestMethod.POST;
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("zhanghao", userInfo.getUser_name());
		map.put("password",userInfo.getUser_password());
		//构建请求
		Request request=new Request(optLogin, map, new BaseParser<BaseResponse>() {

			@Override
			public BaseResponse parse(String paramString) {
				// TODO Auto-generated method stub
				//得到JSON字符串,解析
				LogUtil.log(TAG, Log.INFO,"paramString="+paramString);
				LoginResponse loginResponse = loginBaseParser.parse(paramString);
				
				return loginResponse;
			}
		});
		
	
		
		HttpRequestAsyncTask ha=new HttpRequestAsyncTask();
		ha.setOnCompleteListener(new OnCompleteListener() {

			@Override
			public void onComplete(Object result, String resultString) {
				// TODO Auto-generated method stub
				dismissProgressDialog();
				//解析成对象
				LogUtil.log(TAG, Log.INFO,"resultString="+resultString);
				//LoginResponse loginResponse = (LoginResponse)result;
				//保存缓存中
				//显示
				
				
			}
		});
		ha.execute(request);
	}
	
}
