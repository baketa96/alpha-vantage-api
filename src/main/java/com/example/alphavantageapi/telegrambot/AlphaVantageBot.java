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
        Message message = update.getMessage();
        if (message.isCommand()) checkCommands(message);
        if (screaming) scream(message.getFrom().getId(), message);
        copyMessage(message.getFrom().getId(), message.getMessageId());
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
