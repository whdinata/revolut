package com.revolut.rate;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class ApplicationModule {

    private final RateApplication application;

    ApplicationModule(RateApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }
}
