package com.example.challenge.client.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import com.example.challenge.client.LocationClient;
import com.example.challenge.location.Coordinates;
import com.example.challenge.location.State;
import com.example.challenge.location.StatesDetails;
import com.example.challenge.location.impl.StatesDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;


@Service("locationClient")
public class LocationClientImpl implements LocationClient {
	
	private static final String ENDPOINT_LOCATION_STATE = "/georef/api/provincias?nombre={name}";
	
	private final String prototypeEndpoint;
	
	private final RestTemplate restTemplate;	
	
	public LocationClientImpl() {
		String endpoint = buildUrl("https", "apis.datos.gob.ar", null);
		this.restTemplate = new RestTemplate();
		this.prototypeEndpoint = endpoint + ENDPOINT_LOCATION_STATE;
	}
	
	
	@Override
	public StatesDetails<? extends State<? extends Coordinates>> findStateDetailsByStateName(String name) throws HttpClientErrorException {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Void> requestEntity = new HttpEntity<Void>(headers);
		ResponseEntity<StatesDetailsImpl> responseEntity = restTemplate.exchange(this.prototypeEndpoint, HttpMethod.GET, requestEntity, StatesDetailsImpl.class, name);
		StatesDetailsImpl entity = null;
		entity = responseEntity.getBody();
		return entity;
	}
	
	/**
	   * Builds the url.
	   *
	   * @param httpProtocol the sso protocol
	   * @param clientName the client name
	   * @param httpContext the http context
	   * @return the string
	   */
	  private String buildUrl(String httpProtocol, String clientName, String httpContext) {
	    StringBuilder endpointSb = new StringBuilder();
	    endpointSb.append(httpProtocol).append("://");
	    endpointSb.append(clientName);
	    if (!Strings.isNullOrEmpty(httpContext)) {
	      endpointSb.append('/').append(httpContext);
	    }

	    return endpointSb.toString();
	  }

}
