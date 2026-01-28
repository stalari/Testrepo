package com.acme.foo.acmesvc.api;

import com.acme.foo.acmesvc.domain.FooAcmeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    private final FooAcmeService fooService;

    public FooController(FooAcmeService fooService) {
        this.fooService = fooService;
    }

    @GetMapping(value = "/foo/tell-me-something", produces = MediaType.TEXT_PLAIN_VALUE)
    public String tellMeSomething() {
        return fooService.tellMeSomething();
    }
}