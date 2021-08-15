package com.company;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        }catch (TelegramApiRequestException e){
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()){
            switch (message.getText()){
                case "USD":
                    sendMsg(message,"06.08 - " + "424.3\n" +
                            "07.08 - " + "424.3\n" +
                            "08.08 - " + "424.3\n" +
                            "09.08 - " + "426.4\n" +
                            "10.08 - " + "425.8\n" +
                            "11.08 - " + "425.5\n" +
                            "12.08 - " + "424.2\n" +
                            "13.08 - " + "425.2\n" +
                            "14.08 - " + "425.2\n" +
                            "15.08 - " + "425.2");
                    break;
                case "EUR":
                    sendMsg(message,"06.08 - " + "501.1\n" +
                            "07.08 - " + "501.1\n" +
                            "08.08 - " + "501.1\n" +
                            "09.08 - " + "501.4\n" +
                            "10.08 - " + "499.2\n" +
                            "11.08 - " + "498.2\n" +
                            "12.08 - " + "498.1\n" +
                            "13.08 - " + "499.5\n" +
                            "14.08 - " + "499.5\n" +
                            "15.08 - " + "499.5");
                    break;
                case "RUB":
                    sendMsg(message,"06.08 - " + "5.8\n" +
                            "07.08 - " + "5.8\n" +
                            "08.08 - " + "5.8\n" +
                            "09.08 - " + "5.8\n" +
                            "10.08 - " + "5.8\n" +
                            "11.08 - " + "5.8\n" +
                            "12.08 - " + "5.8\n" +
                            "13.08 - " + "5.8\n" +
                            "14.08 - " + "5.8\n" +
                            "15.08 - " + "5.8");
                    break;
                default:
                    sendMsg(message,"Hello! This is test case of Aigerim for Freedom Finance!");
            }
        }
    }

    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("USD"));
        keyboardFirstRow.add(new KeyboardButton("EUR"));
        keyboardFirstRow.add(new KeyboardButton("RUB"));

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    @Override
    public String getBotUsername() {
        return "ffCurrencyBot";
    }

    @Override
    public String getBotToken() {
        return "1973690851:AAEh1GSkubyWpBGgTSFmUoErcPjSJWNNmHY";
    }
}
