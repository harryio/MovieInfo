package io.thappx.movieinfo.internal.di.components;

import android.app.Activity;

import dagger.Component;
import io.thappx.movieinfo.internal.di.PerActivity;
import io.thappx.movieinfo.internal.di.modules.ActivityModule;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
	Activity activity();
}
