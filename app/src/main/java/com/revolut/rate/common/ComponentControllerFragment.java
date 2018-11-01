package com.revolut.rate.common;

import android.content.Context;
import android.support.annotation.IdRes;

import com.revolut.rate.common.component.ComponentCache;
import com.revolut.rate.common.component.ComponentFactory;

import io.reactivex.annotations.NonNull;

public abstract class ComponentControllerFragment<T> extends BaseFragment
        implements ComponentFactory<T> {

    private T component;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ComponentCache cache = getComponentCache();
        component = cache.getComponent(getComponentId());

        if(component == null) {
            component = createComponent();
            cache.setComponent(getComponentId(), component);
        }
    }

    @NonNull
    protected ComponentCache getComponentCache() {
        return (ComponentCache) getActivity();
    }

    @NonNull
    public T getComponent() {
        return component;
    }

    @IdRes
    protected abstract int getComponentId();
}
