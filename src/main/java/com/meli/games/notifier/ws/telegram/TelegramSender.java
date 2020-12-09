package com.meli.games.notifier.ws.telegram;

import org.springframework.web.client.RestClientException;

public interface TelegramSender {
    void sendMessage(String msg) throws RestClientException;
}
