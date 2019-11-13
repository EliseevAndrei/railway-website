package com.eliseev.app.repository.tmp;

import com.eliseev.app.models.tmp.Employee;

import java.util.List;


public interface EmployeeDAO {

    long count();

    Employee findOne(long id);

    Employee save(Employee employee);

    List<Employee> findById(long id);

    void delete(long id);

    List<Employee> findAll();

}
