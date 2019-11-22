package com.eliseev.app.db.dao;

import com.eliseev.app.repository.custom.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    @Transactional
    public void signIn() {
        assertNotNull(userDAO.findByLoginAndPass("admin", "admin"));
        assertNull(userDAO.findByLoginAndPass("asdf", "asdfk"));
    }

}
