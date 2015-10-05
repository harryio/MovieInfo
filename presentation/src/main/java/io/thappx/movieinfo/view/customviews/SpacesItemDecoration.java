package io.thappx.movieinfo.view.customviews;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
	int mSpace;

	public SpacesItemDecoration(int pSpace) {
		mSpace = pSpace;
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
			state) {
		super.getItemOffsets(outRect, view, parent, state);
		outRect.set(mSpace, mSpace, mSpace, mSpace);
	}
}
