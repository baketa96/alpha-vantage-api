package com.example.alphavantageapi;

import com.example.alphavantageapi.configuration.AlphaVantageAPIConfig;
import com.example.alphavantageapi.telegrambot.AlphaVantageBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@EnableConfigurationProperties({AlphaVantageAPIConfig.class})
public class AlphaVantageApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(AlphaVantageApiApplication.class, args);
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(ctx.getBean("alphaVantageBot", AlphaVantageBot.class));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
