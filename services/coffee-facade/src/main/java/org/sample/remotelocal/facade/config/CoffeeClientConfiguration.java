package org.sample.remotelocal.facade.config;

import org.sample.remotelocal.facade.domain.CoffeeClient;
import org.sample.remotelocal.facade.infra.LocalCoffeeHttpClient;
import org.sample.remotelocal.facade.infra.RemoteCoffeeHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class CoffeeClientConfiguration {

    @Bean
    public CoffeeClient localCoffee(CoffeeEndpointsProperties endpoints, RestClient.Builder builder) {
        return new LocalCoffeeHttpClient(endpoints.localBaseUrl(), builder);
    }

    @Bean
    public CoffeeClient remoteCoffee(CoffeeEndpointsProperties endpoints, RestClient.Builder builder) {
        return new RemoteCoffeeHttpClient(endpoints.remoteBaseUrl(), builder);
    }
}