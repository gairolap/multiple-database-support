/**
 * Model object to hold the service response.
 */
package com.org.jpmorgan.connect.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
@AllArgsConstructor
public class Response<T> {

	T serviceResponse;
	String responseMsg;
	String responseCode;

}