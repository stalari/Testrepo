package org.sample.remotelocal;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeClientRunnerTest {

    @Test
    void run_printsBothResults_onSuccess() {
        CoffeeFacadeClient client = new CoffeeFacadeClient() {
            @Override public String drinkLocalCoffee() { return "L"; }
            @Override public String drinkRemoteCoffee() { return "R"; }
        };

        ByteArrayOutputStream outBuf = new ByteArrayOutputStream();
        ByteArrayOutputStream errBuf = new ByteArrayOutputStream();

        int code = new CoffeeClientRunner(client).run(new PrintStream(outBuf), new PrintStream(errBuf));

        assertEquals(0, code);
        assertEquals("L" + System.lineSeparator() + "R" + System.lineSeparator(), outBuf.toString());
        assertEquals("", errBuf.toString());
    }

    @Test
    void run_printsStackTrace_toErr_onFailure_likeLegacyCoffeeMain() {
        RuntimeException ex = new RuntimeException("boom");
        CoffeeFacadeClient client = new CoffeeFacadeClient() {
            @Override public String drinkLocalCoffee() { throw ex; }
            @Override public String drinkRemoteCoffee() { return "unused"; }
        };

        ByteArrayOutputStream outBuf = new ByteArrayOutputStream();
        ByteArrayOutputStream errBuf = new ByteArrayOutputStream();

        int code = new CoffeeClientRunner(client).run(new PrintStream(outBuf), new PrintStream(errBuf));

        assertEquals(1, code);
        assertEquals("", outBuf.toString());
        assertTrue(errBuf.toString().contains("java.lang.RuntimeException: boom"));
    }
}