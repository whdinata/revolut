package com.revolut.rate.mapper.api;


import com.revolut.rate.mapper.api.todb.ApiToDataModelMapperModule;
import com.revolut.rate.mapper.api.toview.ApiToViewModelMapperModule;

import dagger.Module;

@Module(includes = {ApiToViewModelMapperModule.class, ApiToDataModelMapperModule.class})
public class ApiMapperModule {
}
