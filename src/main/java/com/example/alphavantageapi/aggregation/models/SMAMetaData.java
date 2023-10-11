package com.example.alphavantageapi.aggregation.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class SMAMetaData {

    @JsonProperty("1: Symbol")
    private String symbol;
    @JsonProperty("2: Indicator")
    private String indicator;
    @JsonProperty("3: Last Refreshed")
    private String lastRefreshed;
    @JsonProperty("4: Interval")
    private String interval;
    @JsonProperty("5: Time Period")
    private int timePeriod;
    @JsonProperty("6: Series Type")
    private String seriesType;
    @JsonProperty("7: Time Zone")
    private String timeZone;
}
