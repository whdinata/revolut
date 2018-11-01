package com.revolut.rate.api.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.revolut.rate.api.model.CurrencyApiModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RateListConverter extends StdConverter<Map<String, Double>, List<CurrencyApiModel>> {

    @Override
    public List<CurrencyApiModel> convert(Map<String, Double> value) {

        List<CurrencyApiModel> list = new ArrayList<>();

        for(Map.Entry<String, Double> entry : value.entrySet()) {
            CurrencyApiModel model = new CurrencyApiModel();
            model.setCurrencyCode(entry.getKey());
            model.setValue(entry.getValue());

            list.add(model);
        }

        return list;
    }
}
