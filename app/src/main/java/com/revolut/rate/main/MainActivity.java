package com.revolut.rate.main;

import android.os.Bundle;

import com.revolut.rate.R;
import com.revolut.rate.RateApplication;
import com.revolut.rate.common.ComponentCacheActivity;
import com.revolut.rate.common.module.ActivityModule;
import com.revolut.rate.common.scope.PerActivity;

import org.androidannotations.annotations.EActivity;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@EActivity(R.layout.activity_main)
public class MainActivity extends ComponentCacheActivity {

    @Inject
    MainNavigator navigator;

    private MainComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);

        navigator.navigateToEnterUserId();
    }

    public MainComponent getComponent() {
        if (component == null) {
            component = DaggerMainComponent.builder()
                    .applicationComponent(RateApplication.getInstance().getApplicationComponent())
                    .mainModule(new MainModule(this))
                    .build();
        }

        return component;
    }

    @Override
    protected int getContainerViewResourceId() {
        return R.id.content;
    }

    @Module
    static class MainModule extends ActivityModule<MainActivity> {

        MainModule(MainActivity activity) {
            super(activity);
        }

        @Provides
        @PerActivity
        MainActivity provideMainActivity() {
            return activity;
        }

        @Provides
        @PerActivity
        MainNavigator provideMainNavigator(MainNavigatorImpl navigator) {
            return navigator;
        }
    }
}
