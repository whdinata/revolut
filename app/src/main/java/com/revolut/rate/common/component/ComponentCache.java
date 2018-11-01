package com.revolut.rate.common.component;

import javax.annotation.Nullable;

public interface ComponentCache {

    @Nullable
    <T> T getComponent(int index);

    <T> void setComponent(int index, @Nullable T component);

    void clearCache();
}
