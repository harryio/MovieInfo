package io.thappx.movieinfo.presenter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.appx.domain.model.Movie;
import io.appx.domain.exception.DefaultErrorBundle;
import io.appx.domain.exception.ErrorBundle;
import io.appx.domain.interactor.DefaultSubscriber;
import io.appx.domain.interactor.GetMovieList;
import io.appx.domain.interactor.UseCase;
import io.thappx.movieinfo.exception.ErrorMessageFactory;
import io.thappx.movieinfo.mapper.MovieModelDataMapper;
import io.thappx.movieinfo.model.MovieModel;
import io.thappx.movieinfo.view.MovieListView;
import rx.Observable;
import rx.functions.Action1;

public class MovieListPresenter implements Presenter {

	private MovieListView mMovieListView;
	private MovieModelDataMapper mMovieModelDataMapper;
	private GetMovieList mUseCase;

	@Inject
	public MovieListPresenter(@Named("movieList") UseCase pGetMovieList,
							  MovieModelDataMapper pMovieModelDataMapper) {
		mMovieModelDataMapper = pMovieModelDataMapper;
		mUseCase = (GetMovieList) pGetMovieList;
	}

	public void setView(MovieListView pMovieListView) {
		mMovieListView = pMovieListView;
	}

	public void setMovieType(@Movie.Type String pMovieType) {
		mUseCase.setMovieType(pMovieType);
	}

	public void initialize() {
		loadMovieList();
	}

	private void loadMovieList() {
		hideViewError();
		hideViewRetry();
		showViewLoading();
		getMovieList();
	}

	public void onListItemClicked(MovieModel pMovieModel, int[] pLoc) {
		mMovieListView.viewMovie(pMovieModel, pLoc);
	}

	private void showViewLoading() {
		mMovieListView.showLoading();
	}

	private void hideViewLoading() {
		mMovieListView.hideLoading();
	}

	private void showViewRetry() {
		mMovieListView.showRetry();
	}

	private void hideViewRetry() {
		mMovieListView.hideRetry();
	}

	private void showViewError(ErrorBundle pBundle) {
		String errorMessage = ErrorMessageFactory.create(mMovieListView.getContext(),
				pBundle.getException());
		mMovieListView.showError(errorMessage);
	}

	private void hideViewError() {
		mMovieListView.hideError();
	}

	private void showMoviesInRecyclerView(List<Movie> pMovieList) {
		List<MovieModel> pMovieModelList =
				mMovieModelDataMapper.transform(pMovieList);
		mMovieListView.renderMovieList(pMovieModelList);
	}

	private void getMovieList() {
		mUseCase.execute(new FetchMoviesSubscriber());
	}

	private class FetchMoviesSubscriber
			extends DefaultSubscriber<List<Movie>> {
		@Override
		public void onCompleted() {
			hideViewLoading();
		}

		@Override
		public void onNext(List<Movie> t) {
			hideViewLoading();
			showMoviesInRecyclerView(t);
		}

		@Override
		public void onError(Throwable e) {
			e.printStackTrace();

			hideViewLoading();
			showViewError(new DefaultErrorBundle((Exception) e));
			showViewRetry();
		}
	}

	@Override
	public void resume() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void destroy() {
		mUseCase.unsubscribe();
	}
}
