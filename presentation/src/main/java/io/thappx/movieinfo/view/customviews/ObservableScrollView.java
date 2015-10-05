package io.thappx.movieinfo.view.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView {
	private ScrollViewListener mScrollViewListener = null;

	public ObservableScrollView(Context context) {
		super(context);
	}

	public ObservableScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr, int
			defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public void setScrollViewListener(ScrollViewListener pScrollViewListener) {
		mScrollViewListener = pScrollViewListener;
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		super.onScrollChanged(x, y, oldx, oldy);
		if(mScrollViewListener != null) {
			mScrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
		}
	}
}
