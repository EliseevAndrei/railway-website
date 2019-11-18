package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.User;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.UserDAO;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends AbstractDAO<User>
        implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

}
