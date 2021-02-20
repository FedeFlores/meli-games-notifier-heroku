package com.meli.games.notifier.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.meli.games.notifier.entity.CategoriesEnum;
import com.meli.games.notifier.entity.ResultItem;
import com.meli.games.notifier.repository.ResultItemRepository;
import com.meli.games.notifier.service.NotifierService;
import com.meli.games.notifier.ws.meli.MeliSearch;
import com.meli.games.notifier.ws.meli.model.MeliSearchResponse;
import com.meli.games.notifier.ws.meli.model.Result;
import com.meli.games.notifier.ws.telegram.TelegramSender;

@Component
public class NotifierServiceImpl implements NotifierService {

    private static final Logger logger = LoggerFactory.getLogger(NotifierServiceImpl.class);

    @Autowired
    private ResultItemRepository itemRepository;

    @Autowired
    private MeliSearch meliSearch;

    @Autowired
    private TelegramSender telegramSender;

    @Value("#{'${consVideoKeywords}'.split(',')}")
    private List<String> consVideoKeywords;
    
    @Value("#{'${sinCatKeywords}'.split(',')}")
    private List<String> sinCatKeywords;

    @Value("#{'${blacklist}'.split(',')}")
    private List<String> blacklist;

    @Override
    public void notifyNewListings() {
        logger.info("Getting items with keywords: {}", consVideoKeywords.toString(), sinCatKeywords.toString());
        List<ResultItem> items = getListings();
        logger.info("search returned {} items", items.size());
        logger.info("filtering with blacklist: {}", blacklist.toString());
        items = items.stream().filter(item -> blacklist.stream().noneMatch(word -> StringUtils.containsIgnoreCase(item.getTitle(), word))).collect(Collectors.toList());
        logger.info("{} items after blacklisting", items.size());
        logger.info("removing duplicates and filtering new items");
        items = items.stream().distinct().filter(item -> !itemRepository.existsById(item.getId())).collect(Collectors.toList());
        if (!items.isEmpty()){
            logger.info("{} NEW ITEMS! Sending telegram notifications", items.size());
            sendTelegramNotifications(items);
            items = items.stream().filter(item -> !item.getError()).collect(Collectors.toList());
            logger.info("{} notifications sent", items.size());
            itemRepository.saveAll(items);
        } else {
            logger.info("NO NEW ITEMS :(");
        }
    }

    private List<ResultItem> getListings() {
        List<ResultItem> items = new ArrayList<>();
        for (String keyword : consVideoKeywords) {
            List<ResultItem> results = searchByKeyword(keyword, CategoriesEnum.CONSOLASYVIDEOJUEGOS);
            items.addAll(results);
        }
        for (String keyword : sinCatKeywords) {
            List<ResultItem> results = searchByKeyword(keyword, CategoriesEnum.SINCATEGORIA);
            items.addAll(results);
        }
        return items;
    }

    private List<ResultItem> searchByKeyword(String keyword, CategoriesEnum categoria) {
        Integer offset = 0;
        MeliSearchResponse response = meliSearch.meliSearchByKeyword(keyword, offset, categoria);
        if (response == null) {
            return new ArrayList<>();
        }
        Integer total = response.getPaging().getTotal();
        offset = offset + 50;
        List<ResultItem> items = responseToItems(response);
        while (offset < total) {
            response = meliSearch.meliSearchByKeyword(keyword, offset, categoria);
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
            item.setTimestamp(new Date());
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

	@Override
	public void cleanDbIfFull() {
        logger.info("Checking database size");
        Long dbSize = itemRepository.count();
        logger.info("Found " + dbSize + " items");
        if (dbSize > 8000L) {
        	logger.info("Cleaning Database");
        	itemRepository.cleanOldRecords();
        	logger.info("Database cleaned");
        } else {
        	logger.info("Database is healthy");
        }
	}

}
