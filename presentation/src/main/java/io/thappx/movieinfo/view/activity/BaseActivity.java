package io.thappx.movieinfo.view.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;

import javax.inject.Inject;

import io.thappx.movieinfo.MovieInfoApplication;
import io.thappx.movieinfo.R;
import io.thappx.movieinfo.internal.di.components.ApplicationComponent;
import io.thappx.movieinfo.internal.di.modules.ActivityModule;
import io.thappx.movieinfo.navigation.Navigator;

public abstract class BaseActivity extends AppCompatActivity {
	@Inject
	Navigator mNavigator;

	private int mActionBarSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getApplicationComponent().inject(this);
	}

	protected ApplicationComponent getApplicationComponent() {
		return ((MovieInfoApplication) getApplication()).getApplicationComponent();
	}

	protected ActivityModule getActivityModule() {
		return new ActivityModule(this);
	}

	protected int getActionBarSize() {
		if (mActionBarSize == 0) {
			TypedValue typedValue = new TypedValue();
			int[] textSizeAttr = new int[]{R.attr.actionBarSize};
			int indexOfAttrTextSize = 0;
			TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
			int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
			a.recycle();
			mActionBarSize = actionBarSize;
		}

		return mActionBarSize;
	}
}
