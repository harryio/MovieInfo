package io.thappx.movieinfo.view.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;

import io.thappx.movieinfo.R;
import io.thappx.movieinfo.internal.di.HasComponent;

public abstract class BaseFragment extends Fragment {
	private int mActionBarSize;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@SuppressWarnings("unchecked")
	protected <C> C getComponent(Class<C> componentType) {
		return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
	}

	protected int getActionBarSize() {
		if (mActionBarSize == 0) {
			TypedValue typedValue = new TypedValue();
			int[] textSizeAttr = new int[]{R.attr.actionBarSize};
			int indexOfAttrTextSize = 0;
			TypedArray a = getActivity()
					.obtainStyledAttributes(typedValue.data, textSizeAttr);
			int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
			a.recycle();
			mActionBarSize = actionBarSize;
		}

		return mActionBarSize;
	}
}
