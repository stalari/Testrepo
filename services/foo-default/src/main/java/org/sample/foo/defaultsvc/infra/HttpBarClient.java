package org.sample.foo.defaultsvc.infra;

import org.sample.foo.defaultsvc.domain.BarClient;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public class HttpBarClient implements BarClient {

    private final RestClient restClient;

    public HttpBarClient(String baseUrl, RestClient.Builder builder) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    @Override
    public String sayHello() {
        return restClient.get()
            .uri("/bar/hello")
            .accept(MediaType.TEXT_PLAIN)
            .retrieve()
            .body(String.class);
    }
}