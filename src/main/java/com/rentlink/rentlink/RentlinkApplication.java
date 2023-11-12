package com.rentlink.rentlink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class RentlinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentlinkApplication.class, args);
	}

}
