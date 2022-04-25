package com.example.challenge.location;

import org.springframework.web.client.HttpClientErrorException;

public interface LocationService {
	
	/**
	 * Get Coordinates By State Name
	 * 
	 * @param name
	 * @return Coordinates
	 * @throws HttpClientErrorException
	 */
	public Coordinates getCoordinatesByStateName(String name) throws HttpClientErrorException;

}
