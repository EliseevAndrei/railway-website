package com.eliseev.app.services;

import com.eliseev.app.models.User;
import com.eliseev.app.repository.custom.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User, UserDAO> {

    @Autowired
    public UserService(UserDAO dao) {
        super(dao);
    }

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
