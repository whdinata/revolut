package com.revolut.rate.common.navigation;

import android.support.v4.app.FragmentManager;

import com.revolut.rate.common.BaseFragment;

public interface NavigationController {

    void updateCurrentFragment();

    void setCurrentFragment(BaseFragment baseFragment);

    BaseFragment getCurrentFragment();

    FragmentManager getNavigationManager();
}
