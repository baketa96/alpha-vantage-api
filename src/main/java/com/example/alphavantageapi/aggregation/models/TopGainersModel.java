package com.example.alphavantageapi.aggregation.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TopGainersModel {

    private String ticker;
    private BigDecimal price;

    @JsonProperty("change_amount")
    private BigDecimal changeAmount;

    @JsonProperty("change_percentage")
    private String changePercentage;

    private Long volume;

    @Override
    public String toString() {
        return "Ticker: "
                + ticker
                + "\n"
                + "Price: "
                + price
                + "\n"
                + "Change Amount: "
                + changeAmount
                + "\n"
                + "Change Percentage: "
                + changePercentage
                + "\n"
                + "Volume: "
                + volume;
    }
}
