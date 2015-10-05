package io.appx.domain.interactor;

import javax.inject.Inject;

import io.appx.domain.executor.PostThreadExecutor;
import io.appx.domain.executor.ThreadExecutor;
import io.appx.domain.repository.MovieRepository;
import rx.Observable;

public class GetMovieCast extends UseCase {
	private MovieRepository mMovieRepository;
	private long mId;

	@Inject
	public GetMovieCast(long pId, MovieRepository pMovieRepository,
						   ThreadExecutor pThreadExecutor,
						   PostThreadExecutor pPostThreadExecutor) {
		super(pThreadExecutor, pPostThreadExecutor);
		mMovieRepository = pMovieRepository;
		mId = pId;
	}

	@Override
	protected Observable buildUseCaseObservable() {
		return mMovieRepository.getMovieCast(mId);
	}
}
