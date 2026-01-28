package org.sample.foo.defaultsvc.api;

import org.sample.foo.defaultsvc.domain.FooService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    private final FooService fooService;

    public FooController(FooService fooService) {
        this.fooService = fooService;
    }

    @GetMapping(value = "/foo/tell-me-something", produces = MediaType.TEXT_PLAIN_VALUE)
    public String tellMeSomething() {
        return fooService.tellMeSomething();
    }
}