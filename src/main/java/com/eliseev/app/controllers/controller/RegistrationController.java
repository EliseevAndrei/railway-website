package com.eliseev.app.controllers.controller;

import com.eliseev.app.dto.RoleDto;
import com.eliseev.app.dto.UserDto;
import com.eliseev.app.models.UserRoleEnum;
import com.eliseev.app.services.RoleService;
import com.eliseev.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
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


    @PostMapping("/register")
    public String processRegistration(@ModelAttribute UserDto user) {
        user.setPass(passwordEncoder.encode(user.getPass()));
        RoleDto roleDto = roleService.findByName(UserRoleEnum.USER);
        user.getRoles().add(roleDto);
        UserDto createdUser = userService.create(user);
        if (createdUser == null) {
            return "error/NotUniqueUserError";
        }
        return "redirect:/login";
    }

    @PostMapping("/login-error")
    public String getLoginErrorPage() {
        return "error/login-error";
    }
}
