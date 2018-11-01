package com.revolut.rate.common.threading;


import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

public interface Schedulers {

    Scheduler getMain();

    Scheduler getBackground();

    <T> ObservableTransformer<T, T> applyDefaultSchedulers();
}
