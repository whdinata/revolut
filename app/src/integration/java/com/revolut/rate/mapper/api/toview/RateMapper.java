package com.revolut.rate.mapper.api.toview;

import com.revolut.rate.api.model.CurrencyApiModel;
import com.revolut.rate.main.rate.model.Currency;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class RateMapper extends ApiToViewModelMapper<CurrencyApiModel, Currency> {

    private static final Map<String, String> currencyMap = new HashMap<>();
    static {
        currencyMap.put("AUD", "Australian Dollar");
        currencyMap.put("BGN", "Bulgarian Lev");
        currencyMap.put("BRL", "Brazilian Real");
        currencyMap.put("CAD", "Canadian Dollar");
        currencyMap.put("CHF", "Swiss Franc");
        currencyMap.put("CNY", "Renminbi (Yuan)");
        currencyMap.put("CZK", "Czech Koruna");
        currencyMap.put("DKK", "Danish Krone");
        currencyMap.put("GBP", "Pound Sterling");
        currencyMap.put("HKD", "Hong Kong Dollar");
        currencyMap.put("HRK", "Croatian Kuna");
        currencyMap.put("HUF", "Forint");
        currencyMap.put("IDR", "Rupiah");
        currencyMap.put("ILS", "New Israeli Sheqel");
        currencyMap.put("INR", "Indian Rupee");
        currencyMap.put("ISK", "Iceland Krona");
        currencyMap.put("JPY", "Yen");
        currencyMap.put("KRW", "Won");
        currencyMap.put("MXN", "Mexican Peso");
        currencyMap.put("MYR", "");
        currencyMap.put("NOK", "");
        currencyMap.put("NZD", "");
        currencyMap.put("PHP", "");
        currencyMap.put("PLN", "");
        currencyMap.put("RON", "");
        currencyMap.put("RUB", "");
        currencyMap.put("SEK", "");
        currencyMap.put("SGD", "");
        currencyMap.put("THB", "");
        currencyMap.put("TRY", "");
        currencyMap.put("USD", "");
        currencyMap.put("ZAR", "");
    }

    @Inject
    public RateMapper(Provider<Map<Class<?>, ApiToViewModelMapper>> mapperProvider) {
        super(mapperProvider);
    }

    @Override
    public Currency map(CurrencyApiModel model) {
        Currency rate = new Currency();
        rate.setCurrencyAndFlagUrl(model.getCurrencyCode());
        rate.setValue(Double.toString(model.getValue()));


        return rate;
    }
}
