package io.thappx.movieinfo.view.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;

public class AutoFitRecyclerView extends RecyclerView {
	private GridLayoutManager mLayoutManager;
	private int mColumnWidth = -1;

	public AutoFitRecyclerView(Context context) {
		super(context);
		init(context, null);
	}

	public AutoFitRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public AutoFitRecyclerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {

		if (attrs != null) {

			int[] attrsArray = {
					android.R.attr.columnWidth
			};

			TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
			mColumnWidth = array.getDimensionPixelSize(0, -1);
			array.recycle();
		}

		mLayoutManager = new GridLayoutManager(getContext(), 2);
		setLayoutManager(mLayoutManager);
	}

	@Override
	protected void onMeasure(int widthSpec, int heightSpec) {

		super.onMeasure(widthSpec, heightSpec);

		if (mColumnWidth > 0) {

			int spanCount = Math.max(1, getMeasuredWidth() / mColumnWidth);
			mLayoutManager.setSpanCount(spanCount);
		}
	}

	@Override
	protected void attachLayoutAnimationParameters(View child,
												   ViewGroup.LayoutParams params,
												   int index, int count) {

		if (getAdapter() != null && getLayoutManager() instanceof GridLayoutManager){

			GridLayoutAnimationController.AnimationParameters animationParams =
					(GridLayoutAnimationController.AnimationParameters) params.layoutAnimationParameters;

			if (animationParams == null) {
				animationParams = new GridLayoutAnimationController.AnimationParameters();
				params.layoutAnimationParameters = animationParams;
			}

			int columns = ((GridLayoutManager) getLayoutManager()).getSpanCount();

			animationParams.count = count;
			animationParams.index = index;
			animationParams.columnsCount = columns;
			animationParams.rowsCount = count / columns;

			final int invertedIndex = count - 1 - index;
			animationParams.column = columns - 1 - (invertedIndex % columns);
			animationParams.row = animationParams.rowsCount - 1 - invertedIndex / columns;

		} else {
			super.attachLayoutAnimationParameters(child, params, index, count);
		}
	}

	@Override
	public void scrollTo(int x, int y) {
		super.scrollTo(x, y);
	}
}
