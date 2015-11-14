package io.thappx.movieinfo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewTreeObserver;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.thappx.movieinfo.R;
import io.thappx.movieinfo.internal.di.HasComponent;
import io.thappx.movieinfo.internal.di.components.DaggerMovieComponent;
import io.thappx.movieinfo.internal.di.components.MovieComponent;
import io.thappx.movieinfo.internal.di.modules.MovieModule;
import io.thappx.movieinfo.model.MovieModel;
import io.thappx.movieinfo.view.customviews.RevealBackgroundView;
import io.thappx.movieinfo.view.fragment.MovieDetailFragment;

public class MovieDetailActivity extends BaseActivity
		implements HasComponent<MovieComponent>,
		RevealBackgroundView.OnStateChangeListener {
	private static final String ARG_MOVIE = "io.theappx.movieinfo.arg_movie";
	private static final String ARG_LOC = "io.theappx.movieinfo.arg_loc";
	private static final String INSTANCE_ARG_MOVIE = "io.theappx.movieinfo.instance_arg_movie";

	private MovieModel mMovieModel;
	private MovieComponent mMovieComponent;
	private int[] startingLocation;

	@Bind(R.id.v_reveal)
	RevealBackgroundView revealView;

	public static Intent getCallingIntent(Context pContext,
										  MovieModel pMovie, int[] pLoc) {
		Intent lIntent = new Intent(pContext, MovieDetailActivity.class);
		lIntent.putExtra(ARG_MOVIE, pMovie);
		lIntent.putExtra(ARG_LOC, pLoc);

		return lIntent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		ButterKnife.bind(this);

		initializeActivity(savedInstanceState);
		initializeInjector();
		setupRevealBackground(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		if (outState != null) {
			outState.putParcelable(INSTANCE_ARG_MOVIE, mMovieModel);
		}
		super.onSaveInstanceState(outState);
	}

	private void initializeActivity(Bundle pBundle) {
		if (pBundle == null) {
			mMovieModel = getIntent()
					.getParcelableExtra(ARG_MOVIE);
			startingLocation = getIntent().getIntArrayExtra(ARG_LOC);
		} else {
			mMovieModel = pBundle.getParcelable(INSTANCE_ARG_MOVIE);
		}
	}

	private void initializeInjector() {
		mMovieComponent = DaggerMovieComponent.builder()
				.applicationComponent(getApplicationComponent())
				.movieModule(new MovieModule(mMovieModel.getId()))
				.build();
	}

	@Override
	public MovieComponent getComponent() {
		return mMovieComponent;
	}

	private void setupRevealBackground(Bundle savedInstanceState) {
		revealView.setOnStateChangeListener(this);
		if (savedInstanceState == null) {
			revealView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
				@Override
				public boolean onPreDraw() {
					Log.d("DetailFragment", "starting location: "
							+ startingLocation[0] + " " + startingLocation[1]);
					revealView.getViewTreeObserver().removeOnPreDrawListener(this);
					revealView.startFromLocation(startingLocation);
					return true;
				}
			});
		} else {
			revealView.setToFinishedFrame();
		}
	}

	@Override
	public void onStateChange(int state) {
		if (state == RevealBackgroundView.STATE_FINISHED) {
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.content, MovieDetailFragment
							.newInstance(mMovieModel))
					.commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
