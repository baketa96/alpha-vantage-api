package com.example.alphavantageapi.aggregation.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LatestInfoModel {

    @JsonProperty("01. symbol")
    private String symbol;

    @JsonProperty("02. open")
    private BigDecimal open;

    @JsonProperty("03. high")
    private BigDecimal high;

    @JsonProperty("04. low")
    private BigDecimal low;

    @JsonProperty("05. price")
    private BigDecimal price;

    @JsonProperty("06. volume")
    private Long volume;

    @JsonProperty("07. latest trading day")
    private LocalDate latestTradingDate;

    @JsonProperty("08. previous close")
    private BigDecimal previousClose;

    @JsonProperty("09. change")
    private BigDecimal change;

    @JsonProperty("10. change percent")
    private String changePercent;

    @Override
    public String toString() {
        return "StockData{"
                + "symbol='"
                + symbol
                + '\''
                + ", open="
                + open
                + ", high="
                + high
                + ", low="
                + low
                + ", price="
                + price
                + ", volume="
                + volume
                + ", latestTradingDate="
                + latestTradingDate
                + ", previousClose="
                + previousClose
                + ", change="
                + change
                + ", changePercent='"
                + changePercent
                + '\''
                + '}';
    }
}
