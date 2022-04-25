package com.example.challenge.controllers;


import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.example.challenge.location.Coordinates;
import com.example.challenge.location.LocationService;

@RestController
@RequestMapping("api/v1/location")
public class LocationController {
	private final Logger log = LoggerFactory.getLogger(LocationController.class);
	
	@Autowired
	private LocationService locationService; 
	
	@GetMapping("state")
	@PreAuthorize("hasAuthority('location:view')")
	public ResponseEntity<?> getState(@PathParam("name") String name) {
		log.debug("Input State Name: {}", name);
		ResponseEntity<?> responseEntity = ResponseEntity.ok().build();
		try {
			Coordinates  coordinates = locationService.getCoordinatesByStateName(name);
			if (coordinates == null) {
				responseEntity = ResponseEntity.notFound().build();
			} else {
				responseEntity = ResponseEntity.ok().body(coordinates);
			}
		} catch (HttpClientErrorException e) {
			responseEntity = ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		} catch (Exception e) {
			throw e;
		}
        return responseEntity;
    }

}
