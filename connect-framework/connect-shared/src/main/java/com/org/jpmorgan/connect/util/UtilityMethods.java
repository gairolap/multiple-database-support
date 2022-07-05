/**
 * Class to hold utility methods.
 */
package com.org.jpmorgan.connect.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UtilityMethods {

	static ObjectMapper mapper;

	@Autowired
	public UtilityMethods() {

		mapper = new ObjectMapper();
	}

	/**
	 * Check whether the given object is empty or not.
	 * 
	 * @param {@linkplain Object}
	 * @return {@linkplain boolean}.
	 */
	public static <T> boolean checkForEmptyParams(T object) {

		if (object instanceof List<?>) {

			List<Object> objectsList = ((List<?>) object).stream().map(element -> (Object) element)
					.collect(Collectors.toList());

			for (Object obj : objectsList) {

				if (ObjectUtils.isEmpty(obj))
					return true;
			}
			return false;
		}
		return ObjectUtils.isEmpty(object);
	}

	/**
	 * Converts given object to a given type.
	 * 
	 * @param {@linkplain Object}.
	 * @param {@linkplain TypeReference}.
	 * @return {@linkplain T}.
	 */
	public static <T> T convertObjToTypeRef(Object object, TypeReference<T> typeRef) {

		return mapper.convertValue(object, typeRef);
	}

	/**
	 * Converts given object to a list.
	 * 
	 * @param {@linkplain Object}.
	 * @return {@linkplain List<?>}.
	 */
	public static List<?> convertObjectToList(Object object) {

		List<?> list = new ArrayList<>();

		if (object.getClass().isArray()) {
			list = Arrays.asList((Object[]) object);
		} else if (object instanceof Collection) {
			list = new ArrayList<>((Collection<?>) object);
		}

		return list;
	}

}