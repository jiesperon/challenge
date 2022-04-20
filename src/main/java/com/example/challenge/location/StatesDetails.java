package com.example.challenge.location;

import java.util.List;

public interface StatesDetails <S extends State<? extends Coordinates>>{
	
	public Long getSize();
	
	public void setSize(Long size);
	
	public Long getTotalElements();
	
	public void setTotalElements(Long totalElements);
	
	public Long getStartIndex();
	
	public void setStartIndex(Long startIndex);
	
	public List<? extends State<? extends Coordinates>>  getStates();
	
	public void setStates(List<S> states);
	
}
