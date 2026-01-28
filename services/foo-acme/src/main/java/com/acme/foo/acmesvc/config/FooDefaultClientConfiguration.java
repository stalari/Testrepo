package com.acme.foo.acmesvc.config;

import com.acme.foo.acmesvc.domain.FooDefaultClient;
import com.acme.foo.acmesvc.infra.HttpFooDefaultClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class FooDefaultClientConfiguration {

    @Bean
    public FooDefaultClient fooDefaultClient(FooDefaultEndpointProperties endpoints, RestClient.Builder builder) {
        return new HttpFooDefaultClient(endpoints.defaultBaseUrl(), builder);
    }
}