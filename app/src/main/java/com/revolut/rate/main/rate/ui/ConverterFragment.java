package com.revolut.rate.main.rate.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.revolut.rate.R;
import com.revolut.rate.main.rate.RateController;
import com.revolut.rate.main.rate.model.Currency;
import com.revolut.rate.main.rate.ui.adapter.CurrencyListAdapter;
import com.revolut.rate.main.rate.viewmodel.CurrencyListViewModel;
import com.revolut.rate.main.rate.viewmodel.CurrencyListViewModelFactory;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

@EFragment(R.layout.fragment_converter)
public class ConverterFragment extends BaseRateFragment {

    @Inject
    RateController controller;

    @Inject
    CurrencyListViewModelFactory viewModelFactory;

    @ViewById
    RecyclerView currencyList;

    @ViewById
    ProgressBar progressBar;

    private CurrencyListAdapter adapter;
    private CurrencyListViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CurrencyListViewModel.class);
    }

    @Override
    public void onStop() {
        if (viewModel != null) {
            viewModel.unsubscribe();
        }

        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel
            .setBaseCurrency(getInitialCurrency())
            .setOnErrorListener(error -> Toast.makeText(getContext(), R.string.error,
                    Toast.LENGTH_SHORT).show())
            .getCurrencyList()
            .observe(this, currencies -> {
                adapter.setData(currencies);
                progressBar.setVisibility(View.GONE);
            });
    }

    @AfterViews
    void afterViews() {
        adapter = createAdapter();

        currencyList.setAdapter(adapter);
        currencyList.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel
            .getCurrencyNameList()
            .observe(this, adapter::setMap);
    }

    private Currency getInitialCurrency() {
        Currency currency = new Currency();
        currency.setValue("1");
        currency.setCurrencyAndFlagUrl("EUR");

        return currency;
    }

    private CurrencyListAdapter createAdapter() {
        final CurrencyListAdapter adapter = new CurrencyListAdapter();
        adapter.setHasStableIds(true);
        adapter.setValueChangedListener(value -> viewModel.setBaseCurrency(value));
        adapter.setOnItemClickListener(() -> {
            currencyList.scrollToPosition(0);
            viewModel.setBaseCurrency(adapter.getEditableCurrency());
        });

        return adapter;
    }

    @Override
    public boolean onBackPressed() {
        getMainActivity().finish();

        return super.onBackPressed();
    }
}
