package com.meli.games.notifier.service.impl;

import com.meli.games.notifier.service.MeliTokenService;
import com.meli.games.notifier.ws.meli.MeliToken;
import com.meli.games.notifier.ws.meli.model.MeliTokenRequest;
import com.meli.games.notifier.ws.meli.model.MeliTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MeliTokenServiceImpl implements MeliTokenService {

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

    @Value("${code}")
    private String code;

    @Value("${redirectUri}")
    private String redirectUri;

    @Autowired
    private MeliToken meliToken;

    @Override
    public MeliTokenResponse getToken(String refreshToken) {
        MeliTokenRequest request = getMeliTokenRequest(refreshToken);
        MeliTokenResponse response = meliToken.getToken(request);
        return response;
    }

    private MeliTokenRequest getMeliTokenRequest(String refreshToken) {
        MeliTokenRequest request = new MeliTokenRequest();
        request.setClient_id(clientId);
        request.setClient_secret(clientSecret);
        if (refreshToken == null){
            request.setGrant_type("authorization_code");
            request.setCode(code);
            request.setRedirect_uri(redirectUri);
        } else {
            request.setGrant_type("refresh_token");
            request.setRefresh_token(refreshToken);
        }
        return request;
    }

}
