package io.thappx.movieinfo.view;

import io.thappx.movieinfo.model.MovieDetailModel;
import io.thappx.movieinfo.presenter.Presenter;

public interface MovieDetailView {
	void showMovieDetailError(String pMessage);

	void showMovie(MovieDetailModel pMovieDetailModel);
}
