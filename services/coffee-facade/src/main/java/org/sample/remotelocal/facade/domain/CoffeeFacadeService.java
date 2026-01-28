package org.sample.remotelocal.facade.domain;

import org.springframework.stereotype.Service;

@Service
public class CoffeeFacadeService {

    private final CoffeeClient localCoffee;
    private final CoffeeClient remoteCoffee;

    public CoffeeFacadeService(CoffeeClient localCoffee, CoffeeClient remoteCoffee) {
        this.localCoffee = localCoffee;
        this.remoteCoffee = remoteCoffee;
    }

    public String drinkLocalCoffee() {
        return "drinking Wacker's Kaffee: " + localCoffee.drink() + " (via " + localCoffee.getClass().getSimpleName() + ")";
    }

    public String drinkRemoteCoffee() {
        return "drinking Starbucks: " + remoteCoffee.drink() + " (via " + remoteCoffee.getClass().getSimpleName() + ")";
    }
}