package org.sample.remotelocal;

public class CoffeeMain {

    public static void main(String[] args) {
        String baseUrl = System.getenv().getOrDefault("COFFEE_FACADE_BASE_URL", "http://localhost:8086");
        CoffeeFacadeClient client = new HttpCoffeeFacadeClient(baseUrl);
        int code = new CoffeeClientRunner(client).run(System.out, System.err);
        if (code != 0) {
            System.exit(code);
        }
    }
}