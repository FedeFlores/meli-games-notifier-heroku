package com.meli.games.notifier.repository;

import com.meli.games.notifier.entity.ResultItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultItemRepository extends CrudRepository<ResultItem, String> {

}

