package com.meli.games.notifier.ws.meli;

import com.meli.games.notifier.ws.meli.model.MeliSearchResponse;

public interface MeliSearch {

    MeliSearchResponse meliSearchByKeyword(String keyword, Integer offset);

}
