package io.thappx.movieinfo.view;

import java.util.List;

import io.thappx.movieinfo.model.MovieModel;

public interface MovieListView extends LoadDataView {
	void renderMovieList(List<MovieModel> movieModelCollection);

	void viewMovie(MovieModel pMovieModel, int[] pLoc);
}
