package com.eliseev.app.controllers.controller;

import com.eliseev.app.models.User;
import com.eliseev.app.models.UserRoleEnum;
import com.eliseev.app.services.RoleService;
import com.eliseev.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping
    public String processRegistration(@ModelAttribute  User user) {
        user.setPass(passwordEncoder.encode(user.getPass()));
        user.getRoles().add(roleService.findByName(UserRoleEnum.USER));
        userService.create(user);
        return "redirect:/login";
    }
}
