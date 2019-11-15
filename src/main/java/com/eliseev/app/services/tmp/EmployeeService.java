package com.eliseev.app.services.tmp;

import com.eliseev.app.models.tmp.Employee;
import com.eliseev.app.repository.tmp.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Transactional(readOnly = true)
    public Employee findOne(long id) {
        return employeeDAO.findOne(id);
    }

    @Transactional
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Transactional
    public void delete(long id) {
        employeeDAO.delete(id);
    }

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Transactional(readOnly = true)
    public long count() {
        return employeeDAO.count();
    }

    @Transactional
    public Employee update(Employee employee) {
        return employeeDAO.save(employee);
    }

}
