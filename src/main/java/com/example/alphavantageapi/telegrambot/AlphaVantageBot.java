package com.example.alphavantageapi.telegrambot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class AlphaVantageBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Autowired private AlphaVantageBotService alphaVantageBotService;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage response;
            Message message = update.getMessage();
            long chatId = message.getChatId();

            String messageText = message.getText();

            if (messageText.equals(BotCommandsEnum.START.getCommand())) {
                response = alphaVantageBotService.handleStartCommand(chatId);
            } else if (messageText.equals(BotCommandsEnum.HELP.getCommand())) {
                response = alphaVantageBotService.handleHelpCommand(chatId);
            } else if (messageText.equals(BotCommandsEnum.MOST_ACTIVE.getCommand())) {
                response = alphaVantageBotService.handleMostActive(chatId);
            } else if (messageText.contains(BotCommandsEnum.SEARCH.getCommand())) {
                response = alphaVantageBotService.handleSearchCommand(chatId, messageText);

            } else if (messageText.contains(BotCommandsEnum.LATEST_INFO.getCommand())) {
                response = alphaVantageBotService.handleLatestInfoCommand(chatId, messageText);
            } else {
                response = alphaVantageBotService.handleUnknownCommand(chatId);
            }

            sendText(response);
        }
    }

    public void copyMessage(Long userId, Integer msgId) {
        CopyMessage cm =
                CopyMessage.builder()
                        .fromChatId(userId.toString())
                        .chatId(userId.toString())
                        .messageId(msgId)
                        .build();

        try {
            execute(cm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendText(SendMessage sm) {
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
