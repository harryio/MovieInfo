package io.thappx.movieinfo.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import io.appx.domain.model.Movie;
import io.thappx.movieinfo.view.fragment.MovieListFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
	SparseArray<MovieListFragment> registeredFragments =
			new SparseArray<>();

	private final String[] mMovieTypes = {
			Movie.POPULAR, Movie.UPCOMING,
			Movie.NOW_SHOWING, Movie.TOP_RATED
	};

	private final String[] mTabsName = {
			"Popular", "Upcoming", "Now Showing", "Top Rated"
	};

	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return MovieListFragment.newInstance(mMovieTypes[position]);
	}

	@Override
	public int getCount() {
		return mMovieTypes.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mTabsName[position];
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		Fragment lFragment = (Fragment)
				super.instantiateItem(container, position);
		registeredFragments.put(position, (MovieListFragment) lFragment);
		return lFragment;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		registeredFragments.remove(position);
		super.destroyItem(container, position, object);
	}

	public MovieListFragment getRegisteredFragment(int position) {
		return registeredFragments.get(position);
	}


}
