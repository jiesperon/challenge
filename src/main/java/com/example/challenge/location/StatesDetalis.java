package com.example.challenge.location;

import java.util.List;

public interface StatesDetalis <S extends State<? extends Coordinates>>{
	
	public Long getTotalElements();
	
	public void setTotalElements(Long totalElements);
	
	public Long getStartIndex();
	
	public void setStartIndex(Long startIndex);
	
	public List<S>  getStates();
	
	public void setStates(List<S> states);
	
}
