package com.eliseev.app.services;

import com.eliseev.app.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> {

    private Logger logger = LoggerFactory.getLogger(TrainService.class);

    {
        User a = new User(1L, "Андрей", "Елисеев", "ывлао23Э",
                "dron", "dron");
        super.entities.put(1L, a);
        User b = new User(2L, "Владимир", "Елисеев", "ывлао23Э",
                "bunny", "bunny");
        super.entities.put(2L, b);
    }



}
