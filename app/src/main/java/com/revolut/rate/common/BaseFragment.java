package com.revolut.rate.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.revolut.rate.R;
import com.revolut.rate.common.navigation.NavigationController;
import com.revolut.rate.common.util.FragmentManagerUtil;

public class BaseFragment extends Fragment implements NavigationController {

    protected BaseFragment currentFragment;

    public String getTagName() {
        return FragmentManagerUtil.getTagName(getClass());
    }

    @Override
    public void updateCurrentFragment() {
        currentFragment = (BaseFragment) getChildFragmentManager()
            .findFragmentById(R.id.content);
    }

    @Override
    public void setCurrentFragment(BaseFragment baseFragment) {
        currentFragment = baseFragment;
    }

    @Override
    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    @Override
    public FragmentManager getNavigationManager() {
        return getChildFragmentManager();
    }

    public boolean onBackPressed() {
        if (currentFragment == null) {
            return false;
        }

        boolean childHandled = currentFragment.onBackPressed();
        updateCurrentFragment();
        return childHandled;
    }
}
