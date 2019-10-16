package com.eliseev.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class SpringServiceController {
    @RequestMapping(method = RequestMethod.GET)
    public String getGreeting() {
        return "Hello, World";
    }
}
