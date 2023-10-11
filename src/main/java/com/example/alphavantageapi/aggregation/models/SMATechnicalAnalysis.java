package com.example.alphavantageapi.aggregation.models;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class SMATechnicalAnalysis {

    private Map<String, BigDecimal> technicalAnalysisMap;

    @JsonAnySetter
    public void setTechnicalAnalysis(String date, SMAData data) {
        if (technicalAnalysisMap == null) technicalAnalysisMap = new HashMap<>();
        // Jackson will call this method for any extra fields
        // in the "Technical Analysis" object and add them to the map.
        technicalAnalysisMap.put(date, new BigDecimal(data.getSma()));
    }
}
