package io.thappx.data.entity;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * POJO representing one movie
 */
@StorIOSQLiteType(table = "movies")
public class MovieEntity {
    @StorIOSQLiteColumn(name = "movie_id", key = true)
    long id;

    @StorIOSQLiteColumn(name = "title")
    String title;

    @StorIOSQLiteColumn(name = "overview")
    String overview;

    @StorIOSQLiteColumn(name = "vote_average")
    String vote_average;

	@StorIOSQLiteColumn(name = "vote_count")
	int vote_count;

    @StorIOSQLiteColumn(name = "release_date")
    String release_date;

    @StorIOSQLiteColumn(name = "adult")
    boolean adult;

    int genre_ids[];

    @StorIOSQLiteColumn(name = "backdrop")
    String backdrop_path;

    @StorIOSQLiteColumn(name = "poster")
    String poster_path;

    @StorIOSQLiteColumn(name = "movie_type")
    String identifier;

	@StorIOSQLiteColumn(name = "genres")
	String genreString;

    MovieEntity() {}

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getVoteAverage() {
        return vote_average;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public boolean isAdult() {
        return adult;
    }

    public int[] getGenreIds() {
        return genre_ids;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public String getPosterPath() {
        return poster_path;
    }

	public int getVoteCount() {
		return vote_count;
	}

    public void setIdentifier(String pIdentifier) {
        identifier = pIdentifier;
    }

	public String getGenreString() {
		return genreString;
	}

	public void setGenreString(String pGenreString) {
		genreString = pGenreString;
	}

	public void setBackdropPath(String pBackdrop_path) {
		backdrop_path = pBackdrop_path;
	}

	public void setPosterPath(String pPoster_path) {
		poster_path = pPoster_path;
	}
}
