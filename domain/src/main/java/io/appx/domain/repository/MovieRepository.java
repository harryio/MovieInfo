package io.appx.domain.repository;

import java.util.List;

import io.appx.domain.model.Cast;
import io.appx.domain.model.Movie;
import io.appx.domain.model.MovieDetail;
import rx.Observable;

public interface MovieRepository {
	Observable<List<Movie>> getMovies(@Movie.Type String movieType);

	Observable<MovieDetail> getMovieDetail(long id);

	Observable<Cast> getMovieCast(long id);
}
