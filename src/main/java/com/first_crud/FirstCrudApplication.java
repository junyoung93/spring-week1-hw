package com.first_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FirstCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstCrudApplication.class, args);
    }

}
