package com.example.challenge.location;

import org.springframework.web.client.HttpClientErrorException;

public interface LocationService {
	
	public Coordinates getCoordinatesByStateName(String name) throws HttpClientErrorException;

}
