package com.example.alphavantageapi.aggregation.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TopGainersWrapper {

    private String metadata;

    @JsonProperty("last_updated")
    private String lastUpdated;

    @JsonProperty("top_gainers")
    private List<TopGainersModel> topGainers;
}
