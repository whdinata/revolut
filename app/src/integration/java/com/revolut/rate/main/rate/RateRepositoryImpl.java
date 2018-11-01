package com.revolut.rate.main.rate;

import com.revolut.rate.api.ApiClient;
import com.revolut.rate.api.model.CurrencyApiModel;
import com.revolut.rate.api.response.RateListResponse;
import com.revolut.rate.database.model.CurrencyDataModel;
import com.revolut.rate.mapper.MapperHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class RateRepositoryImpl implements RateRepository {

    private ApiClient apiClient;
    private CurrencyDataManager dataManager;
    private MapperHelper mapperHelper;

    @Inject
    public RateRepositoryImpl(ApiClient apiClient, CurrencyDataManager dataManager,
                              MapperHelper mapperHelper) {
        this.apiClient = apiClient;
        this.dataManager = dataManager;
        this.mapperHelper = mapperHelper;
    }

    @Override
    public Single<RateListResponse> getRateList(String baseCurrency) {
        return apiClient.getRates(baseCurrency);
    }

    @Override
    public Single<List<CurrencyDataModel>> getCurrencyList() {
        return Single.just(dataManager.getCurrencyList())
                .flatMap(currencyDataModelList -> {
                    if (currencyDataModelList.isEmpty()) {
                        return fetchCurrencyListFromServer()
                                .map(currencyDataModels ->
                                        Single.just(dataManager.saveCurrencyList(currencyDataModels)))
                                .map(success -> dataManager.getCurrencyList());
                    }

                    return Single.just(currencyDataModelList);
                });
    }

    private Single<List<CurrencyDataModel>> fetchCurrencyListFromServer() {
        return apiClient.getCurrencies()
                .map(currencyListResponse -> currencyListResponse.getCurrencies())
                .map(currencyApiModels -> {
                    List<CurrencyDataModel> dataModels =
                            mapperHelper.mapApiModelListToDataModelList(currencyApiModels,
                            CurrencyApiModel.class);

                    return dataModels;
                });
    }
}
