package org.sample.coffee;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeController {

    @GetMapping(value = "/coffee/drink", produces = MediaType.TEXT_PLAIN_VALUE)
    public String drink() {
        return "drinking coffee!";
    }
}