package io.thappx.data.entity;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Wrapper class for cast of the movie
 */
@StorIOSQLiteType(table = "cast")
public class CastEntity {
	Cast[] cast;

	@StorIOSQLiteColumn(name = "cast_string")
	String castString;

	@StorIOSQLiteColumn(name = "movie_id", key = true)
	long movieID;

	public Cast[] getCast() {
		return cast;
	}

	private String getCastStringFromArray() {
		StringBuilder lStringBuilder = new StringBuilder();
		int length = cast.length;

		if (length != 0) {
			for (int i = 0; i < length - 1; ++i) {
				Cast lCast = cast[i];
				lStringBuilder.append(lCast.getName()).append(", ");
			}

			lStringBuilder.append(cast[length - 1].getName());
		}

		return lStringBuilder.toString();
	}

	public void setMovieID(long id) {
		movieID = id;
	}

	public long getMovieID() {
		return movieID;
	}

	public void setCastString() {
		castString = getCastStringFromArray();
	}

	public String getCastString() {
		return castString;
	}

	public static CastEntity getDummyCast() {
		CastEntity lCastEntity = new CastEntity();
		lCastEntity.castString = "";
		return lCastEntity;
	}
}
