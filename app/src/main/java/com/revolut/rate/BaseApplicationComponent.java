package com.revolut.rate;

import android.content.Context;

import com.revolut.rate.common.BaseActivity;
import com.revolut.rate.common.threading.Schedulers;

interface BaseApplicationComponent {

    void inject(BaseActivity activity);

    Context getContext();

    Schedulers getSchedulers();
}
