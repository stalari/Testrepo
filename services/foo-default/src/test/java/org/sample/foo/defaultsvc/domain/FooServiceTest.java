package org.sample.foo.defaultsvc.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FooServiceTest {

    @Test
    void tellMeSomething_includesBarSayHelloOutput_exactLegacyFormat() {
        BarClient bar = () -> "BAR_OUT";
        FooService foo = new FooService(bar);

        assertEquals("Foo tells you (this is what Bar said): BAR_OUT", foo.tellMeSomething());
    }
}