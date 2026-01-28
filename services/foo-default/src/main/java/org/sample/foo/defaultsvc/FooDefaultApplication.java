package org.sample.foo.defaultsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class FooDefaultApplication {
    public static void main(String[] args) {
        SpringApplication.run(FooDefaultApplication.class, args);
    }
}