package com.meli.games.notifier.ws.meli.impl;

import com.meli.games.notifier.ws.meli.MeliSearch;
import com.meli.games.notifier.ws.meli.model.MeliSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class MeliSearchImpl implements MeliSearch {

    private static final Logger logger = LoggerFactory.getLogger(MeliSearchImpl.class);

    @Override
    public MeliSearchResponse meliSearchByKeyword(String keyword, Integer offset) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(getUrl(keyword, offset.toString()), MeliSearchResponse.class);
        } catch (RestClientException ex){
            logger.error("Error searching on mercado libre: " + ex.getMessage());
            return null;
        }
    }

    private String getUrl(String keyword, String offset) {
        // &category=MLA373840 -> videojuegos
        // &category=MLA1144   -> consolas y videojuegos
        StringBuilder url = new StringBuilder("https://api.mercadolibre.com/sites/MLA/search?q=")
                .append(keyword).append("&since=today").append("&category=MLA1144")
                .append("&offset=").append(offset);
//        if ("ps4".equalsIgnoreCase(keyword) || "ps5".equalsIgnoreCase(keyword)) {
//            // &format=2431740 -> formato fisico, solo lista categoria videojuegos MLA373840
//            url.append("&format=2431740");
//        }
        return url.toString();
    }
}
