package com.example.challenge.location.impl;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.example.challenge.client.LocationClient;
import com.example.challenge.location.Coordinates;
import com.example.challenge.location.LocationService;
import com.example.challenge.location.State;
import com.example.challenge.location.StatesDetails;

@Service
public class LocationServiceImpl implements LocationService {
	
	private final Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);
	
	@Autowired
	@Qualifier("locationClient")
	private LocationClient locationClient;

	@Override
	public Coordinates getCoordinatesByStateName(String name) throws HttpClientErrorException {
		log.debug("State Name: {}", name);
		StatesDetails<? extends State<? extends Coordinates>> state = locationClient.findStateDetailsByStateName(name);
		log.debug("State: {}", state);
		//Checks Exist State
		if (state.getTotalElements() > 0) {
			//Checks if brings one State
			if (state.getTotalElements() == 1) {
				return state.getStates().get(0).getCoordinates();
			} else {
				log.error("More than one element for State {}", name);
				throw new HttpClientErrorException(HttpStatus.CONFLICT, "Confict", "{\"message\":\"More than one items\"}".getBytes(),StandardCharsets.UTF_8);
			}
		} else {
			log.warn("Not found State {}", name);
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not Found", "{\"message\":\"Not Found\"}".getBytes(),StandardCharsets.UTF_8);
		}
	}
}
