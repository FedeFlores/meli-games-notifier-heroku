package com.meli.games.notifier.repository;

import com.meli.games.notifier.entity.ResultItem;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultItemRepository extends CrudRepository<ResultItem, String> {

	@Query(value="DELETE FROM items WHERE ctid IN (SELECT ctid FROM items ORDER BY timestamp LIMIT 7000)", nativeQuery = true)
	void cleanOldRecords();

}

