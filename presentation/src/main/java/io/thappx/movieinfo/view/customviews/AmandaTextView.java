package io.thappx.movieinfo.view.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

public class AmandaTextView extends TextView {
	public AmandaTextView(Context context) {
		super(context);
		init();
	}

	public AmandaTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public AmandaTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public AmandaTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	public void init() {
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "amanda.ttf");
		setTypeface(tf, 1);
	}
}
