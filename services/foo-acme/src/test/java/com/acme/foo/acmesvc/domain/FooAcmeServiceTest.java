package com.acme.foo.acmesvc.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FooAcmeServiceTest {

    @Test
    void tellMeSomething_prefixesAndPreservesBaseBehavior_exactLegacyString() {
        FooDefaultClient parent = () -> "PARENT_OUT";
        FooAcmeService svc = new FooAcmeService(parent);

        assertEquals("this is ACME FOO. any my parent says: PARENT_OUT", svc.tellMeSomething());
    }
}