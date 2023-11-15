package com.example.alphavantageapi.telegrambot;

import com.example.alphavantageapi.aggregation.AlphaVantageFetcherService;
import com.example.alphavantageapi.aggregation.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Service
public class AlphaVantageBotService {

    @Autowired private AlphaVantageFetcherService alphaVantageFetcherService;

    public SendMessage handleStartCommand(long chatId) {
        String response = "Welcome to Alpha Vantage API Bot! New stuff is coming soon!";
        return new SendMessage(String.valueOf(chatId), response);
    }

    public SendMessage handleHelpCommand(long chatId) {
        String response =
                "This bot will help you get financial data from Alpha Vantage API. \n "
                        + BotCommandsEnum.getAllCommands()
                        + "\n"
                        + "It is still in development, new stuff will come soon";
        return new SendMessage(String.valueOf(chatId), response);
    }

    public SendMessage handleUnknownCommand(long chatId) {
        String response =
                "Unknown command, use \\help command to check what this bot can do for you";
        return new SendMessage(String.valueOf(chatId), response);
    }

    public SendMessage handleMostActive(long chatId) {
        TopGainersWrapper topGainersWrapper =
                alphaVantageFetcherService.getTopGainersLosersTraded();
        List<TopGainersModel> topGainersModelList = topGainersWrapper.getTopGainers();
        return new SendMessage(String.valueOf(chatId), generateMessage(topGainersModelList));
    }

    private <T> String generateMessage(List<T> listData) {
        StringBuilder stringBuilder = new StringBuilder();

        for (T t : listData) {
            stringBuilder.append(t.toString());
            stringBuilder.append("\n ----------- \n");
        }
        return stringBuilder.toString();
    }

    public SendMessage handleSearchCommand(long chatId, String messageText) {
        SendMessage errorMessage =
                evaluateCommand(BotCommandsEnum.SEARCH.getCommand(), chatId, messageText);

        if (errorMessage != null) return errorMessage;

        String[] parts = messageText.split(" ", 2);

        String keyword = parts[1];
        SearchListModel searchListModel = alphaVantageFetcherService.searchEntity(keyword);
        String message = generateMessage(searchListModel.getBestMatches());
        return new SendMessage(
                String.valueOf(chatId),
                message.isEmpty() ? "Nothing was found, try again" : message);
    }

    public SendMessage handleLatestInfoCommand(long chatId, String messageText) {

        SendMessage errorMessage =
                evaluateCommand(BotCommandsEnum.LATEST_INFO.getCommand(), chatId, messageText);

        if (errorMessage != null) return errorMessage;

        String[] parts = messageText.split(" ", 2);

        String keyword = parts[1];
        LatestInfoModelWrapper latestInfoModelWrapper =
                alphaVantageFetcherService.getLatestInfo(keyword);
        String message =
                latestInfoModelWrapper.getData() != null
                        ? latestInfoModelWrapper.getData().toString()
                        : "Nothing was found for give entity";
        return new SendMessage(String.valueOf(chatId), message);
    }

    public SendMessage evaluateCommand(String command, Long chatId, String text) {
        if (!text.startsWith(command)) {
            return handleUnknownCommand(chatId);
        }

        String[] parts = text.split(" ", 2);
        if (parts.length < 2) {
            return new SendMessage(
                    String.valueOf(chatId),
                    "Please provide a keyword. Type " + command + " followed by the keyword.");
        }
        return null;
    }

    public SendMessage handleMarketsStatusCommand(long chatId) {
        MarketStatusListModel topGainersWrapper = alphaVantageFetcherService.getMarkets();
        List<MarketModel> marketList = topGainersWrapper.getMarkets();
        return new SendMessage(String.valueOf(chatId), generateMessage(marketList));
    }
}
