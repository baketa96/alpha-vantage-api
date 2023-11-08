package com.example.alphavantageapi.aggregation;

import com.example.alphavantageapi.aggregation.models.*;
import com.example.alphavantageapi.configuration.AlphaVantageAPIConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public TopGainersWrapper getTopGainersLosersTraded() {
        String apiUrl =
                String.format(alphaVantageAPIConfig.getEndpointByName("top-gainers").get(), apiKey);

        try {
            return restTemplate.getForObject(apiUrl, TopGainersWrapper.class);
        } catch (Exception e) {
            throw new AlphaVantageException(
                    "Error while fetching top gainers " + e.getMessage(), e.getCause());
        }
    }

    public void getSMADataForKeyword(String keyword) {

        String apiUrl =
                String.format(
                        alphaVantageAPIConfig.getEndpointByName("sma-analysis").get(),
                        keyword,
                        apiKey);
        try {
            String json = restTemplate.getForObject(apiUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            SMACombinedData combinedData = objectMapper.readValue(json, SMACombinedData.class);

            TechIndicatorMetaData metaData = combinedData.getMetaData();
            System.out.println(metaData);

            System.out.println(
                    combinedData
                            .getTechnicalAnalysis()
                            .getTechnicalAnalysisMap()
                            .get("2020-08-07"));
        } catch (Exception e) {
            throw new AlphaVantageException(
                    "Error while fetching SMA Data for " + keyword + e.getMessage(), e.getCause());
        }
    }

    public void getEMADataForKeyword(String keyword) {

        String apiUrl =
                String.format(
                        alphaVantageAPIConfig.getEndpointByName("ema-analysis").get(),
                        keyword,
                        apiKey);
        try {

            String json = restTemplate.getForObject(apiUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            EMACombinedData combinedData = objectMapper.readValue(json, EMACombinedData.class);

            TechIndicatorMetaData metaData = combinedData.getMetaData();
            System.out.println(metaData);

            System.out.println(
                    combinedData
                            .getTechnicalAnalysis()
                            .getTechnicalAnalysisMap()
                            .get("2021-07-30"));
        } catch (Exception e) {
            throw new AlphaVantageException(
                    "Error while fetching SMA Data for " + keyword + e.getMessage(), e.getCause());
        }
    }
}
