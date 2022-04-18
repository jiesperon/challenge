package com.example.challenge.config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.security.Keys;

@Configuration
public class AppSecretKeyConfig {
	
	private final AppUserConfig appUserConfig;
	
	@Autowired
	public AppSecretKeyConfig(AppUserConfig appUserConfig) {
		this.appUserConfig = appUserConfig;
	}
	
	@Bean
	public SecretKey secretKey() {
		return Keys.hmacShaKeyFor(appUserConfig.getSecretKey().getBytes());
	}
	
}
