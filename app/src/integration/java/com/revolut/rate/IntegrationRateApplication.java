package com.revolut.rate;

import com.revolut.rate.database.AppDatabase;
import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.runtime.ContentResolverNotifier;

public class IntegrationRateApplication extends RateApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(FlowConfig.builder(getApplicationContext())
                .addDatabaseConfig(DatabaseConfig.builder(AppDatabase.class)
                        .modelNotifier(new ContentResolverNotifier(BuildConfig.APPLICATION_ID))
                        .build()).build());

        StethoInitializer.initialize(this);
    }
}
