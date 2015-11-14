package io.thappx.movieinfo.view.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.thappx.movieinfo.R;
import io.thappx.movieinfo.databinding.LayoutMovieItemBinding;
import io.thappx.movieinfo.model.MovieModel;

public class MovieListAdapter
		extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
	private List<MovieModel> mMovieList;
	private OnItemClickListener mItemClickListener;
	private Context mContext;

	public MovieListAdapter(Context pContext, List<MovieModel> pMovieList) {
		if (pMovieList == null)
			throw new NullPointerException("Movie list in adapter cannot be null");

		mContext = pContext;
		mMovieList = pMovieList;
	}

	static class MovieViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.img_poster)
		ImageView mPosterImageView;
		@Bind(R.id.tv_title)
		TextView mTitleTextView;
		@Bind(R.id.v_root_item)
		View mRootView;
		LayoutMovieItemBinding mBinding;

		private OnItemClickListener mOnItemClickListener;
		private MovieModel mMovieModel;

		public MovieViewHolder(View itemView) {
			super(itemView);

			mBinding = DataBindingUtil.bind(itemView);
			ButterKnife.bind(this, itemView);
		}

		@OnClick(R.id.v_root_item)
		public void onItemClicked() {
			int[] rootViewPosition = new int[2];
			mRootView.getLocationOnScreen(rootViewPosition);
			mOnItemClickListener.onMovieItemClicked(mMovieModel, rootViewPosition);
		}

		void setOnItemClickListener(OnItemClickListener pOnItemClickListener) {
			mOnItemClickListener = pOnItemClickListener;
		}

		void setMovie(MovieModel pMovie) {
			mBinding.setMovie(pMovie);
			mMovieModel = pMovie;
		}
	}

	@Override
	public MovieListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemLayout = LayoutInflater
				.from(parent.getContext()).inflate(R.layout.layout_movie_item, parent, false);

		MovieViewHolder lMovieViewHolder = new MovieViewHolder(itemLayout);
		lMovieViewHolder.setOnItemClickListener(mItemClickListener);
		return lMovieViewHolder;
	}

	@Override
	public void onBindViewHolder(MovieListAdapter.MovieViewHolder holder, int position) {
		MovieModel mMovie = mMovieList.get(position);
		holder.setMovie(mMovie);
	}

	@Override
	public int getItemCount() {
		return mMovieList.size();
	}

	public void setUpListener(OnItemClickListener pItemClickListener) {
		mItemClickListener = pItemClickListener;
	}

	public void setMovieList(List<MovieModel> pMovieList) {
		validateMovieList(pMovieList);
		mMovieList = pMovieList;
		notifyDataSetChanged();
	}

	private void validateMovieList(List<MovieModel> pMovieList) {
		if (pMovieList == null)
			throw new NullPointerException("Movie List in adapter cannot be null");

	}

	public interface OnItemClickListener {
		void onMovieItemClicked(MovieModel pMovieModel, int[] pLoc);
	}

	@BindingAdapter("app:imageUrl")
	public static void setMovieImage(ImageView pMovieImage, String pUrl) {
		Picasso.with(pMovieImage.getContext())
				.load(pUrl)
                .fit()
				.into(pMovieImage);
	}
}
