package com.castleapp.apigatewaycastle.basic.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {
		//this.logger.debug("Using default configure(HttpSecurity). "
		//		+ "If subclassed this will potentially override subclass configure(HttpSecurity).");
		
		http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
		http.formLogin();
		http.httpBasic();
	}
} 

