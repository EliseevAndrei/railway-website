package com.eliseev.app.repository.tmp;

import com.eliseev.app.models.tmp.Employee;

import java.util.List;


public interface EmployeeDAO {

    long count();

    Employee findOne(long id);

    Employee save(Employee employee);

    void delete(long id);

    List<Employee> findAll();

}
