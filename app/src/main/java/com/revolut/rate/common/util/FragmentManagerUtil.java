package com.revolut.rate.common.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import org.androidannotations.api.view.HasViews;

public class FragmentManagerUtil {

    private FragmentManagerUtil() {}

    public static <T extends Fragment> T findFragment(final FragmentManager fragmentmanager,
                                                      final String tag) {
        return (T) fragmentmanager.findFragmentByTag(tag);
    }

    public static <T extends Fragment> T findFragment(final Context context, final String tag) {
        FragmentActivity fragmentActivity = (FragmentActivity) context;
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();

        return findFragment(fragmentManager, tag);
    }

    public static String getTagName(final Class<?> fragmentClass) {
        if(HasViews.class.isAssignableFrom(fragmentClass)) {
            return fragmentClass.getSuperclass().getName();
        }

        return fragmentClass.getName();
    }
}
