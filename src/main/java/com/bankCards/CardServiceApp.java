package com.bankCards;

import com.bankCards.security.JWTProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JWTProperties.class)
public class CardServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(CardServiceApp.class, args);
    }
}
