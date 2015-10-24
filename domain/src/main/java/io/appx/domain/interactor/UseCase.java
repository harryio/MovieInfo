package io.appx.domain.interactor;

import io.appx.domain.executor.PostThreadExecutor;
import io.appx.domain.executor.ThreadExecutor;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class UseCase {
	private final ThreadExecutor mThreadExecutor;
	private final PostThreadExecutor mPostThreadExecutor;

	private Subscription mSubscription = Subscriptions.empty();

	protected UseCase(ThreadExecutor pThreadExecutor,
					  PostThreadExecutor pPostThreadExecutor) {
		mThreadExecutor = pThreadExecutor;
		mPostThreadExecutor = pPostThreadExecutor;
	}

	protected abstract Observable buildUseCaseObservable();

	@SuppressWarnings ("unchecked")
	public void execute(Subscriber pSubscriber) {
		Observable lObservable = buildUseCaseObservable();
		this.mSubscription = lObservable
				.subscribeOn(Schedulers.from(mThreadExecutor))
				.observeOn(mPostThreadExecutor.getScheduler())
				.subscribe(pSubscriber);
	}

	public void unsubscribe() {
		mSubscription.unsubscribe();
	}
}
