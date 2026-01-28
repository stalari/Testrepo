package org.sample.remotelocal;

import java.io.PrintStream;

public class CoffeeClientRunner {

    private final CoffeeFacadeClient client;

    public CoffeeClientRunner(CoffeeFacadeClient client) {
        this.client = client;
    }

    public int run(PrintStream out, PrintStream err) {
        try {
            out.println(client.drinkLocalCoffee());
            out.println(client.drinkRemoteCoffee());
            return 0;
        } catch (Exception e) {
            e.printStackTrace(err);
            return 1;
        }
    }
}