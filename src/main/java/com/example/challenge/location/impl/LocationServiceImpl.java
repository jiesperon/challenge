package com.example.challenge.location.impl;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private LocationClient locationClient;

	@Override
	public Coordinates getCoordinatesByStateName(String name) throws HttpClientErrorException {
		StatesDetails<? extends State<? extends Coordinates>> state = locationClient.findStateDetailsByStateName(name);
		if (state.getTotalElements() > 0) {
			if (state.getTotalElements() == 1) {
				return state.getStates().get(0).getCoordinates();
			} else {
				throw new HttpClientErrorException(HttpStatus.CONFLICT, "Confict", "{\"message\":\"More than one items\"}".getBytes(),StandardCharsets.UTF_8);
			}
			
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not Found", "{\"message\":\"Not Found\"}".getBytes(),StandardCharsets.UTF_8);
		}
	}
}
