package com.castleapp.namingservercastle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NamingServerCastleApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamingServerCastleApplication.class, args);
	}

}
