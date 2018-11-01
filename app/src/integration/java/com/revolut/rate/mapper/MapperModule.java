package com.revolut.rate.mapper;


import com.revolut.rate.mapper.api.ApiMapperModule;
import com.revolut.rate.mapper.db.DbMapperModule;

import dagger.Module;

@Module(includes = {ApiMapperModule.class})
public class MapperModule {
}
