package com.example.alphavantageapi.telegrambot;

import com.example.alphavantageapi.aggregation.AlphaVantageFetcherService;
import com.example.alphavantageapi.aggregation.models.SearchListModel;
import com.example.alphavantageapi.aggregation.models.TopGainersModel;
import com.example.alphavantageapi.aggregation.models.TopGainersWrapper;

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
        SendMessage sendMessage;
        if (messageText.startsWith("/search")) {
            String[] parts = messageText.split(" ", 2);
            if (parts.length < 2) {
                sendMessage =
                        new SendMessage(String.valueOf(chatId), "Please provide a search keyword.");
            } else {
                String keyword = parts[1];
                SearchListModel searchListModel = alphaVantageFetcherService.searchEntity(keyword);
                String message = generateMessage(searchListModel.getBestMatches());
                sendMessage =
                        new SendMessage(
                                String.valueOf(chatId),
                                message.isEmpty() ? "Nothing was found, try again" : message);
            }
        } else {
            sendMessage = handleUnknownCommand(chatId);
        }
        return sendMessage;
    }
}
