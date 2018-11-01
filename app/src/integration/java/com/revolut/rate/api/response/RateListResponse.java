package com.revolut.rate.api.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.revolut.rate.api.converter.RateListConverter;
import com.revolut.rate.api.model.CurrencyApiModel;

import java.util.List;

public class RateListResponse {

    private String base;
    private String date;

    @JsonDeserialize(converter = RateListConverter.class)
    private List<CurrencyApiModel> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<CurrencyApiModel> getRates() {
        return rates;
    }

    public void setRates(List<CurrencyApiModel> rates) {
        this.rates = rates;
    }
}
