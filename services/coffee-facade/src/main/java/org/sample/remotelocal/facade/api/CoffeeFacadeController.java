package org.sample.remotelocal.facade.api;

import org.sample.remotelocal.facade.domain.CoffeeFacadeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeFacadeController {

    private final CoffeeFacadeService service;

    public CoffeeFacadeController(CoffeeFacadeService service) {
        this.service = service;
    }

    @GetMapping(value = "/coffee-facade/drink-local", produces = MediaType.TEXT_PLAIN_VALUE)
    public String drinkLocalCoffee() {
        return service.drinkLocalCoffee();
    }

    @GetMapping(value = "/coffee-facade/drink-remote", produces = MediaType.TEXT_PLAIN_VALUE)
    public String drinkRemoteCoffee() {
        return service.drinkRemoteCoffee();
    }
}