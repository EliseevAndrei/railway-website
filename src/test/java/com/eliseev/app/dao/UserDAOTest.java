package com.eliseev.app.dao;

import com.eliseev.app.models.User;
import com.eliseev.app.repository.custom.RoleDAO;
import com.eliseev.app.repository.custom.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoTestConfig.class)
public class UserDAOTest {

    private Logger logger = LoggerFactory.getLogger(UserDAOTest.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Test
    @Transactional
    public void findAll() {
        List<User> users = userDAO.findAll();
        assertEquals(6, (long) users.get(0).getId());
        assertEquals(9, (long) users.get(1).getId());
    }

    @Test
    @Transactional
    public void delete() {
        assertEquals(2, userDAO.count());
        assertEquals(3, roleDAO.count());
        userDAO.delete(6);
        assertEquals(1, userDAO.count());
        assertEquals(3, roleDAO.count());
    }

    @Test
    @Transactional
    public void findByUserName() {
        User foundByName = userDAO.findByUsername("dron");
        User foundById = userDAO.findOne(9);
        assertEquals(foundById, foundByName);
    }

    @Test
    @Transactional
    public void findOne() {
        User user = userDAO.findOne(9);
        assertNotNull(user);
        assertEquals(2, user.getRoles().size());

        user = userDAO.findOne(6);
        assertNotNull(user);
        assertEquals(1, user.getRoles().size());
    }

}
