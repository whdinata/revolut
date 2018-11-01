package com.revolut.rate;

import com.revolut.rate.api.ApiClient;
import com.revolut.rate.api.ApiModule;
import com.revolut.rate.common.threading.ThreadModule;
import com.revolut.rate.mapper.MapperHelper;
import com.revolut.rate.mapper.MapperModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ThreadModule.class, ApiModule.class, MapperModule.class})
public interface ApplicationComponent extends BaseApplicationComponent {

    void inject(IntegrationGlideModule glideModule);

    ApiClient getApiClient();

    MapperHelper getMapperHelper();
}
