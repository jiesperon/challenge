package com.example.challenge.location.impl;

import java.util.List;

import com.example.challenge.location.StatesDetalis;

public class StatesDetailsImpl implements StatesDetalis<StateImpl>{

	
	private Long totalElements;
	private Long startIndex;
	private List<StateImpl> states;
	
	@Override
	public Long getTotalElements() {
		return totalElements;
	}

	@Override
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	@Override
	public Long getStartIndex() {
		return startIndex;
	}

	@Override
	public void setStartIndex(Long startIndex) {
		this.startIndex = startIndex;
	}

	@Override
	public List<StateImpl> getStates() {
		return states;
	}

	@Override
	public void setStates(List<StateImpl> states) {
		this.states = states;
		
	}

}
