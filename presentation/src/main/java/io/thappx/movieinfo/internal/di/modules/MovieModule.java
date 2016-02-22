package io.thappx.movieinfo.internal.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.appx.domain.executor.PostThreadExecutor;
import io.appx.domain.executor.ThreadExecutor;
import io.appx.domain.interactor.GetMovieCast;
import io.appx.domain.interactor.GetMovieDetails;
import io.appx.domain.interactor.GetMovieList;
import io.appx.domain.interactor.UseCase;
import io.appx.domain.repository.MovieRepository;
import io.thappx.movieinfo.internal.di.PerActivity;

/**
 * Dagger module that provides movie related collaborators.
 */
@Module
public class MovieModule {
    private long movieId = -1;

    public MovieModule() {
    }

    public MovieModule(long pMovieId) {
        movieId = pMovieId;
    }

    @Provides
    @PerActivity
    @Named("movieList")
    UseCase provideMovieList(
            GetMovieList pGetMovieList) {
        return pGetMovieList;
    }

    @Provides
    @PerActivity
    @Named("movieDetail")
    UseCase provideMovieDetail(MovieRepository pMovieRepository,
                               ThreadExecutor pThreadExecutor,
                               PostThreadExecutor pPostThreadExecutor) {
        return new GetMovieDetails(movieId, pMovieRepository, pThreadExecutor, pPostThreadExecutor);
    }

    @Provides
    @PerActivity
    @Named("movieCast")
    UseCase provideMovieCast(MovieRepository pMovieRepository,
                             ThreadExecutor pThreadExecutor,
                             PostThreadExecutor pPostThreadExecutor) {
        return new GetMovieCast(movieId, pMovieRepository, pThreadExecutor, pPostThreadExecutor);
    }
}
