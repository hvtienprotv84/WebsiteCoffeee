package com.tynadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.tynentity", "com.tynadmin"})
public class TynAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(TynAdminApplication.class, args);
    }

}
