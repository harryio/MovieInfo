package io.thappx.data.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.appx.domain.model.Cast;
import io.appx.domain.model.Movie;
import io.appx.domain.model.MovieDetail;
import io.thappx.data.entity.CastEntity;
import io.thappx.data.entity.MovieDetailEntity;
import io.thappx.data.entity.MovieEntity;

@Singleton
public class MovieEntityDataMapper {

	@Inject
	public MovieEntityDataMapper() {
	}

	public Movie transform(MovieEntity pMovieEntity) {
		if (pMovieEntity != null) {
			Movie lMovie = new Movie(pMovieEntity.getId());
			lMovie.setTitle(pMovieEntity.getTitle());
			lMovie.setOverview(pMovieEntity.getOverview());
			lMovie.setVoteAverage(pMovieEntity.getVoteAverage());
			lMovie.setVoteCount(pMovieEntity.getVoteCount());
			lMovie.setReleaseDate(pMovieEntity.getReleaseDate());
			lMovie.setBackdropPath(pMovieEntity.getBackdropPath());
			lMovie.setPosterPath(pMovieEntity.getPosterPath());
			lMovie.setIsAdult(pMovieEntity.isAdult());
			lMovie.setGenreString(pMovieEntity.getGenreString());

			return lMovie;
		}

		return null;
	}

	public List<Movie> transform(List<MovieEntity> pMovieEntityList) {
		List<Movie> lMovieList = new ArrayList<>(pMovieEntityList.size());

		Movie lMovie;
		for (MovieEntity lMovieEntity : pMovieEntityList) {
			lMovie = transform(lMovieEntity);

			if (lMovie != null) {
				lMovieList.add(lMovie);
			}
		}

		return lMovieList;
	}

	public MovieDetail transform(MovieDetailEntity pMovieDetailEntity) {
		if (pMovieDetailEntity != null) {
			MovieDetail lMovieDetail = new MovieDetail();
			lMovieDetail.setMovieId(pMovieDetailEntity.getMovieId());
			lMovieDetail.setTagline(pMovieDetailEntity.getTagline());
			lMovieDetail.setCompanies(pMovieDetailEntity.getCompanyString());
			return lMovieDetail;
		} else throw new IllegalStateException("Movie Detail Entity cannot be null");
	}

	public Cast transform(CastEntity pCastEntity) {
		if (pCastEntity != null) {
			Cast lCast = new Cast();
			lCast.setMovieId(pCastEntity.getMovieID());
			lCast.setCast(pCastEntity.getCastString());
			return lCast;
		} else throw new IllegalStateException("Cast Entity cannot be null in mapper");
	}
}
