/**
 * Controller class for SAVE operation. 
 */
package com.org.jpmorgan.connect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.jpmorgan.connect.service.SaveService;
import com.org.jpmorgan.connect.util.UtilityMethods;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveController {

	@Autowired
	SaveService crudService;

	@PostMapping("${uri}")
	public ResponseEntity<?> save(@RequestHeader(value = "consumer", required = true) String consumer,
			@RequestBody(required = true) List<Object> records) {

		if (UtilityMethods.checkForEmptyParams(records) || UtilityMethods.checkForEmptyParams(consumer)) {

			throw new IllegalArgumentException();
		}

		return new ResponseEntity<>(crudService.save(consumer, records), HttpStatus.OK);
	}

}