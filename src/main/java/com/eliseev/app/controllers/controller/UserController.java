package com.eliseev.app.controllers.controller;


import com.eliseev.app.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/authoriseForm")
    public String getSignInUpForm(Model model) {
        logger.info("user send GET /users/authoriseForm");
        model.addAttribute("user", new User());
        return "sign_in_up";
    }


}
