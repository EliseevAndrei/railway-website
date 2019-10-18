package com.eliseev.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/hello")
public class SpringServiceController {

    @GetMapping
    public String getGreeting() {
        return "index";
    }

}
