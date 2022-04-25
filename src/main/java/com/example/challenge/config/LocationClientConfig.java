package com.example.challenge.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import com.example.challenge.client.LocationClient;
import com.example.challenge.client.impl.LocationClientImpl;

@Profile("!test")
@Configuration
public class LocationClientConfig {
	
	@Bean
	@Qualifier("locationClient")
	LocationClient locationClient() {
		return new LocationClientImpl(new RestTemplate());
	}
	
}
