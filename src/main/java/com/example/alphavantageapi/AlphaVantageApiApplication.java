package com.example.alphavantageapi;

import com.example.alphavantageapi.configuration.AlphaVantageAPIConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({AlphaVantageAPIConfig.class})
public class AlphaVantageApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlphaVantageApiApplication.class, args);
    }
}
