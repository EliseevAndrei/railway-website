package com.eliseev.app.db;

import com.eliseev.app.models.tmp.Department;
import com.eliseev.app.models.tmp.Employee;
import com.eliseev.app.services.tmp.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class DaoEmployeeTest {

    private Logger logger = LoggerFactory.getLogger(DaoEmployeeTest.class);

    @Autowired
    private EmployeeService service;


    @Test
    public void count() {
        assertEquals(3, service.count());
    }

    @Test
    public void findOne() {
        Employee employee = service.findOne(1);
        assertEquals(employee.getLastName(), "eliseev");
        assertEquals(employee.getFirstName(), "andrei");
        assertEquals(employee.getEmail(), "eliseev.andrei345@mail.ru");
        assertEquals(employee.getDepartment().getName(), "dep");
    }

    @Test
    public void save() {
        assertEquals(3, service.count());
        Department department = new Department("new dep");
        Employee employee = new Employee("vera", "kozlova", "verko@mail.ru", department);
        Employee saved = service.save(employee);
        Employee found = service.findOne(4);
        assertEquals(found, saved);
        assertEquals(4, saved.getDepartment().getId().longValue());

    }

    @Test
    public void delete() {
        assertNotNull(service.findOne(1));
        service.delete(1);
        assertNull(service.findOne(1));
    }




}
