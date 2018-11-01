package com.revolut.rate.mapper.db.fromdb;

import com.revolut.rate.mapper.BaseMapper;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Map;

import javax.inject.Provider;

public abstract class DataToViewModelMapper<T extends BaseModel, R> extends BaseMapper<T, R> {

    public DataToViewModelMapper(Provider<Map<Class<?>, DataToViewModelMapper>> mapperProvider) {
        super(mapperProvider);
    }
}
