package com.example.challenge.client;


import org.springframework.web.client.HttpClientErrorException;

import com.example.challenge.location.Coordinates;
import com.example.challenge.location.State;
import com.example.challenge.location.StatesDetails;

public interface LocationClient {
	
	/**
	 * Find State Details Information By State Name
	 * 
	 * @param name
	 * @return StatesDetails
	 * @throws HttpClientErrorException
	 */
	StatesDetails<? extends State<? extends Coordinates>> findStateDetailsByStateName(String name) throws HttpClientErrorException;

}
