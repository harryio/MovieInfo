package io.appx.domain.model;

/**
 * Movie cast representation in the domain layer
 */
public class Cast {
	private String mCast;
	private long mMovieId;

	public String getCast() {
		return mCast;
	}

	public void setCast(String pCast) {
		mCast = pCast;
	}

	public long getMovieId() {
		return mMovieId;
	}

	public void setMovieId(long pMovieId) {
		mMovieId = pMovieId;
	}
}
