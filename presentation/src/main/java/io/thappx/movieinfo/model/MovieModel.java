package io.thappx.movieinfo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {
	private long id;
	private String title;
	private String overview;
	private String voteAverage;
	private int voteCount;
	private String releaseDate;
	private boolean adult;
	private String backdropPath;
	private String posterPath;
	private String genreString;

	public MovieModel (long pId) {
		id = pId;
	}

	public String getTitle() {
		return adult ? title.concat("A") : title;
	}

	public void setTitle(String pTitle) {
		title = pTitle;
	}

	public long getId() {
		return id;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String pOverview) {
		overview = pOverview;
	}

	public String getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(String pVoteAverage) {
		voteAverage = pVoteAverage;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String pReleaseDate) {
		releaseDate = pReleaseDate;
	}

	public void setIsAdult(boolean pIsAdult) {
		adult = pIsAdult;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String pBackdropPath) {
		backdropPath = pBackdropPath;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String pPosterPath) {
		posterPath = pPosterPath;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int pVoteCount) {
		voteCount = pVoteCount;
	}

	public String getGenreString() {
		return genreString;
	}

	public void setGenreString(String pGenreString) {
		genreString = pGenreString;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(this.id);
		dest.writeString(this.title);
		dest.writeString(this.overview);
		dest.writeString(this.voteAverage);
		dest.writeInt(this.voteCount);
		dest.writeString(this.releaseDate);
		dest.writeByte(adult ? (byte) 1 : (byte) 0);
		dest.writeString(this.backdropPath);
		dest.writeString(this.posterPath);
		dest.writeString(this.genreString);
	}

	protected MovieModel(Parcel in) {
		this.id = in.readLong();
		this.title = in.readString();
		this.overview = in.readString();
		this.voteAverage = in.readString();
		this.voteCount = in.readInt();
		this.releaseDate = in.readString();
		this.adult = in.readByte() != 0;
		this.backdropPath = in.readString();
		this.posterPath = in.readString();
		this.genreString = in.readString();
	}

	public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
		public MovieModel createFromParcel(Parcel source) {
			return new MovieModel(source);
		}

		public MovieModel[] newArray(int size) {
			return new MovieModel[size];
		}
	};
}
