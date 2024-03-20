package com.tynuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.tynentity"})
public class TynUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(TynUserApplication.class, args);
    }

}
