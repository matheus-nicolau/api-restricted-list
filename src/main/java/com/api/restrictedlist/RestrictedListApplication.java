package com.api.restrictedlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RestrictedListApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestrictedListApplication.class, args);
	}

}
