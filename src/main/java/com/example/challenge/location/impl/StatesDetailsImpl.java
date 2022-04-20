package com.example.challenge.location.impl;

import java.util.List;

import com.example.challenge.location.Coordinates;
import com.example.challenge.location.State;
import com.example.challenge.location.StatesDetails;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties
public class StatesDetailsImpl implements StatesDetails<StateImpl>{

	private Long size;
	private Long totalElements;
	private Long startIndex;
	private List<StateImpl> states;
	
	@JsonGetter("total")
	public Long getTotalElements() {
		return totalElements;
	}

	@JsonSetter("total")
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	@JsonGetter("inicio")
	public Long getStartIndex() {
		return startIndex;
	}

	@JsonSetter("inicio")
	public void setStartIndex(Long startIndex) {
		this.startIndex = startIndex;
	}

	@JsonGetter("provincias")
	public List<? extends State<? extends Coordinates>> getStates() {
		return states;
	}

	@JsonSetter("provincias")
	public void setStates(List<StateImpl> states) {
		this.states = states;
		
	}

	@JsonGetter("cantidad")
	public Long getSize() {
		return size;
	}

	@JsonSetter("cantidad")
	public void setSize(Long size) {
		this.size = size;
	}

}
