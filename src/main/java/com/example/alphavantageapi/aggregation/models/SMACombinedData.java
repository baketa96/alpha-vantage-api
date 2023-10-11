package com.example.alphavantageapi.aggregation.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SMACombinedData {

    @JsonProperty("Meta Data")
    private SMAMetaData metaData;

    @JsonProperty("Technical Analysis: SMA")
    private SMATechnicalAnalysis technicalAnalysis;
}
