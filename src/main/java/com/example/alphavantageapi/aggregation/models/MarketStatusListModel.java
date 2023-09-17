package com.example.alphavantageapi.aggregation.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MarketStatusListModel {

    private String endpoint;
    private List<MarketModel> markets;
}
