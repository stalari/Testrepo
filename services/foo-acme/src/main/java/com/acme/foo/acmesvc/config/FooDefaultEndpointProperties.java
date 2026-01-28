package com.acme.foo.acmesvc.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "foo")
public record FooDefaultEndpointProperties(@NotBlank String defaultBaseUrl) {}