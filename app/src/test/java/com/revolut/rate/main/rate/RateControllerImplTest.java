package com.revolut.rate.main.rate;

import com.revolut.rate.database.model.CurrencyDataModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.subscribers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RateControllerImplTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    RateRepository repository;

    private RateController controller;

    @Before
    public void setUp() throws Exception {
        controller = new RateControllerImpl(repository, null, null);
    }

    @Test
    public void getCurrencyMap() {
        //given
        List<CurrencyDataModel> dataModels = new ArrayList<>();
        CurrencyDataModel dataModel = new CurrencyDataModel();
        dataModel.setCurrencyCode("EUR");
        dataModel.setCurrencyName("Euro");

        CurrencyDataModel dataModel2 = new CurrencyDataModel();
        dataModel2.setCurrencyCode("GBP");
        dataModel2.setCurrencyName("Poundsterling");

        CurrencyDataModel dataModel3 = new CurrencyDataModel();
        dataModel3.setCurrencyCode("IDR");
        dataModel3.setCurrencyName("Rupiah");

        dataModels.add(dataModel);
        dataModels.add(dataModel2);
        dataModels.add(dataModel3);

        Map<String, String> expected = new HashMap<>();
        expected.put("EUR", "Euro");
        expected.put("GBP", "Poundsterling");
        expected.put("IDR", "Rupiah");

        //when
        when(repository.getCurrencyList()).thenReturn(Single.just(dataModels));

        //then
        controller.getCurrencyMap()
                .test()
                .assertResult(expected);
    }
}