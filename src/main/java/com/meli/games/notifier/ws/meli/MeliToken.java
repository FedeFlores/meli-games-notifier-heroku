package com.meli.games.notifier.ws.meli;

import com.meli.games.notifier.ws.meli.model.MeliTokenRequest;
import com.meli.games.notifier.ws.meli.model.MeliTokenResponse;

public interface MeliToken {

    MeliTokenResponse getToken(MeliTokenRequest req);

}
