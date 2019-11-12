package com.eliseev.app.repository.tmp;

import com.eliseev.app.models.tmp.Department;
import com.eliseev.app.models.tmp.Employee;

import java.util.List;


public interface EmployeeDAO {

    List<Employee> getAllEmployees();
    List<Department> getAllDepartments();
    void addEmployee(Employee employee);

}
