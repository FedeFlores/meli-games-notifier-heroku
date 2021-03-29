package com.meli.games.notifier.scheduling;

import com.meli.games.notifier.service.MeliTokenService;
import com.meli.games.notifier.service.NotifierService;
import com.meli.games.notifier.ws.meli.MeliToken;
import com.meli.games.notifier.ws.meli.model.MeliTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private NotifierService notifierService;

    @Autowired
    private MeliTokenService meliTokenService;

    private String refresh_token;
    private String token;


    @Scheduled(fixedDelayString = "${delay}", initialDelayString = "${initialDelay}")
    public void notifierScheduler() {
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
        log.info("Starting process at: {}", dateFormat.format(new Date()));
        notifierService.notifyNewListings(token);
    }

    @Scheduled(fixedDelayString = "${tokenDelay}")
    public void tokenScheduler() {
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
        log.info("Obtaining token at: {}", dateFormat.format(new Date()));
        MeliTokenResponse response = meliTokenService.getToken(refresh_token);
        refresh_token = response.getRefresh_token();
        token = response.getAccess_token();
    }

}
