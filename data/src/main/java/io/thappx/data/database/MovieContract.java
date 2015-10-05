package io.thappx.data.database;

import android.provider.BaseColumns;

public class MovieContract {

    public static final class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "movies";

        static final String COLUMN_ID = "movie_id";
        static final String COLUMN_TITLE = "title";
        static final String COLUMN_OVERVIEW = "overview";
        static final String COLUMN_VOTE_AVERAGE = "vote_average";
		static final String COLUMN_VOTE_COUNT ="vote_count";
        static final String COLUMN_IS_ADULT = "adult";
        static final String COLUMN_RELEASE_DATE = "release_date";
        static final String COLUMN_BACKDROP_PATH = "backdrop";
        static final String COLUMN_POSTER_PATH = "poster";
		static final String COLUMN_GENRES = "genres";
        public static final String COLUMN_MOVIE_TYPE = "movie_type";
    }

	public static final class CastEntry implements BaseColumns {
		public static final String TABLE_NAME = "cast";

		public static final String COLUMN_ID = "movie_id";
		public static final String COLUMN_CAST = "cast_string";
	}

	public static final class MovieDetailEntry implements BaseColumns {
		public static final String TABLE_NAME = "movieDetail";

		public static final String COLUMN_ID = "movie_id";
		public static final String COLUMN_TAGLINE = "tagline";
		public static final String COLUMN_COMPANIES = "companies";
	}
}
