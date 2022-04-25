package com.example.challenge.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@Test
    public void loginUserTest() throws Exception {
		//Success Login
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON).content("{ \"password\": \"challenge\", \"username\": \"juan.challenge.2\"}")).andExpect(status().is2xxSuccessful());
        
        //Failed Login (incorrect username)
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON).content("{ \"password\": \"challenge\", \"username\": \"juan.challenge\"}")).andExpect(status().is4xxClientError());
    }
	
	@Test
    public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
		//Forbidden Access without token
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/location?state=test")).andExpect(status().isForbidden());
    }

}
