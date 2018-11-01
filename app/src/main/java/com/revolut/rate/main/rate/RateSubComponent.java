package com.revolut.rate.main.rate;

import com.revolut.rate.main.rate.ui.ConverterFragment;

import dagger.Subcomponent;

@Rate
@Subcomponent(modules = RateModule.class)
public interface RateSubComponent {

    void inject(ConverterFragment fragment);
}
