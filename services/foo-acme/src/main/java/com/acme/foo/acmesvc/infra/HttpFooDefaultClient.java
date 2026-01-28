package com.acme.foo.acmesvc.infra;

import com.acme.foo.acmesvc.domain.FooDefaultClient;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public class HttpFooDefaultClient implements FooDefaultClient {

    private final RestClient restClient;

    public HttpFooDefaultClient(String baseUrl, RestClient.Builder builder) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    @Override
    public String tellMeSomething() {
        return restClient.get()
            .uri("/foo/tell-me-something")
            .accept(MediaType.TEXT_PLAIN)
            .retrieve()
            .body(String.class);
    }
}