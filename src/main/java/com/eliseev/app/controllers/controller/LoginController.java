package com.eliseev.app.controllers.controller;

import com.eliseev.app.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String getLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "sign_in_up";
    }

}
