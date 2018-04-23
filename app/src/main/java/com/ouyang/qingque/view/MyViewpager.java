
package com.ouyang.qingque.view;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
public class MyViewpager extends ViewPager{
	public boolean isScrollable() {
		return isScrollable;
	}
	public void setScrollable(boolean scrollable) {
		isScrollable = scrollable;
	}
	boolean isScrollable=true;
	public MyViewpager(Context context) {
		super(context);
	}
	public MyViewpager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return isScrollable && super.onTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return isScrollable && super.onInterceptTouchEvent(ev);
	}

	@Override
	public void setCurrentItem(int item, boolean smoothScroll) {
		super.setCurrentItem(item, smoothScroll);
	}

	@Override
	public void setCurrentItem(int item) {
		super.setCurrentItem(item);
	}

	@Override
	public void scrollTo(int x, int y) {
		super.scrollTo(x, y);
	}
}