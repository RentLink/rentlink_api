package com.rentlink.rentlink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RentlinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentlinkApplication.class, args);
    }
}
