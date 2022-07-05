/**
 * Default repository interface.
 */
package com.org.jpmorgan.connect.repo;

public interface DefaultRepository {

	Object process(Object request, String operation);

	String getRepo();

}