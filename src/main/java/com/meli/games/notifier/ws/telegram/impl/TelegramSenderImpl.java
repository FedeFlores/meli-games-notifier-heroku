package com.meli.games.notifier.ws.telegram.impl;

import com.meli.games.notifier.ws.telegram.TelegramSender;
import com.meli.games.notifier.ws.telegram.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class TelegramSenderImpl implements TelegramSender {

    @Value("${token}")
    private String token;

    @Value("${chatId}")
    private String chatId;

    @Override
    public void sendMessage(String msg) throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.telegram.org/bot%s/sendMessage";
        url = String.format(url, token);
        SendMessageRequest request = new SendMessageRequest();
        request.setChat_id(chatId);
        request.setText(msg);
        restTemplate.postForEntity(url, request, Void.class);
    }
}
