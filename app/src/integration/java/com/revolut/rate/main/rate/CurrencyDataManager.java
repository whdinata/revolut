package com.revolut.rate.main.rate;

import com.revolut.rate.database.DatabaseManager;
import com.revolut.rate.database.model.CurrencyDataModel;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class CurrencyDataManager {

    private DatabaseManager databaseManager;

    @Inject
    public CurrencyDataManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public List<CurrencyDataModel> getCurrencyList() {
        return databaseManager.getModelList(CurrencyDataModel.class);
    }

    public boolean saveCurrencyList(List<CurrencyDataModel> currencyDataModelList) {

        for (CurrencyDataModel model : currencyDataModelList) {
            model.save();
        }

        return true;
    }
}
