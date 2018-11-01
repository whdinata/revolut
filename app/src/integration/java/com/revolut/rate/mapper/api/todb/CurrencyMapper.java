package com.revolut.rate.mapper.api.todb;

import com.revolut.rate.api.model.CurrencyApiModel;
import com.revolut.rate.database.model.CurrencyDataModel;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import timber.log.Timber;

public class CurrencyMapper extends ApiToDataModelMapper<CurrencyApiModel, CurrencyDataModel> {

    @Inject
    public CurrencyMapper(Provider<Map<Class<?>, ApiToDataModelMapper>> mapperProvider) {
        super(mapperProvider);
    }

    @Override
    public CurrencyDataModel map(CurrencyApiModel model) {
        CurrencyDataModel dataModel = new CurrencyDataModel();

        if (model != null) {
            dataModel.setCurrencyCode(model.getCurrencyCode());
            dataModel.setCurrencyName(model.getCurrencyName());
        }

        return dataModel;
    }
}
