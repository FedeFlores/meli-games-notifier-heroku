package com.meli.games.notifier.service;

import com.meli.games.notifier.ws.meli.model.MeliTokenResponse;

public interface MeliTokenService {

    MeliTokenResponse getToken(String refreshToken);

}
