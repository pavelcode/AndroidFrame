package com.cblue.framework.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.BadTokenException;
import android.widget.Toast;

import com.cblue.framework.R;
import com.cblue.framework.application.SoftApplication;
import com.cblue.framework.network.Request;
import com.cblue.framework.network.HttpRequestAsyncTask.OnCompleteListener;
import com.cblue.framework.util.NetUtil;



/**
 * Activity基础类
 * 基本逻辑分开：设定布局--布局之前执行--初始化布局控件--布局之后执行
 * 屏幕宽高，是否全屏
 * Toast显示
 * 网络请求
 * 显示或隐藏进度条
 * @author Administrator
 *
 */
public abstract class BaseActivity extends Activity implements OnClickListener{
	
	protected SoftApplication softApplication;
	public boolean isAllowFullScreen;//是否允许全屏
	public boolean hasMenu;//是否有菜单显示
	private ProgressDialog progressDialog;
	protected Resources resources;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		resources=getResources();
		softApplication = (SoftApplication) getApplicationContext();
		SoftApplication.unDestroyActivityList.add(this);
		if(isAllowFullScreen){
			setFullScreen(true);
		}else{
			setFullScreen(false);
		}
		setContentLayout();
		dealLogicBeforeInitView();
		initView();
		dealLogicAfterInitView();
		
	}
	
	/**
	 * 设置布局文件
	 */
	public abstract void setContentLayout();
	/**
	 * 在实例化布局之前处理的逻辑
	 */
	public abstract void dealLogicBeforeInitView();
	/**
	 * 实例化布局文件/组件
	 */
	public abstract void initView();
	/**
	 * 在实例化布局之后处理的逻辑
	 */
	public abstract void dealLogicAfterInitView();
	
	/**
	 * 得到屏幕宽度
	 * @return 宽度
	 */
	public int getScreenWidth() {
		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        return screenWidth;
	}
	/**
	 * 得到屏幕高度
	 * @return 高度
	 */
	public int getScreenHeight() {
		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenHeight = dm.heightPixels;
        return screenHeight;
	}
	
	/**
	 * 是否全屏和显示标题，true为全屏和无标题，false为无标题，请在setContentView()方法前调用
	 * @param fullScreen
	 */
	public void setFullScreen(boolean fullScreen) {
		if(fullScreen) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		} else {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		
	}
    /**
     * 短时间显示Toast
     * @param info 显示的内容
     */
	public void showToast(String info) {
		Toast.makeText(this,info, Toast.LENGTH_SHORT).show();
	}
	/**
	 * 长时间显示Toast
	 * @param info 显示的内容
	 */
	public void showToastLong(String info) {
		Toast.makeText(this,info, Toast.LENGTH_LONG).show();
	}
	/**
     * 短时间显示Toast
     * @param info 显示的内容
     */
	public void showToast(int resId){
		Toast.makeText(this,resId, Toast.LENGTH_SHORT).show();
	}
	/**
	 * 长时间显示Toast
	 * @param info 显示的内容
	 */
	public void showToastLong(int resId){
		Toast.makeText(this,resId, Toast.LENGTH_LONG).show();
	}
	/**
	 * onClick方法的封装，在此方法中处理点击
	 * @param view 被点击的View对象
	 */
	public abstract void onClickEvent(View view);
	
	/**
	 * 普通联网获取数据
	 * @param <T>
	 * @param request
	 * @param onCompleteListener
	 */
	@SuppressWarnings("rawtypes")
	public void getNetWorkDate(Request request,OnCompleteListener onCompleteListener) {
		if(NetUtil.isNetDeviceAvailable(softApplication)) {		
			softApplication.requestNetWork(request,onCompleteListener);
		}else {
			showToast(R.string.network_is_not_available);
		}
	}
	
	@Override
	public void onClick(View v) {
		onClickEvent(v);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		SoftApplication.unDestroyActivityList.remove(this);
	}
	
	/**
	 * 显示正在加载的进度条
	 * 
	 */
	public void showProgressDialog(){
		if(progressDialog != null && progressDialog.isShowing()){
			progressDialog.dismiss();
			progressDialog = null;
		}
		progressDialog = new ProgressDialog(BaseActivity.this);
		progressDialog.setMessage("加载中...");
		try {
			progressDialog.show();
		}catch (BadTokenException exception) {
			exception.printStackTrace();
		}
	}
	
	public void showProgressDialog(String msg){
		if(progressDialog != null && progressDialog.isShowing()){
			progressDialog.dismiss();
			progressDialog = null;
		}
		progressDialog = new ProgressDialog(BaseActivity.this);
		progressDialog.setMessage(msg);
		try {
			progressDialog.show();
		}catch (BadTokenException exception) {
			exception.printStackTrace();
		}
	}
	
	public void showProgressDialog2(){
		OnKeyListener keyListener = new OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_HOME
						|| keyCode == KeyEvent.KEYCODE_SEARCH) {
					return true;
				}
				return false;
			}
		};
		if(progressDialog != null && progressDialog.isShowing()){
			progressDialog.dismiss();
			progressDialog = null;
		}
		progressDialog = new ProgressDialog(this,R.style.MyDialog);
		progressDialog.setCanceledOnTouchOutside(true);
		progressDialog.setIndeterminate(false);
		progressDialog.setOnKeyListener(keyListener);
		progressDialog.setCancelable(true);
		progressDialog.show();
		progressDialog.setContentView(R.layout.dialog_loading_process);
	}
	
	public ProgressDialog createProgressDialog(String msg){
		ProgressDialog progressDialog = new ProgressDialog(BaseActivity.this);
		progressDialog.setMessage(msg);
		return progressDialog;
	}
	
	/**
	 * 隐藏正在加载的进度条
	 * 
	 */
	public void dismissProgressDialog(){
		if(null != progressDialog && progressDialog.isShowing() == true) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}
	
}
