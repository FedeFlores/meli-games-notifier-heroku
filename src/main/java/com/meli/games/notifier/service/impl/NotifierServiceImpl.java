package com.meli.games.notifier.service.impl;

import com.meli.games.notifier.entity.ResultItem;
import com.meli.games.notifier.repository.ResultItemRepository;
import com.meli.games.notifier.service.NotifierService;
import com.meli.games.notifier.ws.meli.MeliSearch;
import com.meli.games.notifier.ws.meli.model.MeliSearchResponse;
import com.meli.games.notifier.ws.meli.model.Result;
import com.meli.games.notifier.ws.telegram.TelegramSender;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotifierServiceImpl implements NotifierService {

    private static final Logger logger = LoggerFactory.getLogger(NotifierServiceImpl.class);

    @Autowired
    private ResultItemRepository itemRepository;

    @Autowired
    private MeliSearch meliSearch;

    @Autowired
    private TelegramSender telegramSender;

    @Value("#{'${keywords}'.split(',')}")
    private List<String> keywords;

    @Value("#{'${blacklist}'.split(',')}")
    private List<String> blacklist;

    @Override
    public void notifyNewListings() {
        logger.info("Getting items with keywords: {}", keywords.toString());
        List<ResultItem> items = getListings(keywords);
        logger.info("search returned {} items", items.size());
        logger.info("filtering with blacklist: {}", blacklist.toString());
        items = items.stream().filter(item -> blacklist.stream().noneMatch(word -> StringUtils.containsIgnoreCase(item.getTitle(), word))).collect(Collectors.toList());
        logger.info("{} items after blacklisting", items.size());
        logger.info("removing duplicates and filtering new items");
        items = items.stream().distinct().filter(item -> !itemRepository.existsById(item.getId())).collect(Collectors.toList());
        if (!items.isEmpty()){
            logger.info("{} NEW ITEMS!!! Sending telegram notifications", items.size());
            sendTelegramNotifications(items);
            items = items.stream().filter(item -> !item.getError()).collect(Collectors.toList());
            logger.info("{} notifications sent", items.size());
            itemRepository.saveAll(items);
        } else {
            logger.info("NO NEW ITEMS :(");
        }
    }

    private List<ResultItem> getListings(List<String> keywords) {
        List<ResultItem> items = new ArrayList<>();
        for (String keyword : keywords) {
            List<ResultItem> results = searchByKeyword(keyword);
            items.addAll(results);
        }
        return items;
    }

    private List<ResultItem> searchByKeyword(String keyword) {
        Integer offset = 0;
        MeliSearchResponse response = meliSearch.meliSearchByKeyword(keyword, offset);
        if (response == null) {
            return new ArrayList<>();
        }
        Integer total = response.getPaging().getTotal();
        offset = offset + 50;
        List<ResultItem> items = responseToItems(response);
        while (offset < total) {
            response = meliSearch.meliSearchByKeyword(keyword, offset);
            if (response == null) {
                break;
            }
            items.addAll(responseToItems(response));
            offset = offset + 50;
        }
        return items;
    }

    private List<ResultItem> responseToItems(MeliSearchResponse response) {
        List<ResultItem> items = new ArrayList<>();
        for (Result result : response.getResults()) {
            ResultItem item = new ResultItem();
            item.setId(result.getId());
            item.setLink(result.getPermalink());
            item.setTitle(result.getTitle());
            item.setPrice(result.getPrice());
            items.add(item);
        }
        return items;
    }

    private void sendTelegramNotifications(List<ResultItem> newItems) {
        for (ResultItem item : newItems) {
            StringBuilder sb = new StringBuilder(item.getTitle())
                    .append("\n").append("$").append(String.valueOf(item.getPrice())).append("\n")
                    .append(item.getLink());
            try {
                telegramSender.sendMessage(sb.toString());
                item.setError(false);
            } catch (RestClientException ex) {
                logger.error("Error sending telegram, message is: {}, exception: {}", sb.toString(), ex.getMessage());
                item.setError(true);
            }
        }
    }

}
