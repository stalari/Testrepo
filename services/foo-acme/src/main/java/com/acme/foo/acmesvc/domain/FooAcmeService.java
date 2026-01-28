package com.acme.foo.acmesvc.domain;

import org.springframework.stereotype.Service;

@Service
public class FooAcmeService {

    private final FooDefaultClient fooDefaultClient;

    public FooAcmeService(FooDefaultClient fooDefaultClient) {
        this.fooDefaultClient = fooDefaultClient;
    }

    public String tellMeSomething() {
        return "this is ACME FOO. any my parent says: " + fooDefaultClient.tellMeSomething();
    }
}