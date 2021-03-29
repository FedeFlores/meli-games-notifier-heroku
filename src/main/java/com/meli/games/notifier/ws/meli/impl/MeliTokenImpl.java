package com.meli.games.notifier.ws.meli.impl;

import com.meli.games.notifier.ws.meli.MeliToken;
import com.meli.games.notifier.ws.meli.model.MeliTokenRequest;
import com.meli.games.notifier.ws.meli.model.MeliTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class MeliTokenImpl implements MeliToken {

    private static final Logger logger = LoggerFactory.getLogger(MeliTokenImpl.class);

    @Override
    public MeliTokenResponse getToken(MeliTokenRequest req) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.postForObject("https://api.mercadolibre.com/oauth/token", req, MeliTokenResponse.class);
        } catch (RestClientException ex){
            logger.error("Error getting token: " + ex.getMessage());
            return null;
        }
    }
}
