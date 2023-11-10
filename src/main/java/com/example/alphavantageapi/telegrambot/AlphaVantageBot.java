package com.example.alphavantageapi.telegrambot;

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

    private boolean screaming;

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
            Message message = update.getMessage();
            long chatId = message.getChatId();

            switch (message.getText()) {
                case "/start" -> handleStartCommand(chatId);
                case "/help" -> handleHelpCommand(chatId);
                default -> handleUnknownCommand(chatId);
            }
        }
    }

    private void handleStartCommand(long chatId) {
        String response = "Welcome to Alpha Vantage API Bot! New stuff is coming soon!";
        sendText(chatId, response);
    }

    private void handleHelpCommand(long chatId) {
        String response =
                "This bot will help you get financial data from Alpha Vantage API. \n "
                        + "It is still in development, new stuff will come soon";
        sendText(chatId, response);
    }

    private void handleUnknownCommand(long chatId) {
        String response =
                "Unknown command, use \\help command to check what this bot can do for you";
        sendText(chatId, response);
    }

    private void checkCommands(Message message) {
        if (message.getText().equals("/scream")) screaming = true;
        else if (message.getText().equals("/whisper")) screaming = false;
        return;
    }

    private void scream(Long id, Message message) {
        if (message.hasText()) sendText(id, message.getText().toUpperCase());
        else copyMessage(message.getFrom().getId(), message.getMessageId());
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

    public void sendText(Long id, String text) {
        SendMessage sm = SendMessage.builder().chatId(id.toString()).text(text).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
