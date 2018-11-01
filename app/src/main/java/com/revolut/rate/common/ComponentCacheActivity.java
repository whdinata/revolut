package com.revolut.rate.common;

import android.util.SparseArray;

import com.revolut.rate.common.component.ComponentCache;

import org.androidannotations.annotations.EActivity;

import java.lang.ref.WeakReference;

import javax.annotation.Nullable;

@EActivity
public abstract class ComponentCacheActivity extends BaseActivity implements ComponentCache {

    private SparseArray<WeakReference<Object>> components;

    @Override
    protected void onDestroy() {
        if (components != null) {
            clearCache();
        }

        super.onDestroy();
    }

    @Nullable
    @Override
    public <T> T getComponent(int index) {
        if (components == null) {
            return null;
        }

        WeakReference<T> reference = (WeakReference<T>) components.get(index);
        if(reference == null) {
            return null;
        }

        return reference.get();
    }

    @Override
    public <T> void setComponent(int index, @Nullable T component) {
        if(components == null) {
            components = new SparseArray<>();
        }

        components.put(index, new WeakReference<>(component));
    }

    @Override
    public void clearCache() {
        components.clear();
    }
}
