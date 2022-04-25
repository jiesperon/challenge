package com.example.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private ApiKey apiKey() { 
	    return new ApiKey("JWT", "x-authorization", "header"); 
	}
	
	private SecurityContext securityContext() { 
	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
	}
	
	
	private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	}
	
	@Bean
	public Docket apiLogin() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("authentication")
				.apiInfo(getApiInfo())
			      .securityContexts(Arrays.asList(securityContext()))
			      
			      .select()
			      .apis(RequestHandlerSelectors.basePackage("com.example.challenge.controller"))
			      .paths(PathSelectors.regex("/api/v1/auth.*"))
			      .build();
	}
	
	@Bean
	public Docket apiLocation() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("location")
				.apiInfo(getApiInfo())
				  .securitySchemes(Arrays.asList(apiKey()))
			      .securityContexts(Arrays.asList(securityContext()))
			      .select()
			      .apis(RequestHandlerSelectors.basePackage("com.example.challenge.controller"))
			      .paths(PathSelectors.regex("/api/v1/auth.*").negate())
			      .build();
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Acciona IT Challenge Service API",
				"Acciona IT Challenge Service API Description",
				"1.0",
				"http://challenge.acciona-it.com/terms",
				new Contact("acciona-it", "https://acciona-it.com", "challenge@acciona-it.com"),
				"LICENSE",
				"LICENSE URL",
				Collections.emptyList()
				);
	}

}
