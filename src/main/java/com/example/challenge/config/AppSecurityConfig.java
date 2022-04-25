package com.example.challenge.config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.challenge.auth.domain.AppUserDetailsService;
import com.example.challenge.auth.domain.AppUserTokenVerifier;
import com.example.challenge.auth.domain.AppUsernameAndPasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;
	private final AppUserDetailsService appUserDetailsService;
	private final AppUserConfig appUserConfig;
	private final SecretKey secretKey;
	
	@Autowired
	public AppSecurityConfig(PasswordEncoder passwordEncoder, 
							 AppUserDetailsService appUserDetailsService,
							 SecretKey secretKey,
                             AppUserConfig appUserConfig) {
		this.passwordEncoder = passwordEncoder;
		this.appUserDetailsService = appUserDetailsService;
		this.appUserConfig = appUserConfig;
		this.secretKey = secretKey;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(appUserDetailsService);
		return provider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(new AppUsernameAndPasswordAuthenticationFilter(authenticationManager(), appUserConfig, secretKey))
            .addFilterAfter(new AppUserTokenVerifier(secretKey, appUserConfig),AppUsernameAndPasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers("/", "index", "/css/*", "/js/*", "/login", "/swagger-ui/*", "/swagger-resources/**","/v2/api-docs", "/api/v1/auth/*","/api/v1/auth/login").permitAll()
            .anyRequest()
            .authenticated();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	

}
