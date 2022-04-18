package com.example.challenge.auth;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.example.challenge.ChallengeApplication;
import com.example.challenge.config.AppPasswordConfg;
import com.example.challenge.config.AppSecurityConfig;
import com.example.challenge.config.AppUserConfig;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration( classes = { 
		ChallengeApplication.class,
		AppPasswordConfg.class,
		AppSecurityConfig.class,
		AppUserConfig.class,
		AppSecurityConfig.class,
		AppUserDetailsloginTest.class,
		AppUserDetailsService.class
		})
public class AppUserDetailsloginTest {
	
	@Autowired
    private MockMvc mvc;
	
	@Autowired
	private AppUserConfig appUserConfig;
	
	@Test
    public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/location?state=test")).andExpect(status().isForbidden());
    }
	
	@Test
    public void loginUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login?username=juan.challenge.2&password=challenge")).andExpect(status().is2xxSuccessful());
    }
	
	@Test
    public void shouldAllowAccessToUnauthenticatedUsers() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login?username=juan.challenge.2&password=challenge")).andReturn();
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/location?state=test").header(appUserConfig.getAuthorizationHeader(), mvcResult.getResponse().getHeader(appUserConfig.getAuthorizationHeader()))).andExpect(status().is4xxClientError());
		
		//mvc.perform(MockMvcRequestBuilders.get("/api/v1/location?state=test").header(appUserConfig.getAuthorizationHeader(), mvcResult.getResponse().getHeader(appUserConfig.getAuthorizationHeader()))).andExpectAll(status().is4xxClientError(), status().is2xxSuccessful());
	}
	
	
	
}
