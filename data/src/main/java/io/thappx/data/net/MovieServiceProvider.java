package io.thappx.data.net;

import retrofit.Retrofit;

/**
 * Singleton providing MovieService
 */
public class MovieServiceProvider {
    private static MovieService mMovieService;

    private MovieServiceProvider() {
        //Do Nothing
    }

    public static MovieService getMovieService() {
        if (mMovieService == null) {
            Retrofit lRetrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/movie/550")
                    .build();

            mMovieService = lRetrofit.create(MovieService.class);
        }

        return mMovieService;
    }
}
