package io.thappx.movieinfo.internal.di.components;

import dagger.Component;
import io.thappx.movieinfo.internal.di.PerActivity;
import io.thappx.movieinfo.internal.di.modules.MovieModule;
import io.thappx.movieinfo.view.fragment.MovieDetailFragment;
import io.thappx.movieinfo.view.fragment.MovieListFragment;

@PerActivity
@Component(dependencies = ApplicationComponent.class,
		modules = {MovieModule.class})
public interface MovieComponent {
	void inject(MovieListFragment pMovieListFragment);
	void inject(MovieDetailFragment pMovieDetailFragment);
}