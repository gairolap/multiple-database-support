/**
 * Service class for SAVE operation.
 */
package com.org.jpmorgan.connect.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class SaveService {

	final Map<String, DefaultRepository> repositoriesMap;

	@Autowired
	public SaveService(List<DefaultRepository> repositories) {

		repositoriesMap = repositories.stream()
				.collect(Collectors.toMap(DefaultRepository::getRepo, Function.identity()));
	}

	@Value("#{${consumers-daos-mapping}}")
	Map<String, String> consumersToDaosMap;

	/**
	 * Insert/Update record(s) in data table.
	 * 
	 * @param {@linkplain String}.
	 * @param {@linkplain Object}.
	 * @return {@linkplain Response}.
	 */
	public Response<?> save(String consumer, Object records) {

		return new Response<>(getRepo(consumer).process(records, "SAVE"), HttpStatus.RESET_CONTENT.name(),
				String.valueOf(HttpStatus.RESET_CONTENT.value()));
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