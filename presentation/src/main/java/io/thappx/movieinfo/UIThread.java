package io.thappx.movieinfo;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.appx.domain.executor.PostThreadExecutor;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

@Singleton
public class UIThread implements PostThreadExecutor {
	@Inject
	public UIThread(){}

	@Override
	public Scheduler getScheduler() {
		return AndroidSchedulers.mainThread();
	}
}
