package io.thappx.data.repository;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.appx.domain.model.Cast;
import io.appx.domain.model.Movie;
import io.appx.domain.model.MovieDetail;
import io.appx.domain.repository.MovieRepository;
import io.thappx.data.entity.CastEntity;
import io.thappx.data.entity.MovieDetailEntity;
import io.thappx.data.entity.mapper.MovieEntityDataMapper;
import io.thappx.data.repository.datastore.MovieDataStore;
import io.thappx.data.repository.datastore.MovieDataStoreFactory;
import rx.Observable;

@SuppressWarnings("Convert2MethodRef")
@Singleton
public class MovieDataRepository implements MovieRepository {
    private final MovieDataStoreFactory mMovieDataStoreFactory;
    private final MovieEntityDataMapper mMovieEntityDataMapper;

    @Inject
    public MovieDataRepository(MovieDataStoreFactory pMovieDataStoreFactory,
                               MovieEntityDataMapper pMovieEntityDataMapper) {
        mMovieDataStoreFactory = pMovieDataStoreFactory;
        mMovieEntityDataMapper = pMovieEntityDataMapper;
    }

    @Override
    public Observable<List<Movie>> getMovies(@Movie.Type String movieType) {
        Observable<List<Movie>> lMovieList = null;
        MovieDataStore lMovieDataStore = mMovieDataStoreFactory.createMovieStore(movieType);

        switch (movieType) {
            case Movie.POPULAR:
                lMovieList = lMovieDataStore.popularMoviesList()
                        .map(t -> {
                            return mMovieEntityDataMapper.transform(t);
                        });
                break;

            case Movie.UPCOMING:
                lMovieList = lMovieDataStore.upcomingMoviesList()
                        .map(t -> {
                            return mMovieEntityDataMapper.transform(t);
                        });
                break;

            case Movie.TOP_RATED:
                lMovieList = lMovieDataStore.topRatedMoviesList()
                        .map(t -> {
                            return mMovieEntityDataMapper.transform(t);
                        });
                break;

            case Movie.NOW_SHOWING:
                lMovieList = lMovieDataStore.nowShowingMoviesList()
                        .map(t -> {
                            return mMovieEntityDataMapper.transform(t);
                        });
                break;

            default:
                lMovieList = Observable.just(Collections.emptyList());
        }

        return lMovieList;
    }

    @Override
    public Observable<MovieDetail> getMovieDetail(long id) {
        MovieDataStore lMovieDataStore = mMovieDataStoreFactory.createMovieDetailStore(id);
        Observable<MovieDetailEntity> lObservable = lMovieDataStore.movieDetail(id);

        return lObservable.map(t -> {
            return mMovieEntityDataMapper.transform(t);
        });
    }

    @Override
    public Observable<Cast> getMovieCast(long id) {
        MovieDataStore lMovieDataStore = mMovieDataStoreFactory.createMovieDetailStore(id);
        Observable<CastEntity> lObservable = lMovieDataStore.movieCast(id);

        return lObservable.map(t -> {
            return mMovieEntityDataMapper.transform(t);
        });
    }
}