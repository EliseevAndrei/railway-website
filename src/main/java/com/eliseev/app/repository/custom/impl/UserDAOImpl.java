package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.User;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDAOImpl extends AbstractDAO<User>
        implements UserDAO {

    private Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    public UserDAOImpl() {
        super(User.class);
    }


    @Override
    public User findOne(long id) {
        try {
            return super.entityManager.createQuery("select distinct u from User u left join fetch u.roles where u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return super.entityManager.createQuery("select distinct u from User u left join fetch u.roles", User.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User findByLoginAndPass(String login, String pass) {

        try {
            return super.entityManager.createQuery("select distinct u from User u left join fetch u.roles where u.login = :login and u.pass = :pass", User.class)
                    .setParameter("login", login)
                    .setParameter("pass", pass)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public User findByUsername(String s) {
        try {
            return super.entityManager.createQuery("select distinct u from User u left join fetch u.roles where u.login = :login", User.class)
                    .setParameter("login", s)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
