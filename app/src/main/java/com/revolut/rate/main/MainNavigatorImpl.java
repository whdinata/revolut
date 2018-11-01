package com.revolut.rate.main;

import com.revolut.rate.common.navigation.BaseRootNavigator;
import com.revolut.rate.main.rate.ui.ConverterFragmentImpl;

import javax.inject.Inject;

public class MainNavigatorImpl extends BaseRootNavigator implements MainNavigator {

    @Inject
    MainNavigatorImpl(MainActivity activity) {
        super(activity);
    }

    @Override
    public void navigateToEnterUserId() {
        moveToScreen(ConverterFragmentImpl.builder().build());
    }
}
