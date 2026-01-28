package org.sample.remotelocal.facade.infra;

import org.sample.remotelocal.facade.domain.CoffeeClient;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public class RemoteCoffeeHttpClient implements CoffeeClient {

    private final RestClient restClient;

    public RemoteCoffeeHttpClient(String baseUrl, RestClient.Builder builder) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    @Override
    public String drink() {
        return restClient.get()
            .uri("/coffee/drink")
            .accept(MediaType.TEXT_PLAIN)
            .retrieve()
            .body(String.class);
    }
}