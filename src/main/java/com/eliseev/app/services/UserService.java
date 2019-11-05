package com.eliseev.app.services;

import com.eliseev.app.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> {

    private Logger logger = LoggerFactory.getLogger(TrainService.class);

    {
        User a = new User(1L, "Андрей", "Елисеев", "eliseev.andrei345@mail.ru",
                "admin", "admin");
        super.entities.put(1L, a);
        User b = new User(2L, "Владимир", "Елисеев", "someemail@gmail.ru",
                "bunny", "bunny");
        super.entities.put(2L, b);
    }


    public User signIn(User user) {
        logger.info("sign-in user {}", user);
        return super.entities.values().stream()
                .filter(u -> u.getLogin().equals(user.getLogin())
                && (u.getPass().equals(user.getPass())))
                .findAny()
                .orElse(null);
    }

}
