package io.thappx.data.repository.datastore;

import java.util.List;

import io.thappx.data.entity.CastEntity;
import io.thappx.data.entity.Configuration;
import io.thappx.data.entity.MovieDetailEntity;
import io.thappx.data.entity.MovieEntity;
import rx.Observable;

public interface MovieDataStore {
    Observable<List<MovieEntity>> popularMoviesList();

    Observable<List<MovieEntity>> upcomingMoviesList();

    Observable<List<MovieEntity>> topRatedMoviesList();

    Observable<List<MovieEntity>> nowShowingMoviesList();

	Observable<MovieDetailEntity> movieDetail(long id);

	Observable<CastEntity> movieCast(long id);
}
