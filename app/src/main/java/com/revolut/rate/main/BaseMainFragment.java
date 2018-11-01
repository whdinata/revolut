package com.revolut.rate.main;

import com.revolut.rate.common.ComponentControllerFragment;

public abstract class BaseMainFragment<T> extends ComponentControllerFragment<T> {

    protected MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }
}
