package io.thappx.movieinfo.internal.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.appx.domain.executor.PostThreadExecutor;
import io.appx.domain.executor.ThreadExecutor;
import io.appx.domain.repository.MovieRepository;
import io.thappx.movieinfo.internal.di.modules.ApplicationModule;
import io.thappx.movieinfo.view.activity.BaseActivity;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
	void inject(BaseActivity pBaseActivity);

	Context context();
	ThreadExecutor threadExecutor();
	PostThreadExecutor postThreadExecutor();
	MovieRepository movieRepository();
}
