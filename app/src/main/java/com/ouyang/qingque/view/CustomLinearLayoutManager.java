
package com.ouyang.qingque.view;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomLinearLayoutManager extends LinearLayoutManager {
	private boolean isScrollEnabled = true;

	public CustomLinearLayoutManager(Context context) {
		super(context);
	}

	public void setScrollEnabled(boolean flag) {
		this.isScrollEnabled = flag;
	}

	@Override
	public boolean canScrollVertically() {
		//Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
		return isScrollEnabled && super.canScrollVertically();
	}
}