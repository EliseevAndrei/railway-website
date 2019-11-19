package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.User;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.UserDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class UserDAOImpl extends AbstractDAO<User>
        implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public User findByLoginAndPass(String login, String pass) {

        try {
            return super.entityManager.createQuery("select u from User u where u.login = :login and u.pass = :pass", User.class)
                    .setParameter("login", login)
                    .setParameter("pass", pass)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
