package io.thappx.movieinfo.internal.di.components;

import android.app.Activity;

import dagger.Component;
import io.thappx.movieinfo.internal.di.PerActivity;
import io.thappx.movieinfo.internal.di.modules.ActivityModule;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 * <p>
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
