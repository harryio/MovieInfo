package io.thappx.data.entity;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

@StorIOSQLiteType(table = "movieDetail")
public class MovieDetailEntity {
	@StorIOSQLiteColumn(name = "tagline")
	String tagline;

	@StorIOSQLiteColumn(name = "movie_id", key = true)
	long movieId;

	ProductionCompany[] production_companies;

	@StorIOSQLiteColumn(name = "companies")
	String companyString;

	public String getTagline() {
		return tagline;
	}

	private String getProductionCompanies() {
		StringBuilder companyString = new StringBuilder();
		int length = production_companies.length;

		if (length != 0) {
			for (int i = 0; i < length - 1; ++i) {
				String company = production_companies[i].getName();
				companyString.append(company).append(", ");
			}

			companyString.append(production_companies[length - 1].getName());
		}

		return companyString.toString();
	}


	public void setCompanyString() {
		companyString = getProductionCompanies();
	}

	public void setMovieId(long id) {
		movieId = id;
	}

	public String getCompanyString() {
		return companyString;
	}

	public long getMovieId() {
		return movieId;
	}

	public static MovieDetailEntity getEmptyObject() {
		MovieDetailEntity lEntity = new MovieDetailEntity();
		lEntity.companyString = "";
		lEntity.tagline = "";
		return lEntity;
	}
}
