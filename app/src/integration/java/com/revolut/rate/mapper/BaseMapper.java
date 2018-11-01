package com.revolut.rate.mapper;

import com.revolut.rate.common.util.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.inject.Provider;

public abstract class BaseMapper<T, R> implements Mapper<T, R> {

    private final Provider<Map<Class<?>, ? extends Mapper>> mapperProvider;

    protected BaseMapper(Provider<?> mapperProvider) {
        this.mapperProvider = (Provider<Map<Class<?>, ? extends Mapper>>) mapperProvider;
    }

    protected <E, C extends Collection> C mapCollection(Collection<E> modelList) {
        C mappedList = (C) new ArrayList<>();

        if(modelList != null) {
            for(E model : modelList) {
                mappedList.add(mapModel(model));
            }
        }

        return mappedList;
    }

    protected <E, O> O mapModel(E model) {
        if(model == null) {
            return null;
        }

        Map<Class<?>, ? extends Mapper> mapperMap = mapperProvider.get();
        Mapper mapper = mapperMap.get(model.getClass());

        return (O) mapper.map(model);
    }
}

