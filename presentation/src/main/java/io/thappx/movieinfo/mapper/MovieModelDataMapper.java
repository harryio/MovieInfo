package io.thappx.movieinfo.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.appx.domain.model.Cast;
import io.appx.domain.model.Movie;
import io.appx.domain.model.MovieDetail;
import io.thappx.movieinfo.internal.di.PerActivity;
import io.thappx.movieinfo.model.CastModel;
import io.thappx.movieinfo.model.MovieDetailModel;
import io.thappx.movieinfo.model.MovieModel;

@PerActivity
public class MovieModelDataMapper {
	@Inject
	public MovieModelDataMapper() {
	}

	public MovieModel transform(Movie pMovie) {
		if (pMovie != null) {
			MovieModel lMovieModel = new MovieModel(pMovie.getId());

			lMovieModel.setTitle(pMovie.getTitle());
			lMovieModel.setOverview(pMovie.getOverview());
			lMovieModel.setVoteAverage(pMovie.getVoteAverage());
			lMovieModel.setVoteCount(pMovie.getVoteCount());
			lMovieModel.setReleaseDate(pMovie.getReleaseDate());
			lMovieModel.setBackdropPath(pMovie.getBackdropPath());
			lMovieModel.setPosterPath(pMovie.getPosterPath());
			lMovieModel.setIsAdult(pMovie.isAdult());
			lMovieModel.setGenreString(pMovie.getGenreString());

			return lMovieModel;
		}

		return null;
	}

	public List<MovieModel> transform(List<Movie> pMovieList) {
		List<MovieModel> lMovieModelList = new ArrayList<>(pMovieList.size());

		MovieModel lMovieModel;
		for (Movie lMovie : pMovieList) {
			lMovieModel = transform(lMovie);

			if (lMovieModel != null) {
				lMovieModelList.add(lMovieModel);
			}
		}

		return lMovieModelList;
	}

	public CastModel transform(Cast pCast) {
		if (pCast != null) {
			CastModel pCastModel = new CastModel();
			pCastModel.setMovieId(pCast.getMovieId());
			pCastModel.setCast(pCast.getCast());
			return pCastModel;
		} else throw new NullPointerException("Cast is null");
	}

	public MovieDetailModel transform(MovieDetail pMovieDetail) {
		if (pMovieDetail != null) {
			MovieDetailModel lMovieDetailModel = new MovieDetailModel();
			lMovieDetailModel.setMovieId(pMovieDetail.getMovieId());
			lMovieDetailModel.setCompanies(pMovieDetail.getCompanies());
			lMovieDetailModel.setTagline(pMovieDetail.getTagline());
			return lMovieDetailModel;
		} else throw new NullPointerException("MovieDetail is null");
	}
}
