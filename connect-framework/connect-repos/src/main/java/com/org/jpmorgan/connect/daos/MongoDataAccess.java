/**
 * MongoDB DAO for performing CRUDs. 
 */
package com.org.jpmorgan.connect.daos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.org.jpmorgan.connect.model.mongo.UMTablesData;
import com.org.jpmorgan.connect.repo.DefaultRepository;
import com.org.jpmorgan.connect.repos.MongoRepo;
import com.org.jpmorgan.connect.util.UtilityMethods;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@EnableMongoRepositories(basePackages = { "com.org.jpmorgan.connect.repos" })
@ConditionalOnProperty(name = "mongodb", havingValue = "enabled")
@Service("MongoDao")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MongoDataAccess implements DefaultRepository {

	@Autowired
	MongoRepo mongoRepo;

	@Override
	public Object process(Object request, String operation) {

		switch (operation) {

		case "READ":
			return findAll(request);

		case "SAVE":
			return save(request);

		case "DELETE":
			return delete(request);

		default:
			return "Unsupported operation requested";
		}

	}

	@Override
	public String getRepo() {

		return "MongoDao";
	}

	/**
	 * Retrieves data for given table.
	 * 
	 * @param {@linkplain Object}.
	 * @return {@linkplain Object}.
	 */
	private Object findAll(Object tblNm) {

		return mongoRepo.findByTblNm(String.valueOf(tblNm));
	}

	/**
	 * Saves data for given table(s).
	 * 
	 * @param {@linkplain Object}.
	 * @return {@linkplain Object}.
	 */
	private Object save(Object recordsToSave) {

		return (List<?>) mongoRepo.saveAll(getListOfEntity(Arrays.asList(recordsToSave)));
	}

	/**
	 * Deletes data for given row-id(s).
	 * 
	 * @param {@linkplain Object}.
	 * @return {@linkplain Object}.
	 */
	private Object delete(Object recordsToDelete) {

		mongoRepo.deleteAll(getListOfEntity(Arrays.asList(recordsToDelete)));

		return "Processing successful!";
	}

	/**
	 * Gets you a list of entity from list of objects.
	 * 
	 * @param {@linkplain List<Object>}.
	 * @return {@linkplain List<UMTablesData>}.
	 */
	public List<UMTablesData> getListOfEntity(Object recordsToSave) {

		List<UMTablesData> records = new ArrayList<UMTablesData>();
		UMTablesData umRecord = null;

		List<?> listOfRecs = UtilityMethods.convertObjectToList(recordsToSave);

		for (Object record : listOfRecs) {

			umRecord = new UMTablesData();

			Map<String, Object> recordMap = UtilityMethods.convertObjToTypeRef(record,
					new TypeReference<Map<String, Object>>() {
					});

			if (ObjectUtils.isEmpty(recordMap.get("rowId"))) {
				umRecord.setRowId(String.valueOf(UUID.randomUUID()));
			} else {
				umRecord.setRowId(String.valueOf(recordMap.get("rowId")));
			}
			umRecord.setRecord(String.valueOf(recordMap.get("record")));
			umRecord.setRecordTyp(String.valueOf(recordMap.get("recordTyp")));
			umRecord.setTblNm(String.valueOf(recordMap.get("tblNm")));

			records.add(umRecord);
		}

		return records;
	}

}