package com.acme.bar.acmesvc;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarController {

    @GetMapping(value = "/bar/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello() {
        return "Hey Dude, ACME wants to say H3ll0 to you!";
    }
}