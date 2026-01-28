package org.sample.remotelocal;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public class HttpCoffeeFacadeClient implements CoffeeFacadeClient {

    private final RestClient restClient;

    public HttpCoffeeFacadeClient(String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    @Override
    public String drinkLocalCoffee() {
        return restClient.get()
            .uri("/coffee-facade/drink-local")
            .accept(MediaType.TEXT_PLAIN)
            .retrieve()
            .body(String.class);
    }

    @Override
    public String drinkRemoteCoffee() {
        return restClient.get()
            .uri("/coffee-facade/drink-remote")
            .accept(MediaType.TEXT_PLAIN)
            .retrieve()
            .body(String.class);
    }
}