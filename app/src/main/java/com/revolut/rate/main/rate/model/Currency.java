package com.revolut.rate.main.rate.model;

import com.revolut.rate.main.BaseViewModel;

public class Currency extends BaseViewModel {

    private static final String MAIN_URL = "https://raw.githubusercontent.com/transferwise/currency-flags/master/src/flags/%s.png";

    private String flagUrl;
    private String currency;
    private String value;
    private String name;

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrencyAndFlagUrl(String currency) {
        this.currency = currency;

        flagUrl = String.format(MAIN_URL, currency.toLowerCase());
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
