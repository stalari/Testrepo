package org.sample.bar.defaultsvc;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarController {

    @GetMapping(value = "/bar/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello() {
        return "Dear Sir, humble greetings from the SAMPLE bar bean!";
    }
}