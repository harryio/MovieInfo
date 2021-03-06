package io.appx.domain.interactor;

import javax.inject.Inject;

import io.appx.domain.executor.PostThreadExecutor;
import io.appx.domain.executor.ThreadExecutor;
import io.appx.domain.repository.MovieRepository;
import rx.Observable;

/**
 *This class is an implementation of {@link UseCase} that represents a use case
 * for getting movie details
 */
public class GetMovieDetails extends UseCase {
    private MovieRepository mMovieRepository;
    long mId;

    @Inject
    public GetMovieDetails(long pId, MovieRepository pMovieRepository,
                           ThreadExecutor pThreadExecutor,
                           PostThreadExecutor pPostThreadExecutor) {
        super(pThreadExecutor, pPostThreadExecutor);
        mId = pId;
        mMovieRepository = pMovieRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mMovieRepository.getMovieDetail(mId);
    }
}
