package com.example.challenge.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import com.example.challenge.client.LocationClient;
import com.example.challenge.client.impl.LocationClientImpl;

@Profile("test")
@Configuration
class LocationClientConfigForTest {

	private  MockRestServiceServer server;
    
	@Bean
	@Qualifier("locationClient")
	LocationClient locationClient() {
		final RestTemplate restTemplate = new RestTemplate();
		initMock(restTemplate);
		return new LocationClientImpl(restTemplate);
	}
	
	public void initMock(RestTemplate restTemplate) {
		server = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
		
		server.expect(ExpectedCount.manyTimes(), MockRestRequestMatchers.requestTo("https://apis.datos.gob.ar/georef/api/provincias?nombre=test"))
        .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
        .andRespond(MockRestResponseCreators.withSuccess("{\"cantidad\":0,\"inicio\":0,\"parametros\":{\"nombre\":\"test\"},\"provincias\":[],\"total\":0}", MediaType.APPLICATION_JSON));
		
		server.expect(ExpectedCount.manyTimes(), MockRestRequestMatchers.requestTo("https://apis.datos.gob.ar/georef/api/provincias?nombre=Estero"))
        .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
        .andRespond(MockRestResponseCreators.withSuccess("{\"cantidad\":1,\"inicio\":0,\"parametros\":{\"nombre\":\"Sgo. del Estero\"},\"provincias\":[{\"centroide\":{\"lat\":-27.7824116550944,\"lon\":-63.2523866568588},\"id\":\"86\",\"nombre\":\"Santiago del Estero\"}],\"total\":1}", MediaType.APPLICATION_JSON));
	}

}
