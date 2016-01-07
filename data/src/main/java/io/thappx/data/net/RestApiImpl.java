package io.thappx.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.thappx.data.BuildConfig;
import io.thappx.data.entity.CastEntity;
import io.thappx.data.entity.Configuration;
import io.thappx.data.entity.MovieDetailEntity;
import io.thappx.data.entity.MovieEntity;
import io.thappx.data.exception.CastRequestException;
import io.thappx.data.exception.MovieDetailRequestException;
import io.thappx.data.exception.MoviesRequestException;
import io.thappx.data.exception.NetworkConnectionException;
import io.thappx.data.wrapper.MovieWrapper;
import retrofit.Call;
import retrofit.Response;
import rx.Observable;
import rx.Subscriber;

@Singleton
public class RestApiImpl implements RestApi {
    private final MovieService mMovieService;
    private final Context mContext;
	private Configuration mConfiguration = null;

    @Inject
    public RestApiImpl(Context pContext, MovieService pMovieService) {
        mMovieService = pMovieService;
        mContext = pContext;
    }

    @Override
    public Observable<List<MovieEntity>> getPopularMovies() {
		Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> t) {

			}
		});
        return Observable.create((t) -> {
            if (isThereInternetConnection()) {
                Call<MovieWrapper> lCall = mMovieService.getPopularMovies(BuildConfig.TMDB_API_KEY);
                try {
                    Response<MovieWrapper> lResponse = lCall.execute();

                    if (lResponse.isSuccess()) {
                        t.onNext(lResponse.body().getMovies());
                        t.onCompleted();
                    } else {
                        int code = lResponse.code();
                        t.onError(new MoviesRequestException(
                                "Popular movies request failed with: " + code + "\n"
                                        + lResponse.errorBody().string()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    t.onError(e);
                }
            } else {
                t.onError(new NetworkConnectionException());
            }
        });
    }

	@Override
	public Observable<List<MovieEntity>> getUpcomingMovies() {
		return Observable.create((t) -> {
			if (isThereInternetConnection()) {
				Call<MovieWrapper> lCall = mMovieService.getUpcomingMovies(BuildConfig
						.TMDB_API_KEY);
				try {
					Response<MovieWrapper> lResponse = lCall.execute();

					if (lResponse.isSuccess()) {
						t.onNext(lResponse.body().getMovies());
						t.onCompleted();
					} else {
						int code = lResponse.code();
						t.onError(new MoviesRequestException(
								"Upcoming movies request failed with: " + code + "\n"
										+ lResponse.errorBody().string()));
					}
				} catch (IOException e) {
					e.printStackTrace();
					t.onError(e);
				}
			} else {
				t.onError(new NetworkConnectionException());
			}
		});
	}

	@Override
	public Observable<List<MovieEntity>> getNowShowingMovies() {
		return Observable.create((t) -> {
			if (isThereInternetConnection()) {
				Call<MovieWrapper> lCall = mMovieService.getNowPlayingMovies(BuildConfig
						.TMDB_API_KEY);
				try {
					Response<MovieWrapper> lResponse = lCall.execute();

					if (lResponse.isSuccess()) {
						t.onNext(lResponse.body().getMovies());
						t.onCompleted();
					} else {
						int code = lResponse.code();
						t.onError(new MoviesRequestException(
								"NowPlaying request failed with: " + code + "\n"
										+ lResponse.errorBody().string()));
					}
				} catch (IOException e) {
					e.printStackTrace();
					t.onError(e);
				}
			} else {
				t.onError(new NetworkConnectionException());
			}
		});
	}

	@Override
	public Observable<List<MovieEntity>> getTopRatedMovies() {
		return Observable.create((t) -> {
			if (isThereInternetConnection()) {
				Call<MovieWrapper> lCall = mMovieService.getTopRatedMovies(BuildConfig
						.TMDB_API_KEY);
				try {
					Response<MovieWrapper> lResponse = lCall.execute();

					if (lResponse.isSuccess()) {
						t.onNext(lResponse.body().getMovies());
						t.onCompleted();
					} else {
						int code = lResponse.code();
						t.onError(new MoviesRequestException(
								"TopRated request failed with: " + code + "\n"
										+ lResponse.errorBody().string()));
					}
				} catch (IOException e) {
					e.printStackTrace();
					t.onError(e);
				}
			} else {
				t.onError(new NetworkConnectionException());
			}
		});	}

	@Override
    public Observable<Configuration> getConfiguration() {
        if (mConfiguration != null) {
			return Observable.just(mConfiguration);
		} else {
			return Observable.create(t -> {
				if (isThereInternetConnection()) {
					Call<Configuration> lCall = mMovieService.getConfiguration(BuildConfig.TMDB_API_KEY);
					try {
						Response<Configuration> lResponse = lCall.execute();

						if (lResponse.isSuccess()) {
							mConfiguration = lResponse.body();
							t.onNext(mConfiguration);
						} else {
							int code = lResponse.code();
							t.onError(new MoviesRequestException(
									"Configuration request failed with: " + code + "\n"
											+ lResponse.errorBody().string()));
						}
					} catch (IOException e) {
						e.printStackTrace();
						t.onError(e);
					}
				} else {
					t.onError(new NetworkConnectionException());
				}
			});
		}
    }

	@Override
	public Observable<MovieDetailEntity> getMovieDetails(long id) {
		return Observable.create(t -> {
			if (isThereInternetConnection()) {
				Call<MovieDetailEntity> lCall = mMovieService
						.getMovieDetals(id, BuildConfig.TMDB_API_KEY);
				try {
					Response<MovieDetailEntity> lResponse = lCall.execute();

					if (lResponse.isSuccess()) {
						MovieDetailEntity lEntity = lResponse.body();
						lEntity.setMovieId(id);
						lEntity.setCompanyString();

						t.onNext(lResponse.body());
						t.onCompleted();
					} else {
						t.onError(new MovieDetailRequestException("Failed to fetch movie details"));
					}
				} catch (IOException e) {
					e.printStackTrace();
					t.onError(e);
				}
			} else {
				t.onError(new NetworkConnectionException());
			}
		});
	}

	@Override
	public Observable<CastEntity> getMovieCast(long id) {
		return Observable.create(t -> {
			if (isThereInternetConnection()) {
				Call<CastEntity> lCall = mMovieService
						.getMovieCredits(id, BuildConfig.TMDB_API_KEY);
				try {
					Response<CastEntity> lResponse = lCall.execute();

					if (lResponse.isSuccess()) {
						CastEntity lCastEntity = lResponse.body();
						lCastEntity.setMovieID(id);
						lCastEntity.setCastString();

						t.onNext(lResponse.body());
						t.onCompleted();
					} else {
						t.onError(new CastRequestException("Failed to fetch cast"));
					}
				} catch (IOException e) {
					e.printStackTrace();
					t.onError(e);
				}
			} else {
				t.onError(new NetworkConnectionException());
			}
		});
	}

	/**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
