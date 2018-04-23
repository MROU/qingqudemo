package com.ouyang.qingque.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 
 * @author OU
 * @createdate 2014年7月9日
 * @description 得到屏幕的宽高密度等
 */
public class ScreenInfo {
	private Activity activity;
	/** 屏幕宽度（像素） */
	private int width;
	/** 屏幕高度（像素） */
	private int height;
	/** 屏幕密度（0.75 / 1.0 / 1.5） */
	private float density;
	/** 屏幕密度DPI（120 / 160 / 240） */
	private int densityDpi;

	public ScreenInfo(Activity activity) {
		this.activity = activity;
		init();
	}

	private void init() {
		DisplayMetrics metric = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		width = metric.widthPixels;
		height = metric.heightPixels;
		density = metric.density;
		densityDpi = metric.densityDpi;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public int getDensityDpi() {
		return densityDpi;
	}

	public void setDensityDpi(int densityDpi) {
		this.densityDpi = densityDpi;
	}

	//获取屏幕的宽度
	public static int getScreenWidth(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		return display.getWidth();
	}

	//获取屏幕的高度
	public static int getScreenHeight(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		return display.getHeight();
	}

	/**
	 * 将view转为正方形布局
	 * @param context
	 * @param view
	 * @param layout_type 0 代表RelativeLayout 1 代表 LinerLayout
	 */
	public static void toSquare(Context context, View view, int layout_type){
		int screenWidth = getScreenWidth(context);
		ViewGroup.LayoutParams lp=null;
		if(0==layout_type){
			lp=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		}else if(1==layout_type){
			lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		}else if(2==layout_type){
			lp=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		}
		if(lp==null)
			return;
		lp.width = screenWidth/2;
		lp.height =screenWidth/2;
		view.setLayoutParams(lp);
	}


	/**
	 * 将view转为有比率的布局
	 * @param context
	 * @param view
	 * @param layout_type 0 代表RelativeLayout 1 代表 LinerLayout
	 */
	public static void toScale(Context context, View view, int layout_type, int widthScale, int heightScale){
		int screenWidth = getScreenWidth(context);
		ViewGroup.LayoutParams lp=null;
		if(0==layout_type){
			lp=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		}else if(1==layout_type){
			lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		}
		if(lp==null)
			return;
		
		lp.width = screenWidth;
		lp.height =(screenWidth*widthScale)/heightScale;
		view.setLayoutParams(lp);
	}

}
