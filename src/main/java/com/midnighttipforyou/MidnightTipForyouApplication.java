package com.midnighttipforyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MidnightTipForyouApplication {

    public static void main(String[] args) {
        SpringApplication.run(MidnightTipForyouApplication.class, args);
    }

}
