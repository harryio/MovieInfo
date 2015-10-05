package io.thappx.movieinfo.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.appx.domain.model.Movie;
import io.thappx.movieinfo.R;
import io.thappx.movieinfo.internal.di.components.MovieComponent;
import io.thappx.movieinfo.model.MovieModel;
import io.thappx.movieinfo.presenter.MovieListPresenter;
import io.thappx.movieinfo.view.MovieListView;
import io.thappx.movieinfo.view.activity.MovieListActivity;
import io.thappx.movieinfo.view.adapter.MovieListAdapter;
import io.thappx.movieinfo.view.customviews.SpacesItemDecoration;

public class MovieListFragment extends BaseFragment
		implements MovieListView {
	private static final String ARG_TYPE = "io.theappx.movieinfo.argType";

	@Bind(R.id.rv_movies)
	RecyclerView mRecyclerView;
	@Bind(R.id.progressView)
	View mLoadingView;
	@Bind(R.id.errorView)
	View mErrorView;
	@Bind(R.id.tv_error)
	TextView mErrorTextView;

	@Inject
	MovieListPresenter mPresenter;

	private OnFragmentInteractionListener mListener;
	private MovieListAdapter mAdapter;
	private boolean isScrolling = false;

	public static MovieListFragment newInstance(@Movie.Type String pMovieType) {
		MovieListFragment fragment = new MovieListFragment();
		Bundle args = new Bundle();
		args.putString(ARG_TYPE, pMovieType);
		fragment.setArguments(args);
		return fragment;
	}

	public MovieListFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
		ButterKnife.bind(this, view);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initialize();

		Bundle lBundle = getArguments();
		if (lBundle != null) {
			@Movie.Type String lMovieType = lBundle.getString(ARG_TYPE);
			mPresenter.setMovieType(lMovieType);
		}

		setUpRecyclerView();
		loadMovieList();
	}

	private void loadMovieList() {
		mPresenter.initialize();
	}

	private void initialize() {
		getComponent(MovieComponent.class).inject(this);
		mPresenter.setView(this);
	}

	private void setUpRecyclerView() {
		mRecyclerView.setHasFixedSize(true);
		mAdapter = new MovieListAdapter(new ArrayList<MovieModel>());
		mAdapter.setUpListener(listItemClickListener);
		int spacingInPixels = getResources()
				.getDimensionPixelSize(R.dimen.spacing);
		mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels ));
		mRecyclerView.setAdapter(mAdapter);
		mRecyclerView.getScrollY();
	}

	@Override
	public void renderMovieList(List<MovieModel> movieModelCollection) {
		mAdapter.setMovieList(movieModelCollection);
	}

	@Override
	public void viewMovie(MovieModel pMovieModel, int[] pLoc) {
		if (mListener != null) {
			mListener.onListItemClicked(pMovieModel, pLoc);
		}
	}

	@Override
	public void showLoading() {
		mLoadingView.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoading() {
		if (mLoadingView != null) {
			mLoadingView.setVisibility(View.GONE);
		}
	}

	@Override
	public void showRetry() {

	}

	@Override
	public void hideRetry() {

	}

	@OnClick(R.id.btn_retry)
	public void onRetryButtonClicked() {
		mPresenter.initialize();
	}

	@Override
	public void showError(String message) {
		mErrorView.setVisibility(View.VISIBLE);
		mErrorTextView.setText(message);
	}

	@Override
	public void hideError() {
		mErrorView.setVisibility(View.GONE);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);

		try {
			mListener = (MovieListActivity) context;
		} catch (ClassCastException e) {
			throw new ClassCastException("Cannot cast MovieListActivity" );
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	public interface OnFragmentInteractionListener {
		void onListItemClicked(MovieModel pMovieModel, int[] ploc);
	}

	private MovieListAdapter.OnItemClickListener listItemClickListener =
			new MovieListAdapter.OnItemClickListener() {
				@Override
				public void onMovieItemClicked(MovieModel pMovieModel, int[] pLoc) {
					MovieListFragment.this.mPresenter.onListItemClicked(pMovieModel, pLoc);
				}
			};

	@Override
	public void onResume() {
		super.onResume();

		mPresenter.resume();
	}

	@Override
	public void onPause() {
		super.onPause();

		mPresenter.pause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		mPresenter.destroy();
		mPresenter = null;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();

		ButterKnife.unbind(this);
	}
}
