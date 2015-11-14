package io.thappx.movieinfo.view.fragment;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.thappx.movieinfo.R;
import io.thappx.movieinfo.databinding.FragmentMovieDetailBinding;
import io.thappx.movieinfo.internal.di.components.MovieComponent;
import io.thappx.movieinfo.model.CastModel;
import io.thappx.movieinfo.model.MovieDetailModel;
import io.thappx.movieinfo.model.MovieModel;
import io.thappx.movieinfo.presenter.MovieDetailPresenter;
import io.thappx.movieinfo.view.MovieCastView;
import io.thappx.movieinfo.view.MovieDetailView;
import io.thappx.movieinfo.view.activity.MovieDetailActivity;

public class MovieDetailFragment extends BaseFragment
		implements MovieCastView, MovieDetailView {
	private static final String ARG_MOVIE = "io.theappx.movieinfo.arg_movie";

	private FragmentMovieDetailBinding lBinding;

	@Inject
	MovieDetailPresenter mMovieDetailPresenter;
	@Bind(R.id.toolbar)
	Toolbar mToolbar;

	private MovieModel mMovie;

//	private OnFragmentInteractionListener mListener;

	public static MovieDetailFragment newInstance(MovieModel pMovie) {
		MovieDetailFragment fragment = new MovieDetailFragment();
		Bundle args = new Bundle();
		args.putParcelable(ARG_MOVIE, pMovie);
		fragment.setArguments(args);
		return fragment;
	}

	public MovieDetailFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mMovie = getArguments().getParcelable(ARG_MOVIE);
		}
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		lBinding = FragmentMovieDetailBinding
				.inflate(inflater, container, false);
		lBinding.setMovie(mMovie);
		View view = lBinding.getRoot();
		ButterKnife.bind(this, view);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setUpToolbar();
		initialize();
	}

	private void setUpToolbar() {
		try {
			MovieDetailActivity lActivity = (MovieDetailActivity)
					getActivity();
			lActivity.setSupportActionBar(mToolbar);

			ActionBar lActionBar = lActivity.getSupportActionBar();
			if (lActionBar != null) {
				lActionBar.setDisplayHomeAsUpEnabled(true);
				lActionBar.setDisplayShowTitleEnabled(false);
			}
		} catch (NullPointerException | ClassCastException e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		getComponent(MovieComponent.class).inject(this);
		mMovieDetailPresenter.setCastView(this);
		mMovieDetailPresenter.setDetailView(this);
		mMovieDetailPresenter.initialize();
	}

	@Override
	public void showCastError(String pMessage) {
		showToast(pMessage);
	}

	@Override
	public void showCast(CastModel pCastModel) {
		lBinding.setCastModel(pCastModel);
	}

	@Override
	public void showMovieDetailError(String pMessage) {
		showToast(pMessage);
	}

	@Override
	public void showMovie(MovieDetailModel pMovieDetailModel) {
		lBinding.setMovieDetail(pMovieDetailModel);
	}

	private void showToast(String pMessage) {
		Toast.makeText(getContext(), pMessage, Toast.LENGTH_SHORT).show();
	}

	//	@Override
//	public void onAttach(Activity activity) {
//		super.onAttach(activity);
//		try {
//			mListener = (OnFragmentInteractionListener) activity;
//		} catch (ClassCastException e) {
//			throw new ClassCastException(activity.toString()
//					+ " must implement OnFragmentInteractionListener");
//		}
//	}
//
//	@Override
//	public void onAttach(Context context) {
//		super.onAttach(context);
//
//		try {
//			MovieDetailActivity lActivity = (MovieDetailActivity) context;
//			mListener = (OnFragmentInteractionListener) lActivity;
//		} catch (ClassCastException e) {
//			e.printStackTrace();
//			throw new ClassCastException("Context cannot be converted to" +
//					"MovieDetailActivity ");
//		}
//	}
//
//	@Override
//	public void onDetach() {
//		super.onDetach();
//		mListener = null;
//	}
//
//	public interface OnFragmentInteractionListener {
//	}

	@Override
	public void onResume() {
		super.onResume();
		mMovieDetailPresenter.resume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mMovieDetailPresenter.pause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMovieDetailPresenter.destroy();
	}
}
