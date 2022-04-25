package com.example.challenge.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.challenge.ChallengeApplication;
import com.example.challenge.config.AppUserConfig;
import com.example.challenge.location.impl.LocationServiceImpl;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration (classes = {ChallengeApplication.class,
								  LocationServiceImpl.class,
								  LocationClientConfigTest.class})
class LocationControllerTest {

	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private AppUserConfig appUserConfig;
	
	
	@Test
    public void loginUserTest() throws Exception {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		//Success Login
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON).content("{ \"password\": \"challenge\", \"username\": \"juan.challenge.2\"}")).andReturn();
        
        //Not Found State 
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/location?state=test").header(appUserConfig.getAuthorizationHeader(), mvcResult.getResponse().getHeader(appUserConfig.getAuthorizationHeader()))).andExpect(status().isNotFound());
		
		//Found an State
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/location?state=Estero").header(appUserConfig.getAuthorizationHeader(), mvcResult.getResponse().getHeader(appUserConfig.getAuthorizationHeader()))).andExpect(status().isOk());
		
	}

}
