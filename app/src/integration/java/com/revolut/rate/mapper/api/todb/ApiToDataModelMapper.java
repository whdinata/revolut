package com.revolut.rate.mapper.api.todb;

import com.revolut.rate.api.model.BaseApiModel;
import com.revolut.rate.mapper.BaseMapper;

import java.util.Map;

import javax.inject.Provider;

public abstract class ApiToDataModelMapper<T extends BaseApiModel, R> extends BaseMapper<T, R> {

    public ApiToDataModelMapper(Provider<Map<Class<?>, ApiToDataModelMapper>> mapperProvider) {
        super(mapperProvider);
    }
}
