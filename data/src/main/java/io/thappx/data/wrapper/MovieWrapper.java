package io.thappx.data.wrapper;

import java.util.List;

import io.thappx.data.entity.MovieEntity;

/**
 * Wrapper for result of fetch movies data network call
 */
public class MovieWrapper {
    List<MovieEntity> results;

    public List<MovieEntity> getMovies() {
        return results;
    }
}
