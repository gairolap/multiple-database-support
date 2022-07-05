/**
 * Controller class for READ operation. 
 */
package com.org.jpmorgan.connect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.jpmorgan.connect.service.ReadService;
import com.org.jpmorgan.connect.util.UtilityMethods;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReadController {

	@Autowired
	ReadService crudService;
	
	@GetMapping("${uri}")
	public ResponseEntity<?> getRecords(@RequestHeader(value = "consumer", required = true) String consumer,
			@RequestParam(value = "tableName", required = true) String tableName) {
		
		if (UtilityMethods.checkForEmptyParams(tableName) || UtilityMethods.checkForEmptyParams(consumer)) {

			throw new IllegalArgumentException();
		}

		return  new ResponseEntity<>(crudService.getRecords(consumer, tableName), HttpStatus.OK);
	}

}