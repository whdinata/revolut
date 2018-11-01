package com.revolut.rate.main.rate;

import com.revolut.rate.common.threading.Schedulers;
import com.revolut.rate.main.rate.viewmodel.CurrencyListViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class RateModule {

    @Provides
    @Rate
    public RateRepository provideRateRepository(RateRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @Rate
    public RateController provideRateController(RateControllerImpl controller) {
        return controller;
    }

    @Provides
    @Rate
    public CurrencyListViewModelFactory provideCurrencyListViewModelFactory(RateController controller,
                                                                            Schedulers schedulers) {
        return new CurrencyListViewModelFactory(controller, schedulers);
    }
}
