package com.revolut.rate.mapper.api.toview;

import com.revolut.rate.api.model.CurrencyApiModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class ApiToViewModelMapperModule {

    @Binds
    @IntoMap
    @ClassKey(CurrencyApiModel.class)
    abstract ApiToViewModelMapper bindRateMapper(RateMapper mapper);
}
