package com.eliseev.app.db.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.repository.custom.RoleDAO;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class RoleDAOTest {

    private Logger logger = LoggerFactory.getLogger(RoleDAOTest.class);

    @Autowired
    private RoleDAO roleDAO;


}
