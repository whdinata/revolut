package com.revolut.rate.mapper;

import com.revolut.rate.main.BaseViewModel;
import com.revolut.rate.mapper.api.todb.ApiToDataModelMapper;
import com.revolut.rate.mapper.api.toview.ApiToViewModelMapper;
import com.revolut.rate.mapper.db.fromdb.DataToViewModelMapper;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class MapperHelper {

    private final Map<Class<?>, ApiToViewModelMapper> apiToViewModelMappers;
    private final Map<Class<?>, ApiToDataModelMapper> apiToDataModelMappers;

    @Inject
    MapperHelper(Map<Class<?>, ApiToViewModelMapper> apiToViewModelMappers,
                 Map<Class<?>, ApiToDataModelMapper> apiToDataModelMappers) {
        this.apiToViewModelMappers = apiToViewModelMappers;
        this.apiToDataModelMappers = apiToDataModelMappers;
    }

    @SuppressWarnings("unchecked")
    public <T, R extends BaseViewModel> List<R> mapApiModelListToViewModelList(List<T> modelList,
                                                                                Class<T> modelClass) {
        ApiToViewModelMapper mapper = apiToViewModelMappers.get(modelClass);

        return (List<R>) mapper.mapCollection(modelList);
    }

    @SuppressWarnings("unchecked")
    public <T, R extends BaseModel> List<R> mapApiModelListToDataModelList(List<T> modelList,
                                                                           Class<T> modelClass) {
        ApiToDataModelMapper mapper = apiToDataModelMappers.get(modelClass);

        return (List<R>) mapper.mapCollection(modelList);
    }

    @SuppressWarnings("unchecked")
    public <T, R extends BaseViewModel> R mapApiModelToViewModel(T model, Class<T> modelClass) {
        ApiToViewModelMapper mapper = apiToViewModelMappers.get(modelClass);

        return (R) mapper.map(model);
    }
}
