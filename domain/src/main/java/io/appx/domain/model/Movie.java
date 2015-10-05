package io.appx.domain.model;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Movie {
	public static final String POPULAR = "P";
	public static final String UPCOMING = "U";
	public static final String TOP_RATED = "T";
	public static final String NOW_SHOWING = "N";

	@StringDef ({POPULAR, UPCOMING, TOP_RATED, NOW_SHOWING})
	@Retention (RetentionPolicy.SOURCE)
	public @interface Type{}

	private long mId;
	private String mTitle;
	private String mOverview;
	private String mVoteAverage;
	private int mVoteCount;
	private String mReleaseDate;
	private boolean mIsAdult;
	private String mBackdropPath;
	private String mPosterPath;
	private String mGenreString;

	public Movie (long pId) {
		mId = pId;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String pTitle) {
		mTitle = pTitle;
	}

	public long getId() {
		return mId;
	}

	public String getOverview() {
		return mOverview;
	}

	public void setOverview(String pOverview) {
		mOverview = pOverview;
	}

	public String getVoteAverage() {
		return mVoteAverage;
	}

	public void setVoteAverage(String pVoteAverage) {
		mVoteAverage = pVoteAverage;
	}

	public String getReleaseDate() {
		return mReleaseDate;
	}

	public void setReleaseDate(String pReleaseDate) {
		mReleaseDate = pReleaseDate;
	}

	public boolean isAdult() {
		return mIsAdult;
	}

	public void setIsAdult(boolean pIsAdult) {
		mIsAdult = pIsAdult;
	}

	public String getBackdropPath() {
		return mBackdropPath;
	}

	public void setBackdropPath(String pBackdropPath) {
		mBackdropPath = pBackdropPath;
	}

	public String getPosterPath() {
		return mPosterPath;
	}

	public void setPosterPath(String pPosterPath) {
		mPosterPath = pPosterPath;
	}

	public int getVoteCount() {
		return mVoteCount;
	}

	public void setVoteCount(int pVoteCount) {
		mVoteCount = pVoteCount;
	}

	public String getGenreString() {
		return mGenreString;
	}

	public void setGenreString(String pGenreString) {
		mGenreString = pGenreString;
	}
}
