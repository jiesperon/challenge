package com.example.challenge.location.impl;

import com.example.challenge.location.Coordinates;
import com.example.challenge.location.State;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class StateImpl implements State<CoordinatesImpl> {
	
	private Long id;
	
	private String name;
	
	private Coordinates coordinates;
	
	@JsonGetter("id")
	public Long getId() {
		return id;
	}

	@JsonSetter("ig")
	public void setId(Long id) {
		this.id = id;

	}

	@JsonGetter("nombre")
	public String getName() {
		return name;
	}

	@JsonSetter("nombre")
	public void setName(String name) {
		this.name = name;

	}

	@JsonGetter("centroide")
	public Coordinates getCoordinates() {
		return coordinates;
	}

	@JsonSetter("centroide")
	public void setCooridantes(CoordinatesImpl coordinates) {
		this.coordinates = coordinates;

	}

}
