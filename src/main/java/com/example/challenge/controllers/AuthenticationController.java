package com.example.challenge.controllers;

import java.time.LocalDate;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.challenge.auth.domain.AppUsernameAndPasswordAuthenticationRequest;
import com.example.challenge.config.AppUserConfig;

import io.jsonwebtoken.Jwts;


@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AppUserConfig appUserConfig;
	
	@Autowired
	private SecretKey secretKey;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<AppUsernameAndPasswordAuthenticationRequest> login(@RequestBody AppUsernameAndPasswordAuthenticationRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		//Building create token
		String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(appUserConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();
		
		
		
	    loginRequest.setPassword(token);
	    
		return ResponseEntity.ok().header(appUserConfig.getAuthorizationHeader(), token).body(loginRequest);
	}
}
