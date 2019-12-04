package com.eliseev.app.db.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.models.User;
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserDAOTest {

    private Logger logger = LoggerFactory.getLogger(UserDAOTest.class);

    @Autowired
    private UserDAO userDAO;

    @Test
    @Transactional
    public void signIn() {
        assertNotNull(userDAO.findByLoginAndPass("admin", "admin"));
        assertNull(userDAO.findByLoginAndPass("asdf", "asdfk"));
    }

    @Test
    @Transactional
    public void findAll() {
        List<User> users = userDAO.findAll();
        logger.info("{}", users);
        for (User user : users) {
            logger.info("{}", user);
            logger.info("{}", user.getRoles());
        }
    }

}
