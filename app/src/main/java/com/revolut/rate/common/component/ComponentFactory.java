package com.revolut.rate.common.component;

import io.reactivex.annotations.NonNull;

public interface ComponentFactory<T> {

    @NonNull
    T createComponent();
}
