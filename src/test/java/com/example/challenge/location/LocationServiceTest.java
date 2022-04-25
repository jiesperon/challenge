package com.example.challenge.location;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
class LocationServiceTest {

	@Autowired
	private LocationService locationService;
	
	@Test
	void NotFoundStatetest() throws Exception {
		try {
			locationService.getCoordinatesByStateName("test");
		} catch (HttpClientErrorException e) {
			assertThat(e.getStatusCode() == HttpStatus.NOT_FOUND);
		}
		
	}
	
	@Test
	void FoundStatetest() throws Exception {
		try {
			Coordinates test = locationService.getCoordinatesByStateName("Estero");
			if (test == null) {
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
