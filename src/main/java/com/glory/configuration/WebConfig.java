package com.glory.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@Import(WebSecurityConfig.class)
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/")
	   	  .allowedOrigins("http://localhost:8080")
		  .allowedMethods("*")
		  .allowedHeaders("*")
		  .allowCredentials(false)	
		  .maxAge(4800);
	}

}
