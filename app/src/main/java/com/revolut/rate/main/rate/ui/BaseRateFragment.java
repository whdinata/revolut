package com.revolut.rate.main.rate.ui;

import com.revolut.rate.R;
import com.revolut.rate.main.BaseMainFragment;
import com.revolut.rate.main.rate.RateSubComponent;

public class BaseRateFragment extends BaseMainFragment<RateSubComponent> {

    @Override
    protected int getComponentId() {
        return R.id.component_id_rate;
    }

    @Override
    public RateSubComponent createComponent() {
        return getMainActivity().getComponent().getRateSubComponent();
    }
}
