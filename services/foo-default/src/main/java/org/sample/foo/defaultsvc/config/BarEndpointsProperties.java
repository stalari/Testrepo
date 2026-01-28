package org.sample.foo.defaultsvc.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "bar")
public record BarEndpointsProperties(
    @NotBlank String defaultBaseUrl,
    @NotBlank String acmeBaseUrl
) {}