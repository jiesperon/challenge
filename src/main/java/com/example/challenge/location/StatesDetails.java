package com.example.challenge.location;

import java.util.List;

public interface StatesDetails <S extends State<? extends Coordinates>>{
	
	/**
	 * Get Size
	 * 
	 * @return Long
	 */
	public Long getSize();
	
	/**
	 * Set Size
	 * 
	 * @param size
	 */
	public void setSize(Long size);
	
	/**
	 * Get Total of Elements
	 * 
	 * @return Long
	 */
	public Long getTotalElements();
	
	/**
	 * Set Total of Elements
	 * 
	 * @param totalElements
	 */
	public void setTotalElements(Long totalElements);
	
	/**
	 * Get Start Index
	 * 
	 * @return Long
	 */
	public Long getStartIndex();
	
	/**
	 * Set Stat Index
	 * 
	 * @param startIndex
	 */
	public void setStartIndex(Long startIndex);
	
	
	/**
	 * Get List of States
	 * 
	 * @return List
	 */
	public List<? extends State<? extends Coordinates>>  getStates();
	
	
	/**
	 * Set List of States
	 * 
	 * @param states
	 */
	public void setStates(List<S> states);
	
}
