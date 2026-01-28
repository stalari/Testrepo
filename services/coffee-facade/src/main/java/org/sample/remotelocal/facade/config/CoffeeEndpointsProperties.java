package org.sample.remotelocal.facade.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "coffee")
public record CoffeeEndpointsProperties(
    @NotBlank String localBaseUrl,
    @NotBlank String remoteBaseUrl
) {}