//package com.castleapp.basic.auth;
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//
//@Configuration
//@EnableWebSecurity
////@ComponentScan("com.castleapp.apigatewaycastle")
//public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		//this.logger.debug("Using default configure(HttpSecurity). "
//		//		+ "If subclassed this will potentially override subclass configure(HttpSecurity).");
//		
////		http.csrf().disable();
////		
////		//http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
////		http.authorizeRequests()
////			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
////				.anyRequest().authenticated();
////			
////		//http.formLogin();
////		http.httpBasic();
//		
//		http
//			.csrf().disable()
//			.authorizeRequests()
//			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//				.anyRequest().authenticated()
//				.and().httpBasic();
//	}
//} 




