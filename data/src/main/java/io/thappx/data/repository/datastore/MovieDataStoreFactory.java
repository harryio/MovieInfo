package io.thappx.data.repository.datastore;

import android.database.Cursor;
import android.support.annotation.WorkerThread;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.Query;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.appx.domain.model.Movie;
import io.thappx.data.net.RestApiImpl;

import static io.thappx.data.database.MovieContract.CastEntry;
import static io.thappx.data.database.MovieContract.MovieDetailEntry;
import static io.thappx.data.database.MovieContract.MovieEntry;

/**
 * Checks for availability of data at multiple sources and provide the one
 * at which data is available
 */
@Singleton
public class MovieDataStoreFactory {
    private final StorIOSQLite mStorIOSQLite;
    private final RestApiImpl mRestApi;

    @Inject
    public MovieDataStoreFactory(StorIOSQLite pStorIOSQLite, RestApiImpl pRestApi) {
        if (pStorIOSQLite == null)
            throw new NullPointerException(
                    "StorIOSqlite cannot be null in data store factory constructor");
        mStorIOSQLite = pStorIOSQLite;
        mRestApi = pRestApi;
    }

    public MovieDataStore createMovieStore(@Movie.Type String pMovieType) {
        Cursor lCursor = getMovieTypeCursor(pMovieType);

        return lCursor.getCount() == 0 || !lCursor.moveToFirst() ?
                createCloudDataStore() : createLocalDataStore();
    }

    public MovieDataStore createMovieDetailStore(long id) {
        Cursor lCursor = getMovieDetailCursor(id);

        return lCursor.getCount() == 0 || !lCursor.moveToFirst() ?
                createCloudDataStore() : createLocalDataStore();
    }

    public MovieDataStore createCastStore(long id) {
        Cursor lCursor = getCastCursor(id);

        return lCursor.getCount() == 0 || !lCursor.moveToFirst() ?
                createCloudDataStore() : createLocalDataStore();
    }


    private MovieDataStore createLocalDataStore() {
        return new LocalMovieDataStore(mStorIOSQLite);
    }

    private MovieDataStore createCloudDataStore() {
        return new CloudMovieDataStore(mRestApi, mStorIOSQLite);
    }

    @WorkerThread
    private Cursor getMovieTypeCursor(@Movie.Type String pMovieType) {
        return mStorIOSQLite.get()
                .cursor()
                .withQuery(Query.builder()
                        .table(MovieEntry.TABLE_NAME)
                        .where(MovieEntry.COLUMN_MOVIE_TYPE + " = ?")
                        .whereArgs(pMovieType)
                        .build())
                .prepare()
                .executeAsBlocking();
    }

    @WorkerThread
    private Cursor getMovieDetailCursor(long id) {
        return mStorIOSQLite.get()
                .cursor()
                .withQuery(Query.builder()
                        .table(MovieDetailEntry.TABLE_NAME)
                        .where(MovieDetailEntry.COLUMN_ID + " = ?")
                        .whereArgs(id)
                        .build())
                .prepare()
                .executeAsBlocking();
    }

    @WorkerThread
    private Cursor getCastCursor(long id) {
        return mStorIOSQLite.get()
                .cursor()
                .withQuery(Query.builder()
                        .table(CastEntry.TABLE_NAME)
                        .where(CastEntry.COLUMN_ID + " = ?")
                        .whereArgs(id)
                        .build())
                .prepare()
                .executeAsBlocking();
    }
}