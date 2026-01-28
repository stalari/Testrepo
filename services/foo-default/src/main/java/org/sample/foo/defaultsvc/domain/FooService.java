package org.sample.foo.defaultsvc.domain;

import org.springframework.stereotype.Service;

@Service
public class FooService {

    private final BarClient barClient;

    public FooService(BarClient barClient) {
        this.barClient = barClient;
    }

    public String tellMeSomething() {
        return "Foo tells you (this is what Bar said): " + barClient.sayHello();
    }
}