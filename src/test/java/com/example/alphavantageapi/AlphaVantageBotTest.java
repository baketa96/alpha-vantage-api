package com.example.alphavantageapi;

import com.example.alphavantageapi.telegrambot.AlphaVantageBot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlphaVantageBotTest {

    @Autowired AlphaVantageBot alphaVantageBot;
    TelegramBotsApi botsApi;

    @BeforeAll
    public void setUp() throws TelegramApiException {

        botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(alphaVantageBot);
    }
}
