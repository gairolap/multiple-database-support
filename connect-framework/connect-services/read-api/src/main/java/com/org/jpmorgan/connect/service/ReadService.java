/**
 * Service class for READ operation.
 */
package com.org.jpmorgan.connect.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.org.jpmorgan.connect.model.response.Response;
import com.org.jpmorgan.connect.repo.DefaultRepository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Service
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReadService {

	final Map<String, DefaultRepository> repositoriesMap;

	@Autowired
	public ReadService(List<DefaultRepository> repositories) {

		repositoriesMap = repositories.stream()
				.collect(Collectors.toMap(DefaultRepository::getRepo, Function.identity()));
	}

	@Value("#{${consumers-daos-mapping}}")
	Map<String, String> consumersToDaosMap;

	/**
	 * Get all records for the given table.
	 * 
	 * @param {@linkplain String}.
	 * @param {@linkplain String}.
	 * @return {@linkplain Response}.
	 */
	public Response<?> getRecords(String consumer, String tableName) {

		Object resultset = getRepo(consumer).process(tableName, "READ");

		if (ObjectUtils.isEmpty(resultset)) {

			throw new EmptyResultDataAccessException(0);
		}

		return new Response<>(resultset, "Data found for given table!", String.valueOf(HttpStatus.FOUND.value()));
	}

	/**
	 * Gets the repository for the given consumer.
	 * 
	 * @param {@linkplain String}.
	 * @return {@linkplain DefaultRepository}.
	 */
	private DefaultRepository getRepo(String consumer) {

		DefaultRepository repo = repositoriesMap.get(consumersToDaosMap.get(consumer));

		if (ObjectUtils.isEmpty(repo)) {

			throw new IllegalArgumentException();
		}

		return repo;
	}

}