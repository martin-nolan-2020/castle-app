package com.martinnolan.castlemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.martinnolan.*")
public class CastlemanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CastlemanagerApplication.class, args);
	}

}
