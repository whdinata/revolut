package com.revolut.rate.api.converter;

import com.revolut.rate.api.model.CurrencyApiModel;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RateListConverterTest {

    private RateListConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new RateListConverter();
    }

    @Test
    public void convert() {
        //given
        Map<String, Double> map = new HashMap<>();
        map.put("EUR", 1.0);
        map.put("IDR", 1.76);
        map.put("GBP", 3.87);

        List<CurrencyApiModel> expected = new ArrayList<>();
        CurrencyApiModel apiModel = new CurrencyApiModel();
        apiModel.setCurrencyCode("EUR");
        apiModel.setValue(1.0);

        CurrencyApiModel apiModel2 = new CurrencyApiModel();
        apiModel2.setCurrencyCode("IDR");
        apiModel2.setValue(1.76);

        CurrencyApiModel apiModel3 = new CurrencyApiModel();
        apiModel3.setCurrencyCode("GBP");
        apiModel3.setValue(3.87);

        expected.add(apiModel);
        expected.add(apiModel2);
        expected.add(apiModel3);

        //when
        List<CurrencyApiModel> actual = converter.convert(map);

        //then
        Assert.assertEquals(expected.size(), actual.size());
    }
}