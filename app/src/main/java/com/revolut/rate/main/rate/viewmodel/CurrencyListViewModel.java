package com.revolut.rate.main.rate.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.revolut.rate.common.threading.Schedulers;
import com.revolut.rate.main.rate.RateController;
import com.revolut.rate.main.rate.model.Currency;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class CurrencyListViewModel extends ViewModel {

    private final MutableLiveData<List<Currency>> currencyList = new MutableLiveData<>();
    private final MutableLiveData<Map<String, String>> currencyNameList = new MutableLiveData<>();
    private final RateController controller;
    private final Schedulers schedulers;
    private Disposable dataUpdateSubscription;
    private OnErrorListener errorListener;

    @Inject
    public CurrencyListViewModel(RateController controller, Schedulers schedulers) {
        this.controller = controller;
        this.schedulers = schedulers;
    }

    public LiveData<List<Currency>> getCurrencyList() {
        dataUpdateSubscription = controller.getRateList()
                .observeOn(schedulers.getMain())
                .doOnNext(currencyList::setValue)
                .subscribe(tracks -> {}, throwable -> {
                    if (errorListener != null) {
                        errorListener.onError(throwable);
                    }
                });

        return currencyList;
    }

    public LiveData<Map<String, String>> getCurrencyNameList() {
        controller.getCurrencyMap()
            .observeOn(schedulers.getMain())
            .subscribe(currencyNameList::setValue, throwable -> {});

        return currencyNameList;
    }

    @Override
    protected void onCleared() {
        unsubscribe();

        super.onCleared();
    }

    public CurrencyListViewModel setBaseCurrency(Currency baseCurrency) {
        controller.setBaseCurrency(baseCurrency);
        return this;
    }

    public void unsubscribe() {
        if (dataUpdateSubscription != null && !dataUpdateSubscription.isDisposed()) {
            dataUpdateSubscription.dispose();
        }

        controller.unsubscribe();
    }

    public CurrencyListViewModel setOnErrorListener(OnErrorListener errorListener) {
        this.errorListener = errorListener;
        return this;
    }

    public interface OnErrorListener {
        void onError(Throwable throwable);
    }
}
