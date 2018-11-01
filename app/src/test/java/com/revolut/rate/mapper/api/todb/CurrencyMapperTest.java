package com.revolut.rate.mapper.api.todb;

import com.revolut.rate.api.model.CurrencyApiModel;
import com.revolut.rate.database.model.CurrencyDataModel;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyMapperTest {

    private CurrencyMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new CurrencyMapper(null);
    }

    @Test
    public void givenValidApiModelThenReturnDataModelWithTheSameData() {

        //given
        CurrencyApiModel apiModel = new CurrencyApiModel();
        apiModel.setCurrencyCode("EUR");
        apiModel.setCurrencyName("Euro");

        CurrencyDataModel expectedDataModel = new CurrencyDataModel();
        expectedDataModel.setCurrencyCode("EUR");
        expectedDataModel.setCurrencyName("Euro");

        //when
        CurrencyDataModel actualDataModel = mapper.map(apiModel);

        //then
        Assert.assertEquals(expectedDataModel.getCurrencyCode(), actualDataModel.getCurrencyCode());
        Assert.assertEquals(expectedDataModel.getCurrencyName(), actualDataModel.getCurrencyName());
    }

    @Test
    public void givenNullApiModelThenReturnDataModelWithEmptyData() {
        //given
        CurrencyApiModel apiModel = null;

        CurrencyDataModel expectedDataModel = new CurrencyDataModel();
        expectedDataModel.setCurrencyCode(null);
        expectedDataModel.setCurrencyName(null);

        //when
        CurrencyDataModel actualDataModel = mapper.map(apiModel);

        //then
        Assert.assertEquals(expectedDataModel.getCurrencyCode(), actualDataModel.getCurrencyCode());
        Assert.assertEquals(expectedDataModel.getCurrencyName(), actualDataModel.getCurrencyName());
    }
}