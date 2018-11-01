package com.revolut.rate.api.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.revolut.rate.api.model.CurrencyApiModel;
import com.revolut.rate.api.serialization.CurrencyListDeserializer;

import java.util.List;

@JsonDeserialize(using = CurrencyListDeserializer.class)
public class CurrencyListResponse {

    private List<CurrencyApiModel> currencies;

    public List<CurrencyApiModel> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyApiModel> currencies) {
        this.currencies = currencies;
    }
}
