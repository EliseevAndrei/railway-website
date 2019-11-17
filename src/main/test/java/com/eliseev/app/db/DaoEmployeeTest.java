package com.eliseev.app.db;

import com.eliseev.app.models.tmp.Department;
import com.eliseev.app.models.tmp.Employee;
import com.eliseev.app.repository.tmp.DepartmentDAO;
import com.eliseev.app.services.tmp.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class DaoEmployeeTest {

    private Logger logger = LoggerFactory.getLogger(DaoEmployeeTest.class);

    @Autowired
    private EmployeeService service;
    @Autowired
    private DepartmentDAO departmentDAO;

    @Test
    @Transactional
    public void count() {
        assertEquals(3, service.count());
    }

    @Test
    @Transactional
    public void findOne() {
        Employee employee = service.findOne(1);
        assertEquals(employee.getLastName(), "eliseev");
        assertEquals(employee.getFirstName(), "andrei");
        assertEquals(employee.getEmail(), "eliseev.andrei345@mail.ru");
        assertEquals(employee.getDepartment().getName(), "dep");
    }

    @Test
    @Transactional
    public void save() {
        assertEquals(3, service.count());
        Department department = new Department("dep");
        Employee employee = new Employee("vera", "kozlova", "verko@mail.ru", department);
        Employee saved = service.save(employee);
        Employee found = service.findOne(4);
        assertEquals(found, saved);
    }

    @Test
    @Transactional
    public void delete() {
        assertNotNull(service.findOne(1));
        service.delete(1);
        assertNull(service.findOne(1));
    }

    @Test
    @Transactional
    public void update() {
        assertEquals(3, service.count());
        Department department = new Department("new dep");
        Employee employee = new Employee(1L,"vera", "kozlova", "verko@mail.ru", department);
        Employee saved = service.update(employee);
        Employee found = service.findOne(1L);
        assertEquals(saved, found);
        assertEquals(3, service.count());
    }


    @Test
    @Transactional
    public void departmentRepositoryTest() {

        Department department = new Department("arr");
        Employee employee = new Employee(1L,"vera", "kozlova", "verko@mail.ru", department);

        service.save(employee);

    }


}
