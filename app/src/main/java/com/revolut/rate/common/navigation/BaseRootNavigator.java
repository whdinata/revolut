package com.revolut.rate.common.navigation;

import com.revolut.rate.common.BaseActivity;

public class BaseRootNavigator extends BaseNavigator {

    private final BaseActivity activity;

    protected BaseRootNavigator(BaseActivity activity) {
        this.activity = activity;
    }

    @Override
    protected NavigationController getNavigationController() {
        return activity;
    }
}
