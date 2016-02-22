package io.thappx.data.net;

import java.util.List;

import io.thappx.data.entity.CastEntity;
import io.thappx.data.entity.Configuration;
import io.thappx.data.entity.MovieDetailEntity;
import io.thappx.data.entity.MovieEntity;
import rx.Observable;

/**
 * Network layer abstraction of the api calls
 */
public interface RestApi {
    /**
     * Fetch popular movies from the network
     * @return Observable containing list of popular movies
     */
    Observable<List<MovieEntity>> getPopularMovies();

    /**
     * Fetch upcoming movies from the network
     * @return Observable containing list of upcoming movies
     */
    Observable<List<MovieEntity>> getUpcomingMovies();

    /**
     * Fetch now showing movies from the network
     * @return Observable containing list of now showing movies
     */
    Observable<List<MovieEntity>> getNowShowingMovies();

    /**
     * Fetch top rated movies from the network
     * @return Observable containing list of top rated movies
     */
    Observable<List<MovieEntity>> getTopRatedMovies();

    /**
     * Fetch configuration from the network
     * @return Observable containing configuration
     */
    Observable<Configuration> getConfiguration();

    /**
     * Fetch movie detail from the network
     * @param id id of the movie for which details are to be fetched
     * @return Observable containing movie details
     */
	Observable<MovieDetailEntity> getMovieDetails(long id);

    /**
     * Fetch movie cast from the network
     * @param id id of the movie for which cast is to be fetched
     * @return Observable containing cast of the movie
     */
	Observable<CastEntity> getMovieCast(long id);
}
