package com.revolut.rate.common.navigation;

import android.support.v4.app.FragmentTransaction;

import com.revolut.rate.R;
import com.revolut.rate.common.BaseFragment;

abstract class BaseNavigator {

    public boolean popBackStack() {
        boolean handled = getNavigationController().getNavigationManager().popBackStackImmediate();
        getNavigationController().updateCurrentFragment();
        return handled;
    }

    protected void moveToScreen(BaseFragment baseFragment) {
        moveToScreen(baseFragment, true);
    }

    protected void moveToScreen(BaseFragment baseFragment, boolean addToBackStack) {
        addFragmentToContainer(baseFragment, addToBackStack);
        getNavigationController().setCurrentFragment(baseFragment);
    }

    private void addFragmentToContainer(BaseFragment fragment, boolean addToBackStack) {

        final FragmentTransaction transaction = getNavigationController().getNavigationManager()
                .beginTransaction();

        transaction.replace(R.id.content, fragment, fragment.getTagName());

        if (addToBackStack) {
            transaction.addToBackStack(fragment.getTagName());
        } else {
            transaction.disallowAddToBackStack();
        }

        transaction.commit();
    }

    protected abstract NavigationController getNavigationController();
}
