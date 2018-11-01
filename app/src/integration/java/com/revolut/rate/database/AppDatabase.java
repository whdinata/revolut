package com.revolut.rate.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public final class AppDatabase {

    public static final String NAME = "AppDatabase";

    static final int VERSION = 1;

    private AppDatabase() {}
}
