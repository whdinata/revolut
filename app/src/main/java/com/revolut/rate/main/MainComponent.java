package com.revolut.rate.main;

import com.revolut.rate.ApplicationComponent;
import com.revolut.rate.common.scope.PerActivity;
import com.revolut.rate.main.rate.RateSubComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = MainActivity.MainModule.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);

    RateSubComponent getRateSubComponent();
}
