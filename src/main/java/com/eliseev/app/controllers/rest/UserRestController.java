package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.User;
import com.eliseev.app.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users/list", produces = "application/json")
public class UserRestController extends AbstractRestController<User, UserService> {

    private Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    public UserRestController(UserService service) {
        super(service);
    }
}
