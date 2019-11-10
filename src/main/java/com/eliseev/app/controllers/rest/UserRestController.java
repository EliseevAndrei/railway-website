package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.User;
import com.eliseev.app.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/registered")
    public ResponseEntity<User> signIn(@RequestBody User user) {
        logger.info("User send POST /users/list/registered with body {}", user);
        User registeredUser = super.service.signIn(user);
        if (registeredUser == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(registeredUser, HttpStatus.OK);
        }
    }
}
