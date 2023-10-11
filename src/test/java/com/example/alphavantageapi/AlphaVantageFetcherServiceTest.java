package com.example.alphavantageapi;

import com.example.alphavantageapi.aggregation.AlphaVantageFetcherService;
import com.example.alphavantageapi.aggregation.models.MarketModel;
import com.example.alphavantageapi.aggregation.models.SearchEntityModel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AlphaVantageFetcherServiceTest {

    @Autowired private AlphaVantageFetcherService service;

    @Test
    void shouldGetMarkets() {
        for (MarketModel market : service.getMarkets().getMarkets()) {
            System.out.println(market);
        }
    }

    @Test
    void shouldReturnSearchResult() {
        for (SearchEntityModel entityModel : service.searchEntity("avanza").getBestMatches()) {
            System.out.println(entityModel);
        }
    }

    @Test
    void shouldGetLatestInfo() {
        System.out.println(service.getLatestInfo("1JJA.FRK").getData());
    }

    @Test
    void shouldGetTopGainers() {
        System.out.println(service.getTopGainersLosersTraded());
    }

    @Test
    void shouldGetSMA() {
        service.getSMADataForKeyword("IBM");
    }
}
