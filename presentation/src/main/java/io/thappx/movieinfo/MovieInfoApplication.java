package io.thappx.movieinfo;

import android.app.Application;

import io.thappx.movieinfo.internal.di.components.ApplicationComponent;
import io.thappx.movieinfo.internal.di.components.DaggerApplicationComponent;
import io.thappx.movieinfo.internal.di.modules.ApplicationModule;

public class MovieInfoApplication extends Application {
	private ApplicationComponent mApplicationComponent;

	@Override
	public void onCreate() {
		super.onCreate();

		this.initializeInjector();
	}

	private void initializeInjector() {
		mApplicationComponent = DaggerApplicationComponent.builder()
				.applicationModule(new ApplicationModule(this))
				.build();
	}

	public ApplicationComponent getApplicationComponent() {
		return this.mApplicationComponent;
	}
}
