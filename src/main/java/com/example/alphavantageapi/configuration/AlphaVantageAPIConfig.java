package com.example.alphavantageapi.configuration;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@ConfigurationProperties(prefix = "api")
@Getter
@Setter
public class AlphaVantageAPIConfig {

    private String host;
    private List<EndpointConfig> endpoints;

    public Optional<String> getEndpointByName(String name) {
        return Optional.of(
                host
                        + endpoints.stream()
                                .filter(endpoint -> endpoint.getName().equals(name))
                                .findFirst()
                                .get()
                                .getPath());
    }
}
