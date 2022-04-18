package com.example.challenge.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/location")
public class LocationController {
	private final Logger log = LoggerFactory.getLogger(LocationController.class);
	
	
	@GetMapping("state")
	@PreAuthorize("hasAuthority('location:view')")
	public List<?> getState(@PathParam("name") String name) {
		log.info("Input State Name: {}", name);
		
        return null;
    }

}
