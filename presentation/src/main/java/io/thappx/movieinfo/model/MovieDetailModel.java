package io.thappx.movieinfo.model;

public class MovieDetailModel {
	private String companies;
	private String tagline;
	private long movieId;

	public String getCompanies() {
		return companies;
	}

	public void setCompanies(String pCompanies) {
		companies = pCompanies;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String pTagline) {
		tagline = pTagline;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long pMovieId) {
		movieId = pMovieId;
	}
}
