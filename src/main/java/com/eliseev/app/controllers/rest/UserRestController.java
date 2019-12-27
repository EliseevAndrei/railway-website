package com.eliseev.app.controllers.rest;

import com.eliseev.app.dto.UserDto;
import com.eliseev.app.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/users/list", produces = "application/json")
public class UserRestController extends AbstractRestController<UserDto, UserService> {

    private Logger logger = LoggerFactory.getLogger(UserRestController.class);
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserRestController(UserService service,
                              PasswordEncoder passwordEncoder) {
        super(service);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto create(@Valid @RequestBody UserDto entity, @PathVariable(required = false) long... id) {
        entity.setPass(passwordEncoder.encode(entity.getPass()));
        return super.create(entity, id);
    }
}
