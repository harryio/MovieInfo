package io.thappx.movieinfo.presenter;

import javax.inject.Inject;
import javax.inject.Named;

import io.appx.domain.interactor.DefaultSubscriber;
import io.appx.domain.interactor.UseCase;
import io.appx.domain.model.Cast;
import io.appx.domain.model.MovieDetail;
import io.thappx.movieinfo.internal.di.PerActivity;
import io.thappx.movieinfo.mapper.MovieModelDataMapper;
import io.thappx.movieinfo.view.MovieCastView;
import io.thappx.movieinfo.view.MovieDetailView;

@PerActivity
public class MovieDetailPresenter implements Presenter {

	private MovieDetailView mMovieDetailView;
	private MovieCastView mMovieCastView;

	private final UseCase mMovieDetailUseCase;
	private final UseCase mMovieCastUseCase;

	private final MovieModelDataMapper mMovieModelDataMapper;

	@Inject
	public MovieDetailPresenter(@Named("movieDetail") UseCase pMovieDetailUseCase,
								@Named("movieCast") UseCase pMovieCastUseCase,
								MovieModelDataMapper pMovieModelDataMapper) {
		mMovieDetailUseCase = pMovieDetailUseCase;
		mMovieCastUseCase = pMovieCastUseCase;
		mMovieModelDataMapper = pMovieModelDataMapper;
	}

	public void setDetailView(MovieDetailView pMovieDetailView) {
		mMovieDetailView = pMovieDetailView;
	}

	public void setCastView(MovieCastView pMovieCastView) {
		mMovieCastView = pMovieCastView;
	}

	@Override
	public void resume() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void destroy() {
		mMovieDetailUseCase.unsubscribe();
		mMovieCastUseCase.unsubscribe();
	}

	public void initialize() {
		getMovieDetails();
		getMovieCast();
	}

	private void getMovieDetails() {
		mMovieDetailUseCase.execute(new MovieDetailSubscriber());
	}

	private void getMovieCast() {
		mMovieCastUseCase.execute(new MovieCastSubscriver());
	}

	private class MovieDetailSubscriber extends DefaultSubscriber<MovieDetail> {
		@Override
		public void onCompleted() {
		}

		@Override
		public void onError(Throwable e) {
			e.printStackTrace();
			mMovieDetailView.showMovieDetailError("Failed to fetch movie details");
		}

		@Override
		public void onNext(MovieDetail t) {
			mMovieDetailView.showMovie(mMovieModelDataMapper.transform(t));
		}
	}

	private class MovieCastSubscriver extends DefaultSubscriber<Cast> {
		@Override
		public void onCompleted() {
		}

		@Override
		public void onError(Throwable e) {
			e.printStackTrace();
			mMovieCastView.showCastError("Failed to fetch movie cast");
		}

		@Override
		public void onNext(Cast t) {
			mMovieCastView.showCast(mMovieModelDataMapper.transform(t));
		}
	}
}
