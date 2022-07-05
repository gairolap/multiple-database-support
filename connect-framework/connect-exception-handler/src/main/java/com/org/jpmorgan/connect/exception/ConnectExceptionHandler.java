/**
 * Class to provide exception handling throughout service.
 */
package com.org.jpmorgan.connect.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.org.jpmorgan.connect.model.response.Response;

@RestControllerAdvice
public class ConnectExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Process response for no data found.
	 * 
	 * @param {@linkplain Exception}.
	 * @param {@linkplain WebRequest}.
	 * @return {@linkplain ResponseEntity}.
	 */
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	protected ResponseEntity<?> handleEmptyResultDataAccessException(Exception exception, WebRequest request) {

		return new ResponseEntity<>(new Response<>("No data found for given table!", HttpStatus.NOT_FOUND.name(),
				String.valueOf(HttpStatus.NOT_FOUND.value())), HttpStatus.NOT_FOUND);
	}

	/**
	 * Process response for missing mandatory request header(s).
	 * 
	 * @param {@linkplain Exception}.
	 * @param {@linkplain WebRequest}.
	 * @return {@linkplain ResponseEntity}.
	 */
	@ExceptionHandler({ MissingRequestHeaderException.class })
	protected ResponseEntity<?> handleMissingRequestHeadersException(Exception exception, WebRequest request) {

		return new ResponseEntity<>(new Response<>("Bad request!", "Missing mandatory request headers!",
				String.valueOf(HttpStatus.BAD_REQUEST.value())), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Process response for missing mandatory request parameter(s).
	 * 
	 * @param {@linkplain Exception}.
	 * @param {@linkplain WebRequest}.
	 * @return {@linkplain ResponseEntity}.
	 */
	@ExceptionHandler({ IllegalArgumentException.class })
	protected ResponseEntity<?> handleInvalidMandatoryInfoException(Exception exception, WebRequest request) {

		return new ResponseEntity<>(new Response<>("Bad request!", "Invalid mandatory request params/headers!",
				String.valueOf(HttpStatus.BAD_REQUEST.value())), HttpStatus.BAD_REQUEST);
	}

}