package com.example.challenge.location;

public interface State<T extends Coordinates> {
	
	public Long getId();
	
	public void setId(Long name);
	
	public String getName();
	
	public void setName(String name);
	
	public Coordinates getCoordinates();
	
	public void setCooridantes(T coordinates);
	
	

}
