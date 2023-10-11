package com.example.alphavantageapi.aggregation.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TopGainersModel {

    private String ticker;
    private BigDecimal price;

    @JsonProperty("change_amount")
    private BigDecimal changeAmount;

    @JsonProperty("change_percentage")
    private String changePercentage;

    private Long volume;
}
