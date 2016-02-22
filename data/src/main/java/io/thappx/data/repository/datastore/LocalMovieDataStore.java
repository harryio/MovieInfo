package io.thappx.data.repository.datastore;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.Query;

import java.util.List;

import io.appx.domain.model.Movie;
import io.thappx.data.database.MovieContract;
import io.thappx.data.entity.CastEntity;
import io.thappx.data.entity.MovieDetailEntity;
import io.thappx.data.entity.MovieEntity;
import rx.Observable;

import static io.thappx.data.database.MovieContract.MovieDetailEntry;
import static io.thappx.data.database.MovieContract.MovieEntry;

/**
 * Local data source
 */
public class LocalMovieDataStore implements MovieDataStore {
    private static final String TAG = LocalMovieDataStore.class.getSimpleName();
    private final StorIOSQLite mStorIOSQLite;

    public LocalMovieDataStore(StorIOSQLite pStorIOSQLite) {
        mStorIOSQLite = pStorIOSQLite;
    }

    @Override
    public Observable<List<MovieEntity>> popularMoviesList() {
        return getStorIoInstance(Movie.POPULAR);
    }

    @Override
    public Observable<List<MovieEntity>> upcomingMoviesList() {
        return getStorIoInstance(Movie.UPCOMING);
    }

    @Override
    public Observable<List<MovieEntity>> topRatedMoviesList() {
        return getStorIoInstance(Movie.TOP_RATED);
    }

    @Override
    public Observable<List<MovieEntity>> nowShowingMoviesList() {
        return getStorIoInstance(Movie.NOW_SHOWING);
    }

    @Override
    public Observable<MovieDetailEntity> movieDetail(long id) {
        List<MovieDetailEntity> list = mStorIOSQLite
                .get()
                .listOfObjects(MovieDetailEntity.class)
                .withQuery(Query.builder()
                        .table(MovieDetailEntry.TABLE_NAME)
                        .where(MovieDetailEntry.COLUMN_ID + " = ?")
                        .whereArgs(id)
                        .build())
                .prepare()
                .executeAsBlocking();

        return list.size() != 0 ? Observable.just(list.get(0)) : Observable.just(MovieDetailEntity
                .getEmptyObject());
    }

    @Override
    public Observable<CastEntity> movieCast(long id) {
        List<CastEntity> list = mStorIOSQLite
                .get()
                .listOfObjects(CastEntity.class)
                .withQuery(Query.builder()
                        .table(MovieContract.CastEntry.TABLE_NAME)
                        .where(MovieContract.CastEntry.COLUMN_ID + " = ?")
                        .whereArgs(id)
                        .build())
                .prepare()
                .executeAsBlocking();

        return list.size() != 0 ? Observable.just(list.get(0)) : Observable.just(CastEntity.getDummyCast());
    }

    private Observable<List<MovieEntity>> getStorIoInstance(@Movie.Type String movieType) {
        return mStorIOSQLite
                .get()
                .listOfObjects(MovieEntity.class)
                .withQuery(Query.builder()
                        .table(MovieEntry.TABLE_NAME)
                        .where(MovieContract.MovieEntry.COLUMN_MOVIE_TYPE + " = ?")
                        .whereArgs(movieType)
                        .build())
                .prepare()
                .createObservable();
    }
}
