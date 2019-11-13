package com.eliseev.app.db;

import com.eliseev.app.repository.tmp.EmployeeDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class DaoEmployeeTest {

    @Autowired
    private EmployeeDAO dao;

    @Test
    public void count() {
        dao.findOne(6);
    }


}
