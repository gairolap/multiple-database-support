/**
 * Repository to perform CRUD over MongoDB.
 */
package com.org.jpmorgan.connect.repos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.org.jpmorgan.connect.model.mongo.UMTablesData;

@Repository
public interface MongoRepo extends MongoRepository<UMTablesData, String> {

	@Query(" { 'tblNm' : ?0 } ")
	public List<UMTablesData> findByTblNm(final String tblNm);
}