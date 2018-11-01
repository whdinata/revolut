package com.revolut.rate.common.threading;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

@Module
public class ThreadModule {

    @Provides
    @Singleton
    @UiScheduler
    Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @BackgroundScheduler
    Scheduler provideBackgroundScheduler() {
        int maxThreads = Math.max(1, Runtime.getRuntime().availableProcessors() - 1);

        if (maxThreads == 1) {

            return io.reactivex.schedulers.Schedulers.from(Executors.newSingleThreadExecutor());
        } else {
            return io.reactivex.schedulers.Schedulers.from(Executors.newFixedThreadPool(maxThreads));
        }
    }

    @Provides
    @Singleton
    Schedulers provideSchedulers(RxSchedulers schedulers) {
        return schedulers;
    }
}

