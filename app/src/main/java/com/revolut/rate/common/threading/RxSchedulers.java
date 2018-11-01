package com.revolut.rate.common.threading;

import javax.inject.Inject;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

class RxSchedulers implements Schedulers {

    private final Scheduler main;
    private final Scheduler background;

    @Inject
    RxSchedulers(@UiScheduler Scheduler main, @BackgroundScheduler Scheduler background) {
        this.main = main;
        this.background = background;
    }

    @Override
    public Scheduler getMain() {
        return main;
    }

    @Override
    public Scheduler getBackground() {
        return background;
    }

    @Override
    public <T> ObservableTransformer<T, T> applyDefaultSchedulers() {
        return observable -> observable.subscribeOn(background)
                                        .observeOn(main);
    }
}
