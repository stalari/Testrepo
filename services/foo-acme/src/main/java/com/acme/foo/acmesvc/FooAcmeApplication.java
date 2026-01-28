package com.acme.foo.acmesvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class FooAcmeApplication {
    public static void main(String[] args) {
        SpringApplication.run(FooAcmeApplication.class, args);
    }
}