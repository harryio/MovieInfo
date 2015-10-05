package io.thappx.data.net;

import java.util.List;

import io.thappx.data.entity.CastEntity;
import io.thappx.data.entity.Configuration;
import io.thappx.data.entity.MovieDetailEntity;
import io.thappx.data.entity.MovieEntity;
import io.thappx.data.exception.NetworkConnectionException;
import rx.Observable;

public interface RestApi {
    Observable<List<MovieEntity>> getPopularMovies();
    Observable<List<MovieEntity>> getUpcomingMovies();
    Observable<List<MovieEntity>> getNowShowingMovies();
    Observable<List<MovieEntity>> getTopRatedMovies();

    Observable<Configuration> getConfiguration();
	Observable<MovieDetailEntity> getMovieDetails(long id);
	Observable<CastEntity> getMovieCast(long id);
}
