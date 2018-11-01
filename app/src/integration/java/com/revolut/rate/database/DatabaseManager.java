package com.revolut.rate.database;

import com.raizlabs.android.dbflow.sql.language.SQLOperator;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

public class DatabaseManager {

    @Inject
    DatabaseManager() {}

    @Nullable
    public <T> T getModel(Class<T> modelClass) {
        return SQLite.select()
                .from(modelClass)
                .querySingle();
    }

    @Nullable
    public <T> List<T> getModelList(Class<T> modelClass) {
        return SQLite.select()
                .from(modelClass)
                .queryList();
    }

    @Nullable
    public <T> List<T> getModelList(Class<T> modelClass, SQLOperator... conditions) {
        return SQLite.select()
                .from(modelClass)
                .where(conditions)
                .queryList();
    }
}
