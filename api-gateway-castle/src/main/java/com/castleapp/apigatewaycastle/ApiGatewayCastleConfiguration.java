package com.castleapp.apigatewaycastle;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin(origins = "http://localhost:4200/")
public class ApiGatewayCastleConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		//want to customise the routes
		//useful to clean up the URIs 
		//in this case we are removing the microservice names from the URIs such as castlemanager and calendar-service
		return builder.routes()
				.route(p -> p.path("/get")		//if a request comes to /get then we want to redirect it to http://httpbin.org
						.filters(f -> f		//also adding a header
								.addRequestHeader("MyHeader!", "MyURI"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/castles-with-bookings-feign/**") //from http://localhost:8765/castlemanager/castles-with-bookings-feign/3/date/2017-02-25
						.uri("lb://castlemanager"))					  //to http://localhost:8765/castles-with-bookings-feign/3/date/2017-02-25
				.route(p -> p.path("/castles-with-bookings/**") 
						.uri("lb://castlemanager"))	
				.route(p -> p.path("/castles/**") 
						.uri("lb://castlemanager"))	
//				.route(p -> p.path("/max-length/**") 
//						.uri("lb://castlemanager"))	
				.route(p -> p.path("/first-test") 
						.uri("lb://castlemanager"))
				.route(p -> p.path("/second-test-castle-bean") 
						.uri("lb://castlemanager"))	
				.route(p -> p.path("/calendar/**")		//from http://localhost:8765/calendar-service/calendar/date/2021-05-23/castles/77
						.uri("lb://calendar-service"))	//to http://localhost:8765/calendar/date/2021-05-23/castles/77
				.build();
	}

}
