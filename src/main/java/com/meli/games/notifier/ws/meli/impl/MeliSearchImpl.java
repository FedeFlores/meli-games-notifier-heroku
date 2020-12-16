package com.meli.games.notifier.ws.meli.impl;

import com.meli.games.notifier.entity.CategoriesEnum;
import com.meli.games.notifier.ws.meli.MeliSearch;
import com.meli.games.notifier.ws.meli.model.MeliSearchResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class MeliSearchImpl implements MeliSearch {

    private static final Logger logger = LoggerFactory.getLogger(MeliSearchImpl.class);

    @Override
    public MeliSearchResponse meliSearchByKeyword(String keyword, Integer offset, CategoriesEnum categoria) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(getUrl(keyword, offset.toString(), categoria), MeliSearchResponse.class);
        } catch (RestClientException ex){
            logger.error("Error searching on mercado libre: " + ex.getMessage());
            return null;
        }
    }

    private String getUrl(String keyword, String offset, CategoriesEnum categoria) {
        // &category=MLA373840 -> videojuegos
        // &category=MLA1144   -> consolas y videojuegos
        StringBuilder url = new StringBuilder("https://api.mercadolibre.com/sites/MLA/search?q=")
                .append(keyword).append("&since=today").append("&offset=").append(offset);
        if(StringUtils.isNotBlank(categoria.getUrlCategoria())) {
        	url.append("&category=").append(categoria.getUrlCategoria());
        }
        return url.toString();
    }
}
