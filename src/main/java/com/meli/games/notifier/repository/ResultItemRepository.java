package com.meli.games.notifier.repository;

import com.meli.games.notifier.entity.ResultItem;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultItemRepository extends CrudRepository<ResultItem, String> {

	@Query(value="DELETE FROM result_item WHERE id IN (SELECT id FROM result_item ORDER BY timestamp LIMIT 7000)", nativeQuery = true)
	void cleanOldRecords();

}

