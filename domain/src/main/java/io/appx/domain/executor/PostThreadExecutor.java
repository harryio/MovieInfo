package io.appx.domain.executor;

import rx.Scheduler;

public interface PostThreadExecutor {
	Scheduler getScheduler();
}
