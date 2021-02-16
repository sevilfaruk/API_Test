package com.example.part2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.testng.annotations.Test;

@SpringBootApplication
public class MainApplication {
    @Test
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
