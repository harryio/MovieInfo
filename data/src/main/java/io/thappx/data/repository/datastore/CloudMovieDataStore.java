package io.thappx.data.repository.datastore;

import android.support.annotation.WorkerThread;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;

import java.util.List;

import io.thappx.data.entity.CastEntity;
import io.thappx.data.entity.Configuration;
import io.thappx.data.entity.Genre;
import io.thappx.data.entity.MovieDetailEntity;
import io.thappx.data.entity.MovieEntity;
import io.thappx.data.net.RestApi;
import rx.Observable;
import rx.functions.Action1;

/**
 * Network data store
 */
@SuppressWarnings("Convert2MethodRef")
public class CloudMovieDataStore implements MovieDataStore {
    private static final String TAG = CloudMovieDataStore.class.getSimpleName();

    private final RestApi mRestApi;
    private final StorIOSQLite mStorIOSQLite;

    private final Action1<List<MovieEntity>> storeMoviesAction =
            movieList -> storeMoviesToDatabase(movieList);

    private final Action1<MovieDetailEntity> storeMovieDetailAction =
            movieDetail -> storeMovieDetailToDatabase(movieDetail);

    private final Action1<CastEntity> storeCastAction =
            cast -> storeCastToDatabase(cast);

    public CloudMovieDataStore(RestApi pRestApi, StorIOSQLite pStorIOSQLite) {
        mRestApi = pRestApi;
        mStorIOSQLite = pStorIOSQLite;
    }

    @Override
    public Observable<List<MovieEntity>> popularMoviesList() {
        Observable<Configuration> lConfiguration = mRestApi.getConfiguration();
        Observable<List<MovieEntity>> lListObservable = mRestApi.getPopularMovies();

        return Observable.zip(lConfiguration, lListObservable, (t1, t2) -> {
            prepareMovies(t1, t2, "P");
            return t2;
        }).doOnNext(storeMoviesAction);
    }

    @Override
    public Observable<List<MovieEntity>> upcomingMoviesList() {
        Observable<Configuration> lConfiguration = mRestApi.getConfiguration();
        Observable<List<MovieEntity>> lListObservable = mRestApi.getUpcomingMovies();

        return Observable.zip(lConfiguration, lListObservable, (t1, t2) -> {
            prepareMovies(t1, t2, "U");
            return t2;
        }).doOnNext(storeMoviesAction);
    }

    @Override
    public Observable<List<MovieEntity>> topRatedMoviesList() {
        Observable<Configuration> lConfiguration = mRestApi.getConfiguration();
        Observable<List<MovieEntity>> lListObservable = mRestApi.getTopRatedMovies();

        return Observable.zip(lConfiguration, lListObservable, (t1, t2) -> {
            prepareMovies(t1, t2, "T");
            return t2;
        }).doOnNext(storeMoviesAction);
    }

    @Override
    public Observable<List<MovieEntity>> nowShowingMoviesList() {
        Observable<Configuration> lConfiguration = mRestApi.getConfiguration();
        Observable<List<MovieEntity>> lListObservable = mRestApi.getNowShowingMovies();

        return Observable.zip(lConfiguration, lListObservable, (t1, t2) -> {
            prepareMovies(t1, t2, "N");
            return t2;
        }).doOnNext(storeMoviesAction);
    }

    @Override
    public Observable<MovieDetailEntity> movieDetail(long id) {
        return mRestApi.getMovieDetails(id).doOnNext(storeMovieDetailAction);
    }

    @Override
    public Observable<CastEntity> movieCast(long id) {
        return mRestApi.getMovieCast(id).doOnNext(storeCastAction);
    }

    @WorkerThread
    private void storeMoviesToDatabase(List<MovieEntity> pMoviesList) {
        CloudMovieDataStore.this.mStorIOSQLite
                .put()
                .objects(pMoviesList)
                .prepare()
                .executeAsBlocking();
    }

    @WorkerThread
    private void storeMovieDetailToDatabase(MovieDetailEntity pMovieDetailEntity) {
        CloudMovieDataStore.this.mStorIOSQLite
                .put()
                .object(pMovieDetailEntity)
                .prepare()
                .executeAsBlocking();
    }

    @WorkerThread
    private void storeCastToDatabase(CastEntity pCastEntity) {
        CloudMovieDataStore.this.mStorIOSQLite
                .put()
                .object(pCastEntity)
                .prepare()
                .executeAsBlocking();
    }

    private void prepareMovies(Configuration pConfiguration, List<MovieEntity> pEntityList,
                               String movieType) {
        for (MovieEntity lMovieEntity : pEntityList) {
            lMovieEntity.setIdentifier(movieType);
            String genreString = Genre.getGenreString(lMovieEntity.getGenreIds());
            lMovieEntity.setGenreString(genreString);
            lMovieEntity.setPosterPath(pConfiguration.getFullPosterPath(lMovieEntity.getPosterPath
                    ()));
            lMovieEntity.setBackdropPath(pConfiguration.getFullBackdropPath(lMovieEntity
                    .getBackdropPath
                            ()));
        }
    }
}
