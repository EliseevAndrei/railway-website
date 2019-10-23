package com.eliseev.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class SpringServiceController {
    final Logger logger = LoggerFactory.getLogger(SpringServiceController.class);

    @GetMapping
    public String getGreeting() {
        logger.info("User send GET /hello request");
        return "index";
    }

    @GetMapping("/{name}")
    public String getGreetingToUser(@PathVariable(name = "name") String name,
        Model model) {
        model.addAttribute("name", name);
        logger.info("User send GET /hello {} request", name);
        return "user_home";
    }

}
