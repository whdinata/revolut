package com.revolut.rate;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

@GlideModule
public class IntegrationGlideModule extends AppGlideModule {

    public IntegrationGlideModule() {
        RateApplication.getInstance().getApplicationComponent().inject(this);
    }

    @Override
    public void applyOptions(final Context context, final GlideBuilder builder) {
        // no-op
    }


    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        registry.append(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }
}
