package com.infodart.kenstar_crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KenstarCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(KenstarCrmApplication.class, args);
	}

}
