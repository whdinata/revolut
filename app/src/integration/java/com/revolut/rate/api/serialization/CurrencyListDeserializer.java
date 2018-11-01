package com.revolut.rate.api.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.revolut.rate.api.model.CurrencyApiModel;
import com.revolut.rate.api.response.CurrencyListResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CurrencyListDeserializer extends StdDeserializer<CurrencyListResponse> {

    public CurrencyListDeserializer() {
        super(CurrencyListResponse.class);
    }

    @Override
    public CurrencyListResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();

        List<CurrencyApiModel> apiModels = new ArrayList<>();
        TreeNode treeNode = mapper.readTree(p);
        Iterator<String> iterator = treeNode.fieldNames();

        while(iterator.hasNext()) {
            String currencyCode = iterator.next();
            String currencyName = treeNode.get(currencyCode).toString();

            CurrencyApiModel apiModel = new CurrencyApiModel();
            apiModel.setCurrencyCode(currencyCode);
            apiModel.setCurrencyName(currencyName.substring(1, currencyName.length() - 1));

            apiModels.add(apiModel);
        }

        CurrencyListResponse response = new CurrencyListResponse();
        response.setCurrencies(apiModels);

        return response;
    }
}
