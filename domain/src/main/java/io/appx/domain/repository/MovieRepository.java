package io.appx.domain.repository;

import java.util.List;

import io.appx.domain.model.Cast;
import io.appx.domain.model.Movie;
import io.appx.domain.model.MovieDetail;
import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link Movie} related data.
 */
public interface MovieRepository {
    /**
     * Get an {@link rx.Observable} that emit a list of movies
     * @param movieType type of movies to be fetched e.g POPULAR, UPCOMING, TOP-RATED, NOW-SHOWING
     */
    Observable<List<Movie>> getMovies(@Movie.Type String movieType);

    /**
     * Get an {@link Observable} than emit MovieDetail
     * @param id The movie id to retrieve MovieDetail
     */
    Observable<MovieDetail> getMovieDetail(long id);

    /**
     * Get an {@link Observable} that emit movie cast
     * @param id The movie to retrieve cast
     */
    Observable<Cast> getMovieCast(long id);
}
