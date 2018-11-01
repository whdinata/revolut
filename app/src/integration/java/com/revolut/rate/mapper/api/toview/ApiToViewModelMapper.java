package com.revolut.rate.mapper.api.toview;

import com.revolut.rate.api.model.BaseApiModel;
import com.revolut.rate.mapper.BaseMapper;

import java.util.Map;

import javax.inject.Provider;

public abstract class ApiToViewModelMapper<T extends BaseApiModel, R> extends BaseMapper<T, R> {

    public ApiToViewModelMapper(Provider<Map<Class<?>, ApiToViewModelMapper>> mapperProvider) {
        super(mapperProvider);
    }
}
