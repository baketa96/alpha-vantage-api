package com.example.alphavantageapi.aggregation;

import com.example.alphavantageapi.aggregation.models.LatestInfoModelWrapper;
import com.example.alphavantageapi.aggregation.models.MarketStatusListModel;
import com.example.alphavantageapi.aggregation.models.SearchListModel;
import com.example.alphavantageapi.configuration.AlphaVantageAPIConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlphaVantageFetcherService {

    private final AlphaVantageAPIConfig alphaVantageAPIConfig;
    private final RestTemplate restTemplate;

    private final String apiKey = "N/A";

    @Autowired
    public AlphaVantageFetcherService(AlphaVantageAPIConfig alphaVantageAPIConfig) {
        this.alphaVantageAPIConfig = alphaVantageAPIConfig;
        this.restTemplate = new RestTemplate();
    }

    public MarketStatusListModel getMarkets() {
        String apiUrl =
                String.format(
                        alphaVantageAPIConfig.getEndpointByName("global-market").get(), apiKey);
        try {
            return restTemplate.getForObject(apiUrl, MarketStatusListModel.class);
        } catch (Exception e) {
            throw new AlphaVantageException(
                    "Error while fetching global market data ", e.getCause());
        }
    }

    public SearchListModel searchEntity(String keyword) {
        String apiUrl =
                String.format(
                        alphaVantageAPIConfig.getEndpointByName("search").get(), keyword, apiKey);
        try {
            return restTemplate.getForObject(apiUrl, SearchListModel.class);
        } catch (Exception e) {
            throw new AlphaVantageException(
                    "Error while searching entity " + keyword, e.getCause());
        }
    }

    public LatestInfoModelWrapper getLatestInfo(String keyword) {
        String apiUrl =
                String.format(
                        alphaVantageAPIConfig.getEndpointByName("global-quote").get(),
                        keyword,
                        apiKey);
        try {
            return restTemplate.getForObject(apiUrl, LatestInfoModelWrapper.class);
        } catch (Exception e) {
            throw new AlphaVantageException(
                    "Error while searching entity " + keyword, e.getCause());
        }
    }
}