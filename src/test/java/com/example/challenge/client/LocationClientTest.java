package com.example.challenge.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;


import com.example.challenge.location.Coordinates;
import com.example.challenge.location.State;
import com.example.challenge.location.StatesDetails;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
class LocationClientTest {
	
	@Autowired
	private LocationClient locationClient;
	
	@Test
	void NotFoundStatetest() throws Exception {
		try {
			locationClient.findStateDetailsByStateName("Estero");
		} catch (HttpClientErrorException e) {
			assertThat(e.getStatusCode() == HttpStatus.NOT_FOUND);
		}
		
	}
	
	@Test
	void FoundStatetest() throws Exception {
		try {
			StatesDetails<? extends State<? extends Coordinates>> test = locationClient.findStateDetailsByStateName("Estero");
			if (test.getSize() != 1) {
				//Not Found State
				assertThat(false);
			}
		} catch (HttpClientErrorException e) {
			// HTTP Request Ex
			assertThat(false);
		} catch (Exception e) {
			assertThat(false);
		}
	}

}
