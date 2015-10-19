package io.thappx.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static io.thappx.data.database.MovieContract.CastEntry;
import static io.thappx.data.database.MovieContract.MovieDetailEntry;
import static io.thappx.data.database.MovieContract.MovieEntry;

public class MovieDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "moviesData.db";

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE =
                "CREATE TABLE " + MovieEntry.TABLE_NAME + "(" +
                        MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        MovieEntry.COLUMN_ID + " TEXT NOT NULL," +
                        MovieEntry.COLUMN_TITLE + " TEXT," +
                        MovieEntry.COLUMN_OVERVIEW + " TEXT," +
                        MovieEntry.COLUMN_RELEASE_DATE + " TEXT," +
                        MovieEntry.COLUMN_VOTE_AVERAGE + " TEXT," +
						MovieEntry.COLUMN_VOTE_COUNT + " INTEGER," +
                        MovieEntry.COLUMN_IS_ADULT + " INTEGER DEFAULT 0," +
                        MovieEntry.COLUMN_BACKDROP_PATH + " TEXT," +
						MovieEntry.COLUMN_GENRES + " TEXT," +
                        MovieEntry.COLUMN_POSTER_PATH + " TEXT," +
                        MovieEntry.COLUMN_MOVIE_TYPE + " TEXT CHECK( "
                                + MovieEntry.COLUMN_MOVIE_TYPE
                                + " IN ('P', 'T', 'U', 'N') ) NOT NULL DEFAULT 'P'," +
                        "UNIQUE (" + MovieEntry.COLUMN_ID + ", "
                                + MovieEntry.COLUMN_MOVIE_TYPE + ") "
                                + "ON CONFLICT IGNORE);";

		final String SQL_CREATE_CAST_TABLE =
				"CREATE TABLE " + CastEntry.TABLE_NAME + "(" +
						CastEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
						CastEntry.COLUMN_ID + " TEXT NOT NULL," +
						CastEntry.COLUMN_CAST + " TEXT NOT NULL" + ");";

		final String SQL_CREATE_MOVIE_DETAIL_TABLE =
				"CREATE TABLE " + MovieDetailEntry.TABLE_NAME + "(" +
						MovieDetailEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
						MovieDetailEntry.COLUMN_ID + " TEXT NOT NULL," +
						MovieDetailEntry.COLUMN_TAGLINE + " TEXT," +
						MovieDetailEntry.COLUMN_COMPANIES + " TEXT" + ");";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
		db.execSQL(SQL_CREATE_CAST_TABLE);
		db.execSQL(SQL_CREATE_MOVIE_DETAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE = "DROP TABLE IF EXISTS ";
        db.execSQL(DROP_TABLE + MovieEntry.TABLE_NAME);
        db.execSQL(DROP_TABLE + CastEntry.TABLE_NAME);
        db.execSQL(DROP_TABLE + MovieDetailEntry.TABLE_NAME);

        onCreate(db);
    }
}
