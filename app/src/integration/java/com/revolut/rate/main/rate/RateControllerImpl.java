package com.revolut.rate.main.rate;

import com.revolut.rate.api.model.CurrencyApiModel;
import com.revolut.rate.common.threading.Schedulers;
import com.revolut.rate.database.model.CurrencyDataModel;
import com.revolut.rate.main.rate.model.Currency;
import com.revolut.rate.mapper.MapperHelper;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

public class RateControllerImpl implements RateController {

    private RateRepository repository;
    private MapperHelper mapperHelper;
    private Schedulers schedulers;

    private PublishSubject<List<Currency>> rateListSubject;
    private Flowable<List<Currency>> rateListFlowable;

    private Currency baseCurrency;
    private Disposable fetcherDisposable;
    private static final DecimalFormat formatter = new DecimalFormat("0.##");

    @Inject
    public RateControllerImpl(RateRepository repository, MapperHelper mapperHelper,
                              Schedulers schedulers) {
        this.repository = repository;
        this.mapperHelper = mapperHelper;
        this.schedulers = schedulers;
    }

    @Override
    public Flowable<List<Currency>> getRateList() {
        if (rateListSubject == null) {
            rateListSubject = PublishSubject.create();
            rateListFlowable = rateListSubject
                    .doOnSubscribe(disposable -> {
                        if (!isFetcherRunning()) {
                            fetchRatePeriodically(1)
                                    .retry()
                                    .subscribe(new CompletableObserver() {
                                        @Override
                                        public void onSubscribe(Disposable d) {
                                            fetcherDisposable = d;
                                        }

                                        @Override
                                        public void onComplete() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Timber.e(e);
                                        }
                                    });
                        }
                    })
                    .toFlowable(BackpressureStrategy.LATEST);
        }

        return rateListFlowable;
    }

    @Override
    public Completable fetchRatePeriodically(long second) {
        return Observable.interval(0, second, TimeUnit.SECONDS)
                .subscribeOn(schedulers.getBackground())
                .flatMap(count -> fetchRateList(baseCurrency).toObservable())
                .ignoreElements();
    }

    private boolean isFetcherRunning() {
        return fetcherDisposable != null && !fetcherDisposable.isDisposed();
    }

    private Single<List<Currency>> fetchRateList(Currency baseCurrency) {
        return repository.getRateList(baseCurrency.getCurrency())
                .map(rateListResponse -> {
                    List<Currency> rateList = mapperHelper.mapApiModelListToViewModelList(
                            rateListResponse.getRates(), CurrencyApiModel.class);

                    for(Currency currency : rateList) {
                        double total = Double.parseDouble(currency.getValue()) * Double.parseDouble(baseCurrency.getValue());
                        currency.setValue(formatter.format(total));
                    }

                    rateList.add(0, baseCurrency);

                    return rateList;
                })
                .doOnSuccess(rateListSubject::onNext);
    }

    @Override
    public Single<Map<String, String>> getCurrencyMap() {
        return repository.getCurrencyList()
                .map(currencyDataModelList -> {
                    Map<String, String> map = new HashMap<>();

                    for(CurrencyDataModel dataModel : currencyDataModelList) {
                        map.put(dataModel.getCurrencyCode(), dataModel.getCurrencyName());
                    }

                    return map;
                });
    }

    @Override
    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    @Override
    public void unsubscribe() {
        if(isFetcherRunning()) {
            fetcherDisposable.dispose();
        }
    }
}
