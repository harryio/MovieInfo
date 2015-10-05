package io.appx.domain.interactor;

import javax.inject.Inject;

import io.appx.domain.model.Movie;
import io.appx.domain.executor.PostThreadExecutor;
import io.appx.domain.executor.ThreadExecutor;
import io.appx.domain.repository.MovieRepository;
import rx.Observable;

public class GetMovieList extends UseCase {
	private final MovieRepository mMovieRepository;
	private String mMovieType;

	@Inject
	protected GetMovieList(MovieRepository pMovieRepository, ThreadExecutor
			pThreadExecutor, PostThreadExecutor pPostThreadExecutor) {
		super(pThreadExecutor, pPostThreadExecutor);
		mMovieRepository = pMovieRepository;
	}

	@Override
	protected Observable buildUseCaseObservable() {
		if (mMovieType != null && mMovieType.length() != 0) {
			return this.mMovieRepository.getMovies(mMovieType);
		} else throw new IllegalStateException("Movie Type not set");
	}

	public void setMovieType(@Movie.Type String pMovieType) {
		mMovieType = pMovieType;
	}
}
