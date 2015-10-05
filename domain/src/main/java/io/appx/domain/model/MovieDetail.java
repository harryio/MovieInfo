package io.appx.domain.model;

public class MovieDetail {
	private String mCompanies;
	private String mTagline;
	private long mMovieId;

	public String getCompanies() {
		return mCompanies;
	}

	public void setCompanies(String pCompanies) {
		mCompanies = pCompanies;
	}

	public String getTagline() {
		return mTagline;
	}

	public void setTagline(String pTagline) {
		mTagline = pTagline;
	}

	public long getMovieId() {
		return mMovieId;
	}

	public void setMovieId(long pMovieId) {
		mMovieId = pMovieId;
	}
}
