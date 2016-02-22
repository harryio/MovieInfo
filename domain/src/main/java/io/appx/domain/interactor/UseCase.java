package io.appx.domain.interactor;

import io.appx.domain.executor.PostThreadExecutor;
import io.appx.domain.executor.ThreadExecutor;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link rx.Subscriber}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
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

    @SuppressWarnings("unchecked")
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
