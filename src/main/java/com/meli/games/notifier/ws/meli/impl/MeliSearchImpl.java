package com.meli.games.notifier.ws.meli.impl;

import com.meli.games.notifier.ws.meli.MeliSearch;
import com.meli.games.notifier.ws.meli.model.MeliSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class MeliSearchImpl implements MeliSearch {

    private static final Logger logger = LoggerFactory.getLogger(MeliSearchImpl.class);

    @Override
    public MeliSearchResponse meliSearchByKeyword(String keyword, Integer offset, String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity httpEntity = new HttpEntity(headers);
        try {
//            return restTemplate.getForObject(getUrl(keyword, offset.toString()), MeliSearchResponse.class);
            return restTemplate.exchange(getUrl(keyword, offset.toString()), HttpMethod.GET, httpEntity, MeliSearchResponse.class).getBody();
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
