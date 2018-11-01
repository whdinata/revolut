package com.revolut.rate;

import android.content.Context;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.Interceptor;

public final class StethoInitializer {

    private StethoInitializer() {
        // utility class
    }

    static void initialize(final Context context) {
        Stetho.initializeWithDefaults(context);
    }

    public static Interceptor createInterceptor() {
        return new StethoInterceptor();
    }
}
