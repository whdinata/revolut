package com.revolut.rate.mapper.api.todb;

import com.revolut.rate.api.model.CurrencyApiModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class ApiToDataModelMapperModule {

    @Binds
    @IntoMap
    @ClassKey(CurrencyApiModel.class)
    abstract ApiToDataModelMapper bindCurrencyMapper(CurrencyMapper mapper);
}
