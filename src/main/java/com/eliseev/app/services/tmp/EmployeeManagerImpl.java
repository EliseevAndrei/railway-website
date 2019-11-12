package com.eliseev.app.services.tmp;

import com.eliseev.app.models.tmp.Department;
import com.eliseev.app.models.tmp.Employee;
import com.eliseev.app.repository.tmp.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeManagerImpl implements EmployeeManager {

    @Autowired
    private EmployeeDAO dao;

    public EmployeeManagerImpl(EmployeeDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return dao.getAllEmployees();
    }

    @Override
    public List<Department> getAllDepartments() {
        return dao.getAllDepartments();
    }

    @Override
    public void addEmployee(Employee employee) {
        dao.addEmployee(employee);
    }
}
