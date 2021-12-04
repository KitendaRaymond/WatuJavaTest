package com.example.wetu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WatuApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatuApplication.class, args);
    }

}
