package io.thappx.movieinfo.internal.di.modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import io.thappx.movieinfo.internal.di.PerActivity;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity pActivity) {
        mActivity = pActivity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return mActivity;
    }
}
