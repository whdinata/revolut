package com.revolut.rate.main.rate.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.revolut.rate.common.threading.Schedulers;
import com.revolut.rate.main.rate.RateController;

import javax.inject.Inject;

public class CurrencyListViewModelFactory implements ViewModelProvider.Factory {

    private final RateController controller;
    private final Schedulers schedulers;

    @Inject
    public CurrencyListViewModelFactory(RateController controller,
                                        Schedulers schedulers) {
        this.controller = controller;
        this.schedulers = schedulers;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CurrencyListViewModel.class)) {
            return (T) new CurrencyListViewModel(controller, schedulers);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
