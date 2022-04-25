package com.example.challenge.location;

public interface State<T extends Coordinates> {
	
	/**
	 * Get State Id
	 * 
	 * @return Long
	 */
	public Long getId();
	
	/**
	 * Set State Id
	 * 
	 * @param id
	 */
	public void setId(Long id);
	
	/**
	 * Get State NAme
	 * 
	 * @return String
	 */
	public String getName();
	
	/**
	 * Set State Name
	 * 
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * Get Coordinates
	 * 
	 * @return Coordinates
	 */
	public Coordinates getCoordinates();
	
	
	
	/**
	 * set Coordinates
	 * 
	 * @param coordinates
	 */
	public void setCooridantes(T coordinates);
	
	

}
