package com.cblue.framework.util;

import android.content.Context;

/**
 * Density（密度）
 * 在Android开发我们一般都可以不需要使用px的,但是某一些控件的属性没有直接支持dip,像下面的代码
 * android.view.ViewGroup.LayoutParams.height
 * android.view.ViewGroup.LayoutParams.width
 * 上面这两个属性的单位为像素,但是为了兼容多种分辨率的手机,我们需要最好使用dip,时候我们可以调用以下的代码进行转换.
 * int heightPx= DisplayUtil.dip2px(this, 33);
 * mTabHost.getTabWidget().getChildAt(i).getLayoutParams().height = heightPx;
 * @author Administrator
 *
 */
public class DensityUtil {

	/**
	 * 根据手机的分辨率dp的单位转成px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率px(像素) 的单位转成dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 获取屏幕宽度
	 * 
	 * @date 2013年7月23日
	 * @param context
	 * @return
	 */
	public static int getWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @date 2013年7月23日
	 * @param context
	 * @return
	 */
	public static int getHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}
}