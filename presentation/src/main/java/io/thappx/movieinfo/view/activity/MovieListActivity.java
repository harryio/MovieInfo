package io.thappx.movieinfo.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.thappx.movieinfo.R;
import io.thappx.movieinfo.internal.di.HasComponent;
import io.thappx.movieinfo.internal.di.components.DaggerMovieComponent;
import io.thappx.movieinfo.internal.di.components.MovieComponent;
import io.thappx.movieinfo.model.MovieModel;
import io.thappx.movieinfo.view.adapter.ViewPagerAdapter;
import io.thappx.movieinfo.view.customviews.CustomViewPager;
import io.thappx.movieinfo.view.fragment.MovieListFragment;

public class MovieListActivity extends BaseActivity
		implements MovieListFragment.OnFragmentInteractionListener,
		HasComponent<MovieComponent> {
	@Bind (R.id.v_tabLayout)
	TabLayout mTabLayout;
	@Bind (R.id.vp_movies)
	CustomViewPager mViewPager;
	@Bind (R.id.toolbar)
	Toolbar mToolbar;

	MovieComponent mMovieComponent;
	ViewPagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initializeInjector();

		ButterKnife.bind(this);

		FragmentManager lFragmentManager = getSupportFragmentManager();
		mPagerAdapter = new ViewPagerAdapter(lFragmentManager);
		mViewPager.setAdapter(mPagerAdapter);

		initializeTabLayout();
	}

	private void initializeTabLayout() {
		mTabLayout.setupWithViewPager(mViewPager);
		mTabLayout.setSelectedTabIndicatorColor(
				ContextCompat.getColor(this, R.color.accent));

	}

	private void initializeInjector() {
		mMovieComponent = DaggerMovieComponent.builder()
				.applicationComponent(getApplicationComponent())
				.build();
	}

	@Override
	public MovieComponent getComponent() {
		if (mMovieComponent != null) return mMovieComponent;
		else throw new NullPointerException("MovieComponent is null");
	}

	@Override
	public void onListItemClicked(MovieModel pMovieModel, int[] pLoc) {
		mNavigator.navigateToMovieDetails(this, pMovieModel, pLoc);
	}
}
