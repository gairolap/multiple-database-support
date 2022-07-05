/**
 * Repository to perform CRUD over MySQL database.
 */
package com.org.jpmorgan.connect.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.org.jpmorgan.connect.model.mysql.UMTablesData;

@Repository
public interface MySQLRepo extends CrudRepository<UMTablesData, String> {

	@Query(value = "SELECT * FROM UM_TABLES_DATA WHERE TBL_NM = ?1", nativeQuery = true)
	public List<UMTablesData> findByTblNm(final String tblNm);

}