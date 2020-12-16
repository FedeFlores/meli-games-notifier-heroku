package com.meli.games.notifier.service;

public interface NotifierService {

    void notifyNewListings();

	void cleanDbIfFull();

}
