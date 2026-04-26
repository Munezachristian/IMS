package com.airtel.IMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImsApplication.class, args);
        System.out.println("========================================");
        System.out.println("Airtel Inventory System Started!");
        System.out.println("Open browser at: http://localhost:8080");
        System.out.println("========================================");
    }
}