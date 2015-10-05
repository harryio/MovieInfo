package io.thappx.movieinfo.internal.di.modules;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio.sqlite.queries.InsertQuery;
import com.pushtorefresh.storio.sqlite.queries.UpdateQuery;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.Proxy;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.appx.domain.executor.PostThreadExecutor;
import io.appx.domain.executor.ThreadExecutor;
import io.appx.domain.repository.MovieRepository;
import io.thappx.data.database.MovieDbHelper;
import io.thappx.data.entity.CastEntity;
import io.thappx.data.entity.CastEntityStorIOSQLiteDeleteResolver;
import io.thappx.data.entity.CastEntityStorIOSQLiteGetResolver;
import io.thappx.data.entity.CastEntityStorIOSQLitePutResolver;
import io.thappx.data.entity.MovieDetailEntity;
import io.thappx.data.entity.MovieDetailEntityStorIOSQLiteDeleteResolver;
import io.thappx.data.entity.MovieDetailEntityStorIOSQLiteGetResolver;
import io.thappx.data.entity.MovieDetailEntityStorIOSQLitePutResolver;
import io.thappx.data.entity.MovieEntity;
import io.thappx.data.entity.MovieEntityStorIOSQLiteDeleteResolver;
import io.thappx.data.entity.MovieEntityStorIOSQLiteGetResolver;
import io.thappx.data.entity.MovieEntityStorIOSQLitePutResolver;
import io.thappx.data.executor.JobExecutor;
import io.thappx.data.net.MovieService;
import io.thappx.data.repository.MovieDataRepository;
import io.thappx.movieinfo.MovieInfoApplication;
import io.thappx.movieinfo.UIThread;
import io.thappx.movieinfo.navigation.Navigator;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

@Module
public class ApplicationModule {
	private final MovieInfoApplication mApplication;

	public ApplicationModule(MovieInfoApplication pApplication) {
		mApplication = pApplication;
	}

	@Provides @Singleton Context provideApplicationContext() {
		return this.mApplication;
	}

	@Provides @Singleton MovieRepository provideMovieRepository(MovieDataRepository pMovieDataRepository) {
		return pMovieDataRepository;
	}

	@Provides @Singleton MovieService provideMovieService() {
		Retrofit lRetrofit = new Retrofit.Builder()
				.baseUrl("https://api.themoviedb.org/3/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		return lRetrofit.create(MovieService.class);
	}

	@Provides @Singleton MovieDbHelper provideMovieDbHelper(Context pContext) {
		return new MovieDbHelper(pContext);
	}

	@Provides @Singleton StorIOSQLite provideStorioSQLite(
			MovieDbHelper pDbHelper,
			SQLiteTypeMapping<MovieEntity> pMovieMapping,
			SQLiteTypeMapping<MovieDetailEntity> pMovieDetailMapping,
			SQLiteTypeMapping<CastEntity> pCastMapping) {
		return DefaultStorIOSQLite.builder()
				.sqliteOpenHelper(pDbHelper)
				.addTypeMapping(MovieEntity.class, pMovieMapping)
				.addTypeMapping(MovieDetailEntity.class, pMovieDetailMapping)
				.addTypeMapping(CastEntity.class, pCastMapping)
				.build();
	}

	@Provides @Singleton ThreadExecutor provideThreadExecutor(JobExecutor pJobExecutor) {
		return pJobExecutor;
	}

	@Provides @Singleton PostThreadExecutor providePostThreadExecutionThread() {
		return new UIThread();
	}

	@Provides @Singleton
	SQLiteTypeMapping<MovieEntity> provideMovieEntityMapping() {
		return SQLiteTypeMapping.<MovieEntity>builder()
				.putResolver(new MovieEntityStorIOSQLitePutResolver())
				.getResolver(new MovieEntityStorIOSQLiteGetResolver())
				.deleteResolver(new MovieEntityStorIOSQLiteDeleteResolver())
				.build();
	}

	@Provides @Singleton
	SQLiteTypeMapping<MovieDetailEntity> provideMovieDetailEntity() {
		return SQLiteTypeMapping.<MovieDetailEntity>builder()
				.putResolver(new MovieDetailEntityStorIOSQLitePutResolver())
				.getResolver(new MovieDetailEntityStorIOSQLiteGetResolver())
				.deleteResolver(new MovieDetailEntityStorIOSQLiteDeleteResolver())
				.build();
	}

	@Provides @Singleton
	SQLiteTypeMapping<CastEntity> provideCaseEntityMapping() {
		return SQLiteTypeMapping.<CastEntity>builder()
				.putResolver(new CastEntityStorIOSQLitePutResolver())
				.getResolver(new CastEntityStorIOSQLiteGetResolver())
				.deleteResolver(new CastEntityStorIOSQLiteDeleteResolver())
				.build();
	}

	@Provides @Singleton
	Navigator getNavigator() {
		return new Navigator();
	}
}
