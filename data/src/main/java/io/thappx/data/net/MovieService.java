package io.thappx.data.net;

import io.thappx.data.entity.CastEntity;
import io.thappx.data.entity.Configuration;
import io.thappx.data.entity.MovieDetailEntity;
import io.thappx.data.wrapper.MovieWrapper;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface MovieService {
    @GET("movie/popular")
    Call<MovieWrapper> getPopularMovies(@Query("api_key") String api_key);

    @GET("movie/upcoming")
    Call<MovieWrapper> getUpcomingMovies(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<MovieWrapper> getTopRatedMovies(@Query("api_key") String api_key);

    @GET("movie/now_playing")
    Call<MovieWrapper> getNowPlayingMovies(@Query("api_key") String api_key);

    @GET("configuration")
    Call<Configuration> getConfiguration(@Query("api_key") String api_key);

	@GET("movie/{id}/credits")
	Call<CastEntity> getMovieCredits(@Path("id") long id,
									 @Query("api_key") String api_key);

	@GET("movie/{id}")
	Call<MovieDetailEntity> getMovieDetals(@Path("id") long id,
										   @Query("api_key") String api_key);
}
