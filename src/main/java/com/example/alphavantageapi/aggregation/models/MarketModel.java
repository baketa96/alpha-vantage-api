package com.example.alphavantageapi.aggregation.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MarketModel {

    @JsonProperty("market_type")
    private String marketType;

    private String region;

    @JsonProperty("primary_exchanges")
    private String primaryExchanges;

    @JsonProperty("local_open")
    private String localOpen;

    @JsonProperty("local_closes")
    private String localClose;

    @JsonProperty("current_status")
    private String currentStatus;

    private String notes;

    @Override
    public String toString() {
        return "YourClass{"
                + "marketType='"
                + marketType
                + '\''
                + ", region='"
                + region
                + '\''
                + ", primaryExchanges='"
                + primaryExchanges
                + '\''
                + ", localOpen='"
                + localOpen
                + '\''
                + ", localClose='"
                + localClose
                + '\''
                + ", currentStatus='"
                + currentStatus
                + '\''
                + ", notes='"
                + notes
                + '\''
                + '}';
    }
}
