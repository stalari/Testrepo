package org.sample.foo.defaultsvc.config;

import org.sample.foo.defaultsvc.domain.BarClient;
import org.sample.foo.defaultsvc.infra.HttpBarClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class BarClientConfiguration {

    @Bean
    public BarClient barClient(
        FooWiringProperties wiring,
        BarEndpointsProperties endpoints,
        RestClient.Builder builder
    ) {
        return switch (wiring.orgSampleFooBeanBar()) {
            case "BarBean" -> new HttpBarClient(endpoints.defaultBaseUrl(), builder);
            case "AcmeBarBean" -> new HttpBarClient(endpoints.acmeBaseUrl(), builder);
            default -> throw new IllegalStateException("Unexpected wiring value: " + wiring.orgSampleFooBeanBar());
        };
    }
}