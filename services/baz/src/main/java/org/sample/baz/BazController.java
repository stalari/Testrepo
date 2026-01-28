package org.sample.baz;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BazController {

    @GetMapping(value = "/baz", produces = MediaType.TEXT_PLAIN_VALUE)
    public String baz() {
        return "baaaaaaaazzzzz!";
    }
}