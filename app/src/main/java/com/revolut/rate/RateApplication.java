package com.revolut.rate;

import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import timber.log.Timber;

public class RateApplication extends MultiDexApplication {

    private static RateApplication instance;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
         return applicationComponent;
    }

    public static RateApplication getInstance() {
        return instance;
    }
}
