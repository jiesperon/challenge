package com.example.challenge.auth.domain;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.challenge.config.AppUserConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;

public class AppUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final Logger log = LoggerFactory.getLogger(AppUsernameAndPasswordAuthenticationFilter.class);
	private final AuthenticationManager authenticationManager;
    private final AppUserConfig appUserConfig;
    private final SecretKey secretKey;
    
    public AppUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager,
    												  AppUserConfig appUserConfig,
    												  SecretKey secretKey) {
		this.appUserConfig = appUserConfig;
		this.authenticationManager = authenticationManager;
		this.secretKey = secretKey;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
												HttpServletResponse response) throws AuthenticationException {
		
		try {
            AppUsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), AppUsernameAndPasswordAuthenticationRequest.class);
            
            
            log.debug("Authentification Username: '{}'", authenticationRequest.getUsername());
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );

            Authentication authenticate = authenticationManager.authenticate(authentication);
            return authenticate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
		
	}
	
	@Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(appUserConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();

        response.addHeader(appUserConfig.getAuthorizationHeader(), token);
    }
	
}
