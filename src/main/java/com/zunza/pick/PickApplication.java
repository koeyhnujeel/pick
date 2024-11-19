package com.zunza.pick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PickApplication {

	public static void main(String[] args) {
		SpringApplication.run(PickApplication.class, args);
	}

}
