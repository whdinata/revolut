package com.revolut.rate.api;


import com.revolut.rate.api.response.CurrencyListResponse;
import com.revolut.rate.api.response.RateListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClient {

    @GET("/latest")
    Single<RateListResponse> getRates(@Query("base") String baseCurrencyCode);

    @GET("https://openexchangerates.org/api/currencies.json?prettyprint=0")
    Single<CurrencyListResponse> getCurrencies();
}
