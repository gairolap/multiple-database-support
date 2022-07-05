/**
 * Controller class for DELETE operation. 
 */
package com.org.jpmorgan.connect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.jpmorgan.connect.service.DeleteService;
import com.org.jpmorgan.connect.util.UtilityMethods;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteController {

	@Autowired
	DeleteService crudService;

	@DeleteMapping("${uri}")
	public ResponseEntity<?> delete(@RequestHeader(value = "consumer", required = true) String consumer,
			@RequestBody(required = true) List<Object> rowIds) {

		if (UtilityMethods.checkForEmptyParams(rowIds) || UtilityMethods.checkForEmptyParams(consumer)) {

			throw new IllegalArgumentException();
		}

		return new ResponseEntity<>(crudService.delete(consumer, rowIds), HttpStatus.OK);
	}

}