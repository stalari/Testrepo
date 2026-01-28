package org.sample.remotelocal.facade.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoffeeFacadeServiceTest {

    static final class LocalClient implements CoffeeClient {
        @Override public String drink() { return "drinking coffee!"; }
    }

    static final class RemoteClient implements CoffeeClient {
        @Override public String drink() { return "drinking coffee!"; }
    }

    @Test
    void drinkLocalCoffee_matchesLegacyFormat_includingRuntimeClassSimpleName() {
        CoffeeFacadeService svc = new CoffeeFacadeService(new LocalClient(), new RemoteClient());
        assertEquals("drinking Wacker's Kaffee: drinking coffee! (via LocalClient)", svc.drinkLocalCoffee());
    }

    @Test
    void drinkRemoteCoffee_matchesLegacyFormat_includingRuntimeClassSimpleName() {
        CoffeeFacadeService svc = new CoffeeFacadeService(new LocalClient(), new RemoteClient());
        assertEquals("drinking Starbucks: drinking coffee! (via RemoteClient)", svc.drinkRemoteCoffee());
    }
}