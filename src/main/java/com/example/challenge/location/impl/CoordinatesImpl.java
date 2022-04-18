package com.example.challenge.location.impl;

import com.example.challenge.location.Coordinates;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties
public class CoordinatesImpl implements Coordinates {

	private Double latitude;
	private Double longitude;
	
	@JsonGetter("lat")
	public Double getLatitude() {
		return latitude;
	}

	@JsonSetter("lat")
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
		
	}

	@JsonGetter("lon")
	public Double getLongitude() {
		return longitude;
	}

	@JsonSetter("lon")
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
