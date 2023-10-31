package com.example.alphavantageapi.aggregation.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EMAData {

    @JsonProperty("EMA")
    private String ema;

}
