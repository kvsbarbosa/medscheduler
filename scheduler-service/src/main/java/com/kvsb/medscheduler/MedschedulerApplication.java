package com.kvsb.medscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("dev")
public class MedschedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedschedulerApplication.class, args);
	}

}
