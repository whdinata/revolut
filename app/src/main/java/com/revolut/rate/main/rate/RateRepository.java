package com.revolut.rate.main.rate;

import com.revolut.rate.api.response.RateListResponse;
import com.revolut.rate.database.model.CurrencyDataModel;

import java.util.List;

import io.reactivex.Single;

public interface RateRepository {

    Single<RateListResponse> getRateList(String baseCurrency);

    Single<List<CurrencyDataModel>> getCurrencyList();
}
