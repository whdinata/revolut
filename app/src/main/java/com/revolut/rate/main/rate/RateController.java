package com.revolut.rate.main.rate;

import com.revolut.rate.main.rate.model.Currency;

import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface RateController {

    Flowable<List<Currency>> getRateList();

    Single<Map<String, String>> getCurrencyMap();

    Completable fetchRatePeriodically(long second);

    void setBaseCurrency(Currency baseCurrency);

    void unsubscribe();
}
