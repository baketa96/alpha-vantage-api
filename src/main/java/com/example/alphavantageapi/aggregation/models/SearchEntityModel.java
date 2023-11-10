package com.example.alphavantageapi.aggregation.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchEntityModel {

    @JsonProperty("1. symbol")
    private String symbol;

    @JsonProperty("2. name")
    private String name;

    @JsonProperty("3. type")
    private String type;

    @JsonProperty("4. region")
    private String region;

    @JsonProperty("5. marketOpen")
    private String marketOpen;

    @JsonProperty("6. marketClose")
    private String marketClose;

    @JsonProperty("7. timezone")
    private String timezone;

    @JsonProperty("8. currency")
    private String currency;

    @JsonProperty("9. matchScore")
    private String matchScore;

    @Override
    public String toString() {
        return "Symbol: "
                + symbol
                + "\n"
                + "Name: "
                + name
                + "\n"
                + "Type: "
                + type
                + "\n"
                + "Region: "
                + region
                + "\n"
                + "Market Open: "
                + marketOpen
                + "\n"
                + "Market Close: "
                + marketClose
                + "\n"
                + "Timezone: "
                + timezone
                + "\n"
                + "Currency: "
                + currency;
    }
}
