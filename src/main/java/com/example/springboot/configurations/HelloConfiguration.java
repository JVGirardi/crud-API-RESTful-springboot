package com.example.springboot.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfiguration {

    @Bean
    public Transport myService() {
        return new Car();
    }
}

class Car implements Transport {
    private String cor;
    private int ano;
}

interface Transport {
    String motor = "";
}
