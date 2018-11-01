package com.revolut.rate.common;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.revolut.rate.RateApplication;
import com.revolut.rate.common.navigation.NavigationController;

import org.androidannotations.annotations.EActivity;

@EActivity
public abstract class BaseActivity extends AppCompatActivity implements NavigationController {

    private BaseFragment currentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RateApplication.getInstance().getApplicationComponent().inject(this);
    }

    @Override
    public void updateCurrentFragment() {
        currentFragment = (BaseFragment) getSupportFragmentManager()
                .findFragmentById(getContainerViewResourceId());
    }

    @Override
    public void setCurrentFragment(BaseFragment currentFragment) {
        this.currentFragment = currentFragment;
    }

    @Override
    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    @Override
    public FragmentManager getNavigationManager() {
        return getSupportFragmentManager();
    }

    @Override
    public void onBackPressed() {
        if (currentFragment == null || (!currentFragment.onBackPressed()
                && !currentFragment.getChildFragmentManager().popBackStackImmediate())) {
            super.onBackPressed();
            updateCurrentFragment();
        }
    }

    @IdRes
    protected abstract int getContainerViewResourceId();
}
