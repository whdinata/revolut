package com.revolut.rate.common.module;

import com.revolut.rate.common.BaseActivity;
import com.revolut.rate.common.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ActivityModule<T extends BaseActivity> {

    protected final T activity;

    public ActivityModule(T activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    protected BaseActivity provideActivity() {
        return activity;
    }
}
